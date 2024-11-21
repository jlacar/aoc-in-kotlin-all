package lacar.junilu.aoc2015.day22

import kotlin.math.min
import kotlin.math.max

class Day22(val wizard: Wizard, val boss: Boss) {
    fun cheapestWin(): Int {
        var cheapest = Int.MAX_VALUE
        var nextRound = Fight(wizard, boss).nextRound()
        while (nextRound.isNotEmpty()) {
            val (wins, nonWins) = nextRound.partition { it.wizardWins() && it.cost < cheapest }
            if (wins.isNotEmpty()) {
                cheapest = min(cheapest, wins.minOf { it.cost })
            }
            nextRound = nonWins.flatMap { it.nextRound() }.filterNot { it.wizardLoses() || it.cost > cheapest }
        }
        return cheapest
    }
}

data class ActiveSpell(val spell: Spell, val timer: Int) {
    fun isEnding() = timer == 1
    fun isNotEnding() = timer > 1
    fun decrease() = copy(timer = timer - 1)
}

class Fight(val wizard: Wizard, val boss: Boss, val spells: List<ActiveSpell> = emptyList()) {

    val cost: Int get() = wizard.spent

    fun nextRound(): List<Fight> {
        val (wizardWins, bossTurn) = wizardTurnOutcomes().partition { it.wizardWins() }
        return wizardWins + bossTurn.map { it.bossTurnOutcome() }
    }

    private fun bossTurnOutcome(): Fight {
        val afterAllSpellsApplied = afterAllSpellsApplied()
        if (afterAllSpellsApplied.wizardWins()) return afterAllSpellsApplied

        return afterAllSpellsApplied.copy(wizard = afterAllSpellsApplied.boss.attack(afterAllSpellsApplied.wizard))
    }

    private fun wizardTurnOutcomes(): List<Fight> {
        val afterAllSpellsApplied = afterAllSpellsApplied()
        if (afterAllSpellsApplied.wizardWins()) return listOf(afterAllSpellsApplied)

        return afterAllSpellsApplied.allCastOutcomes()
    }

    private fun allCastOutcomes(): List<Fight> =
        availableSpells().map { spell ->
            Fight(
                wizard = spell.effects.applyOnCast(wizard.spend(spell.cost)),
                boss = spell.effects.applyOnCast(boss),
                spells = when {
                    spell.duration > 0 -> spells + spell.activate()
                    else -> spells
                }
            )
        }

    fun availableSpells() = Spell.entries
            .filter { wizard.canAfford(it.cost) }
            .minus(spells.filterNot { it.isEnding() }.map { it.spell }.toSet())

    fun copy(wizard: Wizard = this.wizard, boss: Boss = this.boss, spells: List<ActiveSpell> = this.spells) =
        Fight(wizard, boss, spells)

    private fun afterAllSpellsApplied(): Fight {
        val wizardOnTurn = spells.applyOnTurn(wizard)
        val wizardOnEnd = spells.allEndingSpells().applyOnEnd(wizardOnTurn)
        val bossOnTurn = spells.applyOnTurn(boss)
        return Fight(wizard = wizardOnEnd, boss = bossOnTurn, spells = spells.decreaseAllNotEnding())
    }

    private fun List<SpellEffect>.applyOnCast(wizard: Wizard) = fold(wizard) { w, effect -> effect.onCast(w) }
    private fun List<SpellEffect>.applyOnCast(boss: Boss) = fold(boss) { b, effect -> effect.onCast(b) }

    private fun List<ActiveSpell>.allEndingSpells() = filter { it.isEnding() }
    private fun List<ActiveSpell>.decreaseAllNotEnding() = filter { it.isNotEnding() }.map { it.decrease() }

    private fun List<ActiveSpell>.applyOnTurn(wizard: Wizard) = fold(wizard) { w, activeSpell ->
        activeSpell.spell.effects.fold(w) { wiz, spellEffect -> spellEffect.onTurn(wiz) }
    }

    private fun List<ActiveSpell>.applyOnTurn(boss: Boss) = fold(boss) { b, activeSpell ->
        activeSpell.spell.effects.fold(b) { boss, spellEffect -> spellEffect.onTurn(boss) }
    }

    private fun List<ActiveSpell>.applyOnEnd(wizard: Wizard) =
        allEndingSpells().fold(wizard) { w, endingSpell ->
            endingSpell.spell.effects.fold(w) { wiz, effect -> effect.onEnd(wiz) }
        }

    fun wizardWins() = wizard.isAlive() && boss.isDead()
    fun wizardLoses() = !wizard.isAlive()
    fun wizardCantCast() = availableSpells().isEmpty()

    fun hasActive(spell: Spell) = spells.any { (sp, timer) -> spell == sp && timer > 0 }
    fun hasActive(spell: Spell, timeLeft: Int) = spells.any { (sp, timer) -> spell == sp && timeLeft == timer }


    override fun toString() = "Fight[$wizard, $boss, ${spells.joinToString(",\n")}]\n"
}

data class Boss(
    val points: Int,
    val damage: Int
) {
    fun isDead() = points <= 0
    fun attack(wizard: Wizard) = wizard.takeDamage(damage)
    fun takeDamage(points: Int) = copy(points = this.points - points)
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

    fun takeDamage(points: Int) = copy(points = this.points - max(points - armor, 1))
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
    override fun onCast(boss: Boss) = boss.takeDamage(points)
}

private class Heal(private val points: Int) : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.heal(points)
}

private class Damage(private val points: Int) : SpellEffect {
    override fun onTurn(boss: Boss) = boss.takeDamage(points)
}

private class Armor(private val amount: Int) : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.copy(armor = amount)
    override fun onEnd(wizard: Wizard) = wizard.copy(armor = 0)
}

private class Recharge(private val mana: Int) : SpellEffect {
    override fun onTurn(wizard: Wizard) = wizard.copy(mana = wizard.mana + mana)
}

enum class Spell(val cost: Int, val duration: Int, val effects: List<SpellEffect>) {
    MAGIC_MISSILE(
        cost = 53, duration = 0,
        listOf(InstantDamage(4))
    ),
    DRAIN(
        cost = 73, duration = 0,
        listOf(InstantDamage(2), Heal(2))
    ),
    SHIELD(
        cost = 113, duration = 6,
        listOf(Armor(7))
    ),
    POISON(
        cost = 173, duration = 6,
        listOf(Damage(3))
    ),
    RECHARGE(
        cost = 229, duration = 5,
        listOf(Recharge(101))
    );

    fun activate(): ActiveSpell = ActiveSpell(this, duration)
}