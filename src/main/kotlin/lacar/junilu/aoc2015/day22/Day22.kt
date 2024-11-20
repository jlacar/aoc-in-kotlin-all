package lacar.junilu.aoc2015.day22

import kotlin.math.max
import kotlin.math.min

class Day22(val wizard: Wizard, val boss: Boss) {
    fun cheapestWizardWin(): Int = Fight(wizard, boss).cheapestWin()

//    private fun cheapestWin(fight: Fight, leastCost: Int): Int {
//        val afterCasts = fight.casts()
//        val afterAttack = afterCasts
//                .filter { !it.isOver() && it.cost() < leastCost }
//                .map { bossTurn -> bossTurn.applyActiveSpells().attack() }
//
//        val wonInThisRound = afterCasts.filter { it.wizardWins() } + afterAttack.filter { it.wizardWins() }
//        val cheapestThisRound = min(leastCost, wonInThisRound.minOfOrNull { it.cost() } ?: leastCost)
//
//        val cheapestRemaining = afterAttack
//                .filter { !it.isOver() && it.cost() < cheapestThisRound }
//                .minOfOrNull { cheapestWin(it, cheapestThisRound) } ?: cheapestThisRound
//
//        return min(cheapestThisRound, cheapestRemaining)
//    }
}

data class ActiveSpell(val spell: Spell, val timer: Int) {
    fun isEnding() = timer == 1
    fun isNotEnding() = timer > 1
    fun decrease() = copy(timer = timer - 1)
}

class Fight(val wizard: Wizard, val boss: Boss, val spells: List<ActiveSpell> = emptyList()) {

    fun cheapestWin(cheapestSoFar: Int = Int.MAX_VALUE): Int {
        val (wonCast, didntWinCast) = casts().partition { it.wizardWins() }

        val cheapestCastWin = wonCast.filter { it.cost() < cheapestSoFar }
            .minOfOrNull { it.cost() } ?: cheapestSoFar

        val (wonAttack, didntWinRound) = didntWinCast.filterNot { it.wizardLoses() || it.cost() >= cheapestCastWin }
            .map { bossTurn -> bossTurn.attack() }
            .partition { it.wizardWins() }

        val cheapestAttackWin = wonAttack.filter { it.cost() < cheapestCastWin }
            .minOfOrNull { it.cost() } ?: cheapestCastWin

        val cheapestRoundWin = min(cheapestCastWin, cheapestAttackWin)
        val cheapestRemaining = didntWinRound.filterNot { it.wizardLoses() || it.cost() >= cheapestAttackWin }
            .minOfOrNull { it.cheapestWin(cheapestRoundWin) } ?: cheapestRoundWin

        return min(cheapestRemaining, cheapestRoundWin)
    }

    fun casts(): List<Fight> {
        val afterSpellsApplied = applyActiveSpells()
        return availableSpells().map { afterSpellsApplied.cast(it) }
    }

    fun attack(): Fight {
        val afterSpellsApplied = applyActiveSpells()
        return if (afterSpellsApplied.boss.isDead()) afterSpellsApplied
               else Fight(
                        wizard = afterSpellsApplied.boss.attack(afterSpellsApplied.wizard),
                        boss = afterSpellsApplied.boss,
                        spells = afterSpellsApplied.spells
                    )
    }

    override fun toString() = "Fight[$wizard, $boss, ${spells.joinToString(",\n")}]\n"

    fun cast(spell: Spell) = Fight(
        wizard = spell.effects.applyOnCast(wizard.spend(spell.cost)),
        boss = spell.effects.applyOnCast(boss),
        spells = when {
                    spell.duration > 0 -> spells.decreaseAllNotEnding() + spell.activate()
                    else -> spells.decreaseAllNotEnding()
                 }
    )

    private fun List<SpellEffect>.applyOnCast(wizard: Wizard) = fold(wizard) { w, effect -> effect.onCast(w) }
    private fun List<SpellEffect>.applyOnCast(boss: Boss) = fold(boss) { b, effect -> effect.onCast(b) }

    fun availableSpells(): List<Spell> {
        val notEnding = spells.filter { it.isNotEnding() }.map { it.spell }.toSet()
        return Spell.entries.filter { wizard.canAfford(it.cost) }.minus(notEnding)
    }

    fun applyActiveSpells(): Fight {
        val wizardOnTurn = spells.applyOnTurn(wizard)
        return Fight(
            wizard = spells.applyOnEnd(wizardOnTurn),
            boss = spells.applyOnTurn(boss),
            spells = spells.decreaseAllNotEnding()
        )
    }

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

    fun isOver() = wizardWins() || wizardLoses()
    fun wizardWins() = wizard.isAlive() && boss.isDead()
    fun wizardLoses() = !wizard.isAlive() || availableSpells().isEmpty()

    fun hasActive(spell: Spell) = spells.any { (sp, timer) -> spell == sp && timer > 0 }
    fun hasActive(spell: Spell, timeLeft: Int) = spells.any { (sp, timer) -> spell == sp && timeLeft == timer }

    fun cost(): Int = wizard.spent
}

data class Boss(
    val points: Int,
    val damage: Int
) {
    fun isDead() = points <= 0
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