package lacar.junilu

import lacar.junilu.Spell.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day22Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - `() {
            TODO()
//            assertEquals(0,
//                Day22(
//                    Boss22(points = 58, damage = 9),
//                    Wizard(points = 50, mana = 500)
//                )
//                .cheapestWizardWin()
//            )
        }
    }

    @Nested
    inner class Spells {

        private val boss = Boss22(points = 13, damage = 8)
        private val wizard = Wizard(points = 10, mana = 250)

        @Test
        fun `Part 1 Example 1`() {
            val cast1w = wizard.cast(POISON, boss)

            assertAll("wizard turn 1 - casts Poison",
                { assertEquals(POISON, cast1w.effects.first().spell) },
                { assertEquals(6, cast1w.effects.first().timer) }
            )

            assertAll("boss turn 1 before attacking",
                { assertEquals(POISON, cast1w.effects.first().spell) },
                { assertEquals(Wizard(points = 10, mana = 77, armor = 0), cast1w.wizard) },
                { assertEquals(Boss22(points = 13, damage = 8), cast1w.boss) }
            )

            val apply1b = cast1w.effects.fold(cast1w) { acc, effect ->
                effect.applyTo(acc.wizard, acc.boss)
            }

            assertAll("boss turn 1 after effects applied",
                { assertEquals(POISON, apply1b.effects.first().spell) },
                { assertEquals(5, apply1b.effects.first().timer) }
            )

            val attack1b = apply1b.boss.attack(apply1b.wizard)

            assertAll("wizard turn 2 before applying effects",
                { assertEquals(Wizard(points = 2, mana = 77), attack1b.wizard) },
                { assertEquals(Boss22(points = 10, damage = 8), apply1b.boss) }
            )

            val apply2w = apply1b.effects.fold(apply1b.copy(wizard = attack1b.wizard)) { acc, effect ->
                effect.applyTo(acc.wizard, acc.boss)
            }

            assertAll("wizard turn 2 after applying effects",
                { assertEquals(Boss22(points = 7, damage = 8), apply2w.boss) },
                { assertEquals(POISON, apply2w.effects.first().spell) },
                { assertEquals(4, apply2w.effects.first().timer) }
            )

            val cast2w = apply2w.wizard.cast(MAGIC_MISSILE, apply2w.boss)

            assertAll("boss turn 2 before applying effects",
                { assertEquals(Wizard(points = 2, mana = 24), cast2w.wizard) },
                { assertEquals(Boss22(points = 3, damage = 8), cast2w.boss) }
            )

            val apply2b = apply2w.effects.fold(cast2w) { acc, effect ->
                effect.applyTo(acc.wizard, acc.boss)
            }

            assertAll("boss turn 2 after applying effects",
                { assertEquals(POISON, apply2b.effects.first().spell) },
                { assertEquals(3, apply2b.effects.first().timer) }
            )

            assertTrue(apply2b.boss.isDead())
        }

        @Nested
        inner class `Magic Missile` {
            private val outcome = wizard.cast(MAGIC_MISSILE, boss)

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
                assertTrue(outcome.effects.isEmpty())
            }
        }

        @Nested
        inner class Drain {
            private val outcome = wizard.cast(DRAIN, boss)

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
                assertTrue(outcome.effects.isEmpty())
            }
        }

        @Nested
        inner class Shield {
            private val outcome = wizard.cast(SHIELD, boss)

            @Test
            fun `costs wizard 113 mana`() {
                assertEquals(-113, outcome.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 6 turns`() {
                assertTrue(outcome.effects.isNotEmpty())
                assertEquals(6, outcome.effects.first().timer)
            }

            @Test
            fun `does not affect boss when cast`() {
                assertEquals(boss, outcome.boss)
            }

            @Nested
            inner class `when Active during a turn`() {
                private val effect = Effect(spell = SHIELD, armor = 7, timer = 6)
                private val outcome = effect.applyTo(wizard, boss)

                @Test
                fun `gives wizard 7 armor`() {
                    assertEquals(7, outcome.wizard.armor)
                }

                @Test
                fun `decreases timer by 1`() {
                    assertEquals(-1, outcome.effects.first().timer - effect.timer)
                }
            }
        }

        @Nested
        inner class Poison {
            private val outcome = wizard.cast(POISON, boss)

            @Test
            fun `costs wizard 173 mana`() {
                assertEquals(-173, outcome.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 6 turns`() {
                assertTrue(outcome.effects.isNotEmpty())
                assertEquals(6, outcome.effects.first().timer)
            }

            @Test
            fun `does not immediately deal damage to boss when cast`() {
                assertEquals(boss, outcome.boss)
            }

            @Nested
            inner class `when Active during a turn`() {
                private val effect = Effect(spell = POISON, damage = 3, timer = 6)
                private val outcome = effect.applyTo(wizard, boss)

                @Test
                fun `deals 3 damage to boss`() {
                    assertEquals(-3, outcome.boss.points - boss.points)
                }

                @Test
                fun `decreases timer by 1`() {
                    assertEquals(-1, outcome.effects.first().timer - effect.timer)
                }
            }
        }

        @Nested
        inner class Recharge {
            private val outcome = wizard.cast(RECHARGE, boss)

            @Test
            fun `costs wizard 229 mana`() {
                assertEquals(-229, outcome.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 5 turns`() {
                assertTrue(outcome.effects.isNotEmpty())
                assertEquals(5, outcome.effects.first().timer)
            }

            @Test
            fun `does not affect boss when cast`() {
                assertEquals(boss, outcome.boss)
            }

            @Nested
            inner class `when Active during a turn`() {
                private val effect = Effect(spell = RECHARGE, recharge = 101, timer = 5)
                private val outcome = effect.applyTo(wizard, boss)

                @Test
                fun `gives 101 new mana to wizard`() {
                    assertEquals(101, outcome.wizard.mana - wizard.mana)
                }

                @Test
                fun `decreases timer by 1`() {
                    assertEquals(-1, outcome.effects.first().timer - effect.timer)
                }
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

        @ParameterizedTest(name = "armor: {0}, damage done: {1}")
        @CsvSource("8, 1", "10, 1", "7, 1", "6, 2", "5, 3")
        fun `deals at least 1 damage when wizard has armor`(armor: Int, expectedDamage: Int) {
            val boss = Boss22(damage = 8, points = irrelevantNonZero)
            val wizard = Wizard(points = 10, armor = armor, mana = irrelevantNonZero)

            val outcome = boss.attack(wizard)

            assertEquals(expectedDamage, wizard.points - outcome.wizard.points)
        }
    }
}