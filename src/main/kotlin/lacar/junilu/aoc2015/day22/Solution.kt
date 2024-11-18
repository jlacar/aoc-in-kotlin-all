package lacar.junilu.aoc2015.day22

import kotlin.math.max

class Simulator(val wizard: Wizard, val boss: Boss) {

    fun cheapestWizardWin(): Int = fightsWonByWizard().minOf { it.cost() }

    private fun fightsWonByWizard(): List<Fight> = emptyList()
}

class Fight(val wizard: Wizard, val boss: Boss, private val spells: List<Pair<Spell, Int>> = emptyList()) {
//    fun wizardTurns(): List<Fight> {}
//    fun bossTurns(): List<Fight> {}

    fun cast(spell: Spell): Fight {
        val wizardAfter = spell.effects.fold(wizard.buy(spell.cost)) { wiz, effect -> effect.onCast(wiz) }
        val bossAfter = spell.effects.fold(boss) { boss, effect -> effect.onCast(boss) }

        return Fight(wizardAfter, bossAfter, spells + spell.activate())
    }

    fun applySpells(): Fight {
        val activeEffects = spells.filter { (_, t) -> t > 0 }.flatMap { it.first.effects }
        return Fight(
            wizard = activeEffects.applyOnTurn(wizard.noArmor()),
            boss = activeEffects.applyOnTurn(boss),
            spells = decreaseTimers()
        )
    }

    private fun List<SpellEffect>.applyOnTurn(wizard: Wizard) = fold(wizard) { w, effect -> effect.onTurn(w) }
    private fun List<SpellEffect>.applyOnTurn(boss: Boss) = fold(boss) { b, effect -> effect.onTurn(b) }
    private fun decreaseTimers() = spells.map { (sp, t) -> Pair(sp, if (t > 0) t - 1 else 0) }

    fun attack(): Fight = if (boss.isDead()) this else Fight(boss.attack(wizard), boss, spells)

    fun wizardWins() = wizard.isAlive() && boss.isDead()

    fun hasActive(spell: Spell, timer: Int = 0) =
        if (timer == 0)
            spells.any { (sp, t) -> spell == sp && t > 0 }
        else
            spells.any { (sp, t) -> spell == sp && timer == t }

    fun cost(): Int = spells.sumOf { (spell, _) -> spell.cost }
}

data class Boss(
    val points: Int,
    val damage: Int
) {
    fun isAlive() = points > 0
    fun isDead() = !isAlive()

    fun takeAway(damage: Int) = copy(points = points - damage)
    fun attack(wizard: Wizard) = wizard.receive(damage)
}

data class Wizard(
    val points: Int,
    val mana: Int,
    val armor: Int = 0
) {
    fun isAlive() = points > 0

    fun receive(damage: Int) = copy(points = points - max(damage - armor, 1))

    fun buy(cost: Int) = copy(mana = mana - cost)

    fun noArmor() = copy(armor = 0)

    fun healBy(points: Int): Wizard = copy(points = this.points + points)
}

interface SpellEffect {
    fun onCast(wizard: Wizard) = wizard
    fun onCast(boss: Boss) = boss

    fun onTurn(wizard: Wizard) = wizard
    fun onTurn(boss: Boss) = boss
}

class InstantDamage(private val points: Int): SpellEffect {
    override fun onCast(boss: Boss) = boss.takeAway(points)
}

class Heal(private val points: Int): SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.healBy(points)
}

class Damage(private val points: Int): SpellEffect {
    override fun onTurn(boss: Boss) = boss.takeAway(points)
}

class Armor(private val amount: Int): SpellEffect {
    override fun onTurn(wizard: Wizard) = wizard.copy(armor = amount)
}

class Recharge(private val mana: Int): SpellEffect {
    override fun onTurn(wizard: Wizard) = wizard.copy(mana = wizard.mana + mana)
}

enum class Spell(val cost: Int, val timer: Int, val effects: List<SpellEffect>) {
    MAGIC_MISSILE(cost = 53, timer = 0, listOf(InstantDamage(4))),

    DRAIN(cost = 73, timer = 0, listOf(InstantDamage(2), Heal(2))),

    SHIELD(cost = 113, timer = 6, listOf(Armor(7))),

    POISON(cost = 173, timer = 6, listOf(Damage(3))),

    RECHARGE(cost = 229, timer = 5, listOf(Recharge(101)));

    fun activate(): Pair<Spell, Int> = Pair(this, timer)
}