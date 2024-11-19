package lacar.junilu.aoc2015.day22

import kotlin.math.max

class Day22(val wizard: Wizard, val boss: Boss) {

    fun cheapestWizardWin(): Int = fightsWonByWizard().minOf { it.wizard.spent }

    fun fightsWonByWizard(): Sequence<Fight> = Fight(wizard, boss).sequence().filter { it.wizardWins() }
}

data class ActiveSpell(val spell: Spell, val timer: Int) {
    fun isEnding() = timer == 1
    fun decrease() = copy(timer = timer - 1)
}

class Fight(val wizard: Wizard, val boss: Boss, private val spells: List<ActiveSpell> = emptyList()) {

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
        spells = when {
                    spell.duration > 0 -> spells + spell.activate()
                    else -> spells
                 }
    )

    private fun List<SpellEffect>.applyOnCast(wizard: Wizard) = fold(wizard) { w, effect -> effect.onCast(w) }
    private fun List<SpellEffect>.applyOnCast(boss: Boss) = fold(boss) { b, effect -> effect.onCast(b) }

    fun spellsAvailableToCast(): List<Spell> = when {
        wizard.isAlive() -> Spell.entries.filter { spell -> !hasActive(spell) && wizard.canAfford(spell.cost)}
        else -> emptyList()
    }

    fun applyActiveSpells(): Fight {
        val wizardOnTurn = spells.applyOnTurn(wizard)
        val wizardOnEnd = spells.applyOnEnd(wizardOnTurn)
        val bossOnTurn = spells.applyOnTurn(boss)
        return Fight(
            wizard = wizardOnEnd,
            boss = bossOnTurn,
            spells = decreaseTimers()
        )
    }

    private fun List<ActiveSpell>.applyOnTurn(wizard: Wizard) = fold(wizard) { w, activeSpell ->
        activeSpell.spell.effects.fold(w) { wiz, spellEffect -> spellEffect.onTurn(wiz) }
    }

    private fun List<ActiveSpell>.applyOnTurn(boss: Boss) = fold(boss) { b, activeSpell ->
        activeSpell.spell.effects.fold(b) { boss, spellEffect -> spellEffect.onTurn(boss) }
    }

    private fun List<ActiveSpell>.applyOnEnd(wizard: Wizard) = filter { it.isEnding() }
        .fold(wizard) { w, endingSpell ->
            endingSpell.spell.effects.fold(w) { wiz, spellEffect ->
                spellEffect.onEnd(wizard)
            }
        }

    private fun decreaseTimers() = spells.filter { !it.isEnding() }.map { it.decrease() }

    fun attack(): Fight = if (boss.isDead()) this else Fight(boss.attack(wizard), boss, spells)

    fun isOver() = wizardWins() && wizardLoses()
    fun wizardWins() = wizard.isAlive() && boss.isDead()
    fun wizardLoses() = !wizard.isAlive() || spellsAvailableToCast().isEmpty()

    fun hasActive(spell: Spell) = spells.any { (sp, timer) -> spell == sp && timer > 0 }
    fun hasActive(spell: Spell, timeLeft: Int) = spells.any { (sp, timer) -> spell == sp && timeLeft == timer }

    fun cost(): Int = wizard.spent
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

private fun instantDamage(points: Int) : SpellEffect = object: SpellEffect {
    override fun onCast(boss: Boss) = boss.weaken(points)
}

private fun heal(points: Int) = object : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.heal(points)
}

private fun damage(points: Int) = object : SpellEffect {
    override fun onTurn(boss: Boss) = boss.weaken(points)
}

private fun armor(amount: Int) = object : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.copy(armor = amount)
    override fun onEnd(wizard: Wizard) = wizard.copy(armor = 0)
}

private fun recharge(mana: Int) = object : SpellEffect {
    override fun onTurn(wizard: Wizard) = wizard.copy(mana = wizard.mana + mana)
}

enum class Spell(val cost: Int, val duration: Int, val effects: List<SpellEffect>) {
    MAGIC_MISSILE(cost = 53, duration = 0, listOf(instantDamage(4))),

    DRAIN(cost = 73, duration = 0, listOf(instantDamage(2), heal(2))),

    SHIELD(cost = 113, duration = 6, listOf(armor(7))),

    POISON(cost = 173, duration = 6, listOf(damage(3))),

    RECHARGE(cost = 229, duration = 5, listOf(recharge(101)));

    fun activate(): ActiveSpell = ActiveSpell(this, duration)
}