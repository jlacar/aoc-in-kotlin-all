package lacar.junilu.aoc2015.day22

import kotlin.math.max

class Simulator(val wizard: Wizard, val boss: Boss) {

    fun cheapestWizardWin(): Int = fightsWonByWizard().minOf { it.wizard.spent }

    fun fightsWonByWizard(): Sequence<Fight> = Fight(wizard, boss).sequence().filter { it.wizardWins() }
}

typealias TimedEffect = Pair<Spell, Int>
val TimedEffect.spell get() = first
val TimedEffect.timer get() = second
fun TimedEffect.isEnding() = timer == 1
fun TimedEffect.decrease() = copy(second = timer - 1)

class Fight(val wizard: Wizard, val boss: Boss, private val spells: List<Pair<Spell, Int>> = emptyList()) {

    fun sequence(): Sequence<Fight> = sequence {
        val casts = spellsAvailableToCast().asSequence().map { applyActiveSpells().cast(it) }
        yieldAll(casts.filter { it.isOver() })

        val attacks = casts.filter { !it.isOver() }.map { it.applyActiveSpells().attack() }
        yieldAll(attacks.filter { it.isOver() })

        attacks.filter { !it.isOver() }.forEach { yieldAll(it.sequence()) }
    }

    override fun toString() = "Fight[$wizard, $boss, ${spells.joinToString(",\n")}]\n"

    fun cast(spell: Spell) = Fight(
        wizard = spell.effects.applyOnCast(wizard.spend(spell.cost)),
        boss = spell.effects.applyOnCast(boss),
        spells = spells + spell.activate()
    )

    private fun List<SpellEffect>.applyOnCast(wizard: Wizard) = fold(wizard) { w, effect -> effect.onCast(w) }
    private fun List<SpellEffect>.applyOnCast(boss: Boss) = fold(boss) { b, effect -> effect.onCast(b) }

    fun spellsAvailableToCast(): List<Spell> = when {
        wizard.isAlive() -> Spell.entries.filter { spell -> !hasActive(spell) && wizard.canAfford(spell.cost)}
        else -> emptyList()
    }

    fun applyActiveSpells(): Fight {
        // TODO - check logic here
        val activeEffects = spells.filter { (_, t) -> t > 0 }.flatMap { it.first.effects }
        return Fight(
            wizard = activeEffects.applyOnTurn(wizard),
            boss = activeEffects.applyOnTurn(boss),
            spells = decreaseTimers()
        )
    }

    private fun List<SpellEffect>.applyOnTurn(wizard: Wizard) = fold(wizard) { w, effect -> effect.onTurn(w) }
    private fun List<SpellEffect>.applyOnTurn(boss: Boss) = fold(boss) { b, effect -> effect.onTurn(b) }
    private fun decreaseTimers() = spells.map { (sp, t) -> Pair(sp, if (t > 0) t - 1 else 0) }

    fun attack(): Fight = if (boss.isDead()) this else Fight(boss.attack(wizard), boss, spells)

    fun isOver() = wizardWins() && wizardLoses()
    fun wizardWins() = wizard.isAlive() && boss.isDead()
    fun wizardLoses() = !wizard.isAlive() || spellsAvailableToCast().isEmpty()

    fun hasActive(spell: Spell) = spells.any { (sp, timer) -> spell == sp && timer > 0 }
    fun hasActive(spell: Spell, timeLeft: Int) = spells.any { (sp, timer) -> spell == sp && timeLeft == timer }

    fun cost(): Int = spells.sumOf { (spell, _) -> spell.cost }
}

data class Boss(
    val points: Int,
    val damage: Int
) {
    fun isAlive() = points > 0
    fun isDead() = !isAlive()

    fun attack(wizard: Wizard) = wizard.weaken(damage)
    fun weaken(points: Int) = copy(points = this.points - points)
}

data class Wizard(
    val points: Int,
    val mana: Int,
    val armor: Int = 0,
    val spent: Int = 0
) {
    fun isAlive() = points > 0
    fun canAfford(cost: Int) = mana >= cost
    fun spend(cost: Int) = copy(mana = mana - cost, spent = spent + cost)

    fun weaken(points: Int) = copy(points = this.points - max(points - armor, 1))
    fun heal(points: Int) = copy(points = this.points + points)
}

/*
 * By default, methods are no-ops and return the same
 * object they receive. Override to return another object
 * that shows how the original object would be affected.
 */
interface SpellEffect {
    fun onCast(wizard: Wizard) = wizard
    fun onCast(boss: Boss) = boss

    fun onTurn(wizard: Wizard) = wizard
    fun onTurn(boss: Boss) = boss

    fun onEnd(wizard: Wizard) = wizard
}

private class InstantDamage(private val points: Int) : SpellEffect {
    override fun onCast(boss: Boss) = boss.weaken(points)
}

private class Heal(private val points: Int) : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.heal(points)
}

private class Damage(private val points: Int) : SpellEffect {
    override fun onTurn(boss: Boss) = boss.weaken(points)
}

private class Armor(private val amount: Int) : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.copy(armor = amount)
    override fun onEnd(wizard: Wizard) = wizard.copy(armor = 0)
}

private class Recharge(private val mana: Int) : SpellEffect {
    override fun onTurn(wizard: Wizard) = wizard.copy(mana = wizard.mana + mana)
}

enum class Spell(val cost: Int, val duration: Int, val effects: List<SpellEffect>) {
    MAGIC_MISSILE(cost = 53, duration = 0, listOf(InstantDamage(4))),

    DRAIN(cost = 73, duration = 0, listOf(InstantDamage(2), Heal(2))),

    SHIELD(cost = 113, duration = 6, listOf(Armor(7))),

    POISON(cost = 173, duration = 6, listOf(Damage(3))),

    RECHARGE(cost = 229, duration = 5, listOf(Recharge(101)));

    fun activate(): Pair<Spell, Int> = Pair(this, duration)
}