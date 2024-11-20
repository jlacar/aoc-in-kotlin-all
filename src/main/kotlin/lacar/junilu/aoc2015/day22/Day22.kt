package lacar.junilu.aoc2015.day22

import kotlin.math.max
import kotlin.math.min

class Day22(val wizard: Wizard, val boss: Boss) {
    fun cheapestWizardWin(): Int = Fight(wizard, boss).cheapestWin()
}

data class ActiveSpell(val spell: Spell, val timer: Int) {
    fun isEnding() = timer <= 1
    fun isNotEnding() = timer > 1
    fun decrease() = copy(timer = timer - 1)
}

class Fight(val wizard: Wizard, val boss: Boss, val spells: List<ActiveSpell> = emptyList()) {

    fun cheapestWin(cheapestSoFar: Int = Int.MAX_VALUE): Int {
        val (wonOnCast, didntWinCast) = casts().partition { it.wizardWins() }

        val cheapestWinOnCast = min(cheapestSoFar, wonOnCast.minOfOrNull { it.cost } ?: cheapestSoFar)

        val (wonRound, didntWinRound) = didntWinCast.filterNot { it.wizardLoses() || it.cost >= cheapestWinOnCast }
            .map { bossTurn -> bossTurn.attack() }
            .partition { it.wizardWins() }

        println(this.toString())
        printWon("on cast", wonOnCast)
        printWon("on attack", wonRound)

        val cheapestThisRound = min(cheapestWinOnCast, wonRound.minOfOrNull { it.cost } ?: cheapestWinOnCast)

        return didntWinRound.filterNot { it.wizardLoses() || it.wizardCantCast() || it.cost >= cheapestThisRound }
            .minOfOrNull { it.cheapestWin(cheapestThisRound) } ?: cheapestThisRound
    }

    private fun printWon(desc: String, won: List<Fight>) {
        if (won.isEmpty()) {
            println("no win $desc")
            return
        }
        val list = won.joinToString(separator = "\n") { it.toString() }
        println("won $desc: $list")
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

    fun cast(spell: Spell): Fight {
        val afterSpellsApplied = applyActiveSpells()
        val nextWizard = spell.effects.applyOnCast(afterSpellsApplied.wizard.spend(spell.cost))
        val nextBoss = spell.effects.applyOnCast(afterSpellsApplied.boss)
        val nextSpells = when {
            spell.duration > 0 -> afterSpellsApplied.spells + spell.activate()
            else -> afterSpellsApplied.spells
        }
        return Fight(wizard = nextWizard, boss = nextBoss, spells = nextSpells)
    }

    fun applyActiveSpells(): Fight {
        val wizardOnTurn = spells.applyOnTurn(wizard)
        val wizardOnEnd = spells.allEndingSpells().applyOnEnd(wizardOnTurn)
        val bossOnTurn = spells.applyOnTurn(boss)
        return Fight(wizard = wizardOnEnd, boss = bossOnTurn, spells = spells.decreaseAllNotEnding())
    }

    private fun List<SpellEffect>.applyOnCast(wizard: Wizard) = fold(wizard) { w, effect -> effect.onCast(w) }

    private fun List<SpellEffect>.applyOnCast(boss: Boss) = fold(boss) { b, effect -> effect.onCast(b) }

    fun availableSpells(): List<Spell> {
        val notEnding = spells.filterNot { it.isEnding() }.map { it.spell }.toSet()
        return Spell.entries.filter { wizard.canAfford(it.cost) }.minus(notEnding)
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

    fun wizardWins() = wizard.isAlive() && boss.isDead()
    fun wizardLoses() = !wizard.isAlive()
    fun wizardCantCast() = availableSpells().isEmpty()

    fun hasActive(spell: Spell) = spells.any { (sp, timer) -> spell == sp && timer > 0 }
    fun hasActive(spell: Spell, timeLeft: Int) = spells.any { (sp, timer) -> spell == sp && timeLeft == timer }

    val cost: Int get() = wizard.spent

    override fun toString() = "Fight[$wizard, $boss, ${spells.joinToString(",\n")}]\n"
}

data class Boss(
    val points: Int,
    val damage: Int
) {
    fun isDead() = points <= 0
    fun attack(wizard: Wizard) = wizard.damage(damage)
    fun damage(points: Int) = copy(points = this.points - points)
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

    fun damage(points: Int) = copy(points = this.points - max(points - armor, 1))
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
    override fun onCast(boss: Boss) = boss.damage(points)
}

private class Heal(private val points: Int) : SpellEffect {
    override fun onCast(wizard: Wizard) = wizard.heal(points)
}

private class Damage(private val points: Int) : SpellEffect {
    override fun onTurn(boss: Boss) = boss.damage(points)
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