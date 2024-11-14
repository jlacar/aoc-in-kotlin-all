package lacar.junilu

class Day22(val boss: Boss22, val wizard: Wizard) {

    fun cheapestWizardWin(): Battle22Result {
        TODO()
    }

}

data class Battle22Result(val boss: Boss22, val wizard: Wizard, val activeEffects: List<Effect>)

data class TurnResult(val boss: Boss22, val wizard: Wizard, val activeEffects: List<Effect> = emptyList())

data class Boss22(
    val points: Int,
    val damage: Int
) {
    fun isAlive() = points > 0
}

data class Wizard(
    val points: Int,
    val mana: Int
) {
    fun isAlive() = points > 0

    fun cast(spell: Spell, boss: Boss22): TurnResult {
        return TurnResult(
            boss = boss.copy(points = boss.points - spell.effect.damage),
            wizard = copy(points = points + spell.effect.points, mana = mana - spell.cost)
        )
    }
}

enum class Spell(val cost: Int, val effect: Effect) {
    MAGIC_MISSILE(53, Effect(damage = 4)),
    DRAIN(73, Effect(damage = 2, points = 2)),
    SHIELD(113, Effect(timer = 6, armor = 7)),
    POISON(173, Effect(timer = 6, damage = 3)),
    RECHARGE(229, Effect(timer = 5, mana = 101));
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
