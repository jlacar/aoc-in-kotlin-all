package lacar.junilu

import kotlin.math.max

class Day22(val boss: Boss22, val wizard: Wizard) {

    fun cheapestWizardWin(): Int = fightsWonByWizard().minOf { it.cost() }

    private fun fightsWonByWizard(): List<FightOutcome> = emptyList()
}

data class FightOutcome(val boss: Boss22, val wizard: Wizard, val spellsCast: List<Spell> = emptyList()) {
    fun wizardWins() = wizard.isAlive() && boss.isDead()

    fun wizardCasts(spell: Spell): FightOutcome {
        return this
    }

    fun bossAttacks(): FightOutcome {
        return this
    }

    fun cost() = spellsCast.sumOf { it.cost }
}

typealias TurnOutcome = Pair<Wizard, Boss22>

val TurnOutcome.wizard
    get() = first

val TurnOutcome.boss
    get() = second

data class Boss22(
    val points: Int,
    val damage: Int
) {
    fun isDead() = points <= 0

    fun attack(wizard: Wizard) = TurnOutcome (wizard.receive(damage), this)
}

data class Wizard(
    val points: Int,
    val mana: Int,
    val armor: Int = 0
) {
    fun isAlive() = points > 0

    fun receive(damage: Int) = copy(points = points - max(damage - armor, 1))

    fun cast(spell: Spell, boss: Boss22) = TurnOutcome(
        copy(points = points + spell.heal, mana = mana - spell.cost),
        boss.copy(points = boss.points - spell.damage),
    )
}

enum class Spell(
    val cost: Int,
    val damage: Int = 0,
    val timer: Int = 0,
    val heal: Int = 0,
    val armor: Int = 0,
    val recharge: Int = 0
) {
    MAGIC_MISSILE(53, damage = 4),
    DRAIN(73, damage = 2, heal = 2),
    SHIELD(113, timer = 6, armor = 7),
    POISON(173, timer = 6, damage = 3),
    RECHARGE(229, timer = 5, recharge = 101);
}

data class Effect(
    val timer: Int = 0,
    val armor: Int = 0,
    val damage: Int = 0,
    val points: Int = 0,
    val mana: Int = 0
) {
    fun hasEnded() = timer == 0
    fun isEnding() = timer == 1
    fun decrease() = if (timer > 0) copy(timer = timer - 1) else this
}
