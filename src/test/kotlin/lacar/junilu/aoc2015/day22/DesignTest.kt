package lacar.junilu.aoc2015.day22

import lacar.junilu.aoc2015.day22.Spell.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DesignTest {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - `() {
            TODO()
//            assertEquals(0,
//                Simulator(
//                    Wizard(points = 50, mana = 500)
//                    Boss22(points = 58, damage = 9),
//                )
//                .cheapestWizardWin()
//            )
        }
    }

    @Nested
    inner class Spells {
        private val boss = Boss(points = 13, damage = 8)
        private val wizard = Wizard(points = 10, mana = 250)
        private val fight = Fight(wizard, boss)

        @Nested
        inner class `Magic Missile` {
            private val outcome = fight.cast(MAGIC_MISSILE)

            @Test
            fun `costs wizard 53 mana`() {
                assertEquals(53, wizard.mana - outcome.wizard.mana)
            }

            @Test
            fun `instantly does 4 damage to boss`() {
                assertEquals(4, boss.points - outcome.boss.points)
            }

            @Test
            fun `does not start a timed effect`() {
                assertFalse(outcome.hasActive(MAGIC_MISSILE))
            }

            @Test
            fun `fight so far costs 53 mana`() {
                assertEquals(53, outcome.cost())
            }
        }

        @Nested
        inner class Drain {
            private val outcome = fight.cast(DRAIN)

            @Test
            fun `costs wizard 73 mana`() {
                assertEquals(-73, outcome.wizard.mana - wizard.mana)
            }

            @Test
            fun `heals wizard for 2 hit points`() {
                assertEquals(2, outcome.wizard.points - wizard.points)
            }

            @Test
            fun `does not start a timed effect`() {
                assertFalse(outcome.hasActive(DRAIN))
            }
        }

        @Nested
        inner class Shield {
            private val afterCast = fight.cast(SHIELD)

            @Test
            fun `fight so far costs 113 mana`() {
                assertEquals(113, afterCast.cost())
            }

            @Test
            fun `costs wizard 113 mana`() {
                assertEquals(-113, afterCast.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 6 turns`() {
                assertTrue(afterCast.hasActive(SHIELD, 6))
            }

            @Test
            fun `does not affect boss when cast`() {
                assertEquals(boss, afterCast.boss)
            }

            @Nested
            inner class `When boss attacks`() {
                private val afterAttack = afterCast.attack()

                @Test
                fun `gives wizard 7 armor`() {
                    assertEquals(7, afterAttack.wizard.armor)
                }

                @Test
                fun `does not affect boss`() {
                    assertEquals(afterCast.boss, afterAttack.boss)
                }

                @Test
                fun `decreases its timer`() {
                    assertTrue(afterAttack.hasActive(SHIELD, 5))
                }
            }
        }
//
//        @Nested
//        inner class Poison {
//            private val outcome = wizard.cast(POISON, boss)
//
//            @Test
//            fun `costs wizard 173 mana`() {
//                assertEquals(-173, outcome.wizard.mana - wizard.mana)
//            }
//
//            @Test
//            fun `starts an effect that lasts for 6 turns`() {
//                assertTrue(outcome.effects.isNotEmpty())
//                assertEquals(6, outcome.effects.first().timer)
//            }
//
//            @Test
//            fun `does not immediately deal damage to boss when cast`() {
//                assertEquals(boss, outcome.boss)
//            }
//
//            @Nested
//            inner class `when Active during a turn`() {
//                private val effect = Effect(spell = POISON, damage = 3, timer = 6)
//                private val outcome = effect.applyTo(wizard, boss)
//
//                @Test
//                fun `deals 3 damage to boss`() {
//                    assertEquals(-3, outcome.boss.points - boss.points)
//                }
//
//                @Test
//                fun `decreases timer by 1`() {
//                    assertEquals(-1, outcome.effects.first().timer - effect.timer)
//                }
//            }
//        }
//
//        @Nested
//        inner class Recharge {
//            private val outcome = wizard.cast(RECHARGE, boss)
//
//            @Test
//            fun `costs wizard 229 mana`() {
//                assertEquals(-229, outcome.wizard.mana - wizard.mana)
//            }
//
//            @Test
//            fun `starts an effect that lasts for 5 turns`() {
//                assertTrue(outcome.effects.isNotEmpty())
//                assertEquals(5, outcome.effects.first().timer)
//            }
//
//            @Test
//            fun `does not affect boss when cast`() {
//                assertEquals(boss, outcome.boss)
//            }
//
//            @Nested
//            inner class `when Active during a turn`() {
//                private val effect = Effect(spell = RECHARGE, recharge = 101, timer = 5)
//                private val outcome = effect.applyTo(wizard, boss)
//
//                @Test
//                fun `gives 101 new mana to wizard`() {
//                    assertEquals(101, outcome.wizard.mana - wizard.mana)
//                }
//
//                @Test
//                fun `decreases timer by 1`() {
//                    assertEquals(-1, outcome.effects.first().timer - effect.timer)
//                }
//            }
//        }
//    }
//
//    @Nested
//    inner class `Boss attacks` {
//
//        private val irrelevantNonZero = 1
//
//        @Test
//        fun `deals full damage when wizard has no armor`() {
//            val boss = Boss(damage = 8, points = irrelevantNonZero)
//            val wizard = Wizard(points = 10, armor = 0, mana = irrelevantNonZero)
//
//            val outcome = boss.attack(wizard)
//
//            assertEquals(boss.damage, wizard.points - outcome.wizard.points)
//        }
//
//        @ParameterizedTest(name = "armor: {0}, damage done: {1}")
//        @CsvSource("8, 1", "10, 1", "7, 1", "6, 2", "5, 3")
//        fun `deals at least 1 damage when wizard has armor`(armor: Int, expectedDamage: Int) {
//            val boss = Boss(damage = 8, points = irrelevantNonZero)
//            val wizard = Wizard(points = 10, armor = armor, mana = irrelevantNonZero)
//
//            val outcome = boss.attack(wizard)
//
//            assertEquals(expectedDamage, wizard.points - outcome.wizard.points)
//        }
//    }
    }
}