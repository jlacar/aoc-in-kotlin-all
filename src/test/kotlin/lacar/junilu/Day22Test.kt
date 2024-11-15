package lacar.junilu

import lacar.junilu.Spell.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day22Test {

    @Nested
    inner class Solution {
//        @Test
//        fun `Part 1 - `() {
//            assertEquals(0,
//                Day22(
//                    Boss22(points = 58, damage = 9),
//                    Wizard(points = 50, mana = 500)
//                )
//                .cheapestWizardWin()
//            )
//        }
    }

    @Nested
    inner class `Casting Spells` {

        private val boss = Boss22(points = 13, damage = 8)
        private val wizard = Wizard(points = 10, mana = 250)

        @Nested
        inner class `Magic Missile Spell` {
            private val outcome = wizard.cast(MAGIC_MISSILE, boss)

            @Test
            fun `costs wizard 53 mana`() {
                assertEquals(53, wizard.mana - outcome.wizard.mana)
            }

            @Test
            fun `instantly does 4 damage to boss`() {
                assertEquals(4, boss.points - outcome.boss.points)
            }
        }

        @Nested
        inner class `Drain Spell` {
            private val outcome = wizard.cast(DRAIN, boss)

            @Test
            fun `costs wizard 73 mana`() {
                assertEquals(73, wizard.mana - outcome.wizard.mana)
            }

            @Test
            fun `heals wizard for 2 hit points`() {
                assertEquals(2, outcome.wizard.points - wizard.points)
            }
        }

        @Nested
        inner class `Shield Spell` {
            private val outcome = wizard.cast(SHIELD, boss)

            @Test
            fun `costs wizard 113 mana`() {
                assertEquals(113, wizard.mana - outcome.wizard.mana)
            }
        }

        @Nested
        inner class `Poison Spell` {
            private val outcome = wizard.cast(POISON, boss)

            @Test
            fun `costs wizard 173 mana`() {
                assertEquals(173, wizard.mana - outcome.wizard.mana)
            }
        }

        @Nested
        inner class `Recharge Spell` {
            private val outcome = wizard.cast(RECHARGE, boss)

            @Test
            fun `costs wizard 229 mana`() {
                assertEquals(229, wizard.mana - outcome.wizard.mana)
            }
        }
    }

    @Nested
    inner class `Boss attacks` {

        private val irrelevantNonZero = 1

        @Test
        fun `deals full damage when wizard has no armor`() {
            val boss = Boss22(damage = 8, points = irrelevantNonZero)
            val wizard = Wizard(points = 10, armor = 0, mana = irrelevantNonZero)

            val outcome = boss.attack(wizard)

            assertEquals(boss.damage, wizard.points - outcome.wizard.points)
        }

        @ParameterizedTest
        @CsvSource("8, 1", "10, 1", "7, 1", "6, 2", "5, 3")
        fun `deals at least 1 damage when wizard has armor`(armor: Int, expectedDamage: Int) {
            val boss = Boss22(damage = 8, points = irrelevantNonZero)
            val wizard = Wizard(points = 10, armor = armor, mana = irrelevantNonZero)

            val outcome = boss.attack(wizard)

            assertEquals(expectedDamage, wizard.points - outcome.wizard.points)
        }
    }
}