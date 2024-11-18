package lacar.junilu.aoc2015.day22

import lacar.junilu.aoc2015.day22.Spell.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SolutionTest {

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
                private val afterAttack = afterCast.applyActiveSpells().attack()

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

        @Nested
        inner class Poison {
            private val afterCast = fight.cast(POISON)

            @Test
            fun `costs wizard 173 mana`() {
                assertEquals(-173, afterCast.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 6 turns`() {
                assertTrue(afterCast.hasActive(POISON, 6))
            }

            @Test
            fun `does not immediately deal damage to boss when cast`() {
                assertEquals(boss, afterCast.boss)
            }

            @Nested
            inner class `When boss attacks`() {
                private val afterAttack = afterCast.applyActiveSpells().attack()

                @Test
                fun `deals 3 damage to boss`() {
                    assertEquals(-3, afterAttack.boss.points - boss.points)
                }

                @Test
                fun `decreases timer to 5`() {
                    assertTrue(afterAttack.hasActive(POISON, 5))
                }
            }
        }

        @Nested
        inner class Recharge {
            private val afterCast = fight.cast(RECHARGE)

            @Test
            fun `costs wizard 229 mana`() {
                assertEquals(-229, afterCast.wizard.mana - wizard.mana)
            }

            @Test
            fun `starts an effect that lasts for 5 turns`() {
                assertTrue(afterCast.hasActive(RECHARGE, 5))
            }

            @Test
            fun `does not affect boss when cast`() {
                assertEquals(boss, afterCast.boss)
            }

            @Nested
            inner class `When boss attacks`() {
                private val afterAttack = afterCast.applyActiveSpells().attack()

                @Test
                fun `wizard gains 101 mana`() {
                    assertEquals(101, afterAttack.wizard.mana - afterCast.wizard.mana)
                }

                @Test
                fun `wizard loses hit points`() {
                    assertEquals(afterCast.boss.damage, afterCast.wizard.points - afterAttack.wizard.points)
                }

                @Test
                fun `decreases timer to 4`() {
                    assertTrue(afterAttack.hasActive(RECHARGE, 4))
                }
            }
        }
    }

    @Nested
    inner class `Part 1 Example 1 scenarios` {

        val initialState = Fight(
            wizard = Wizard(points = 10, mana = 250),
            boss = Boss(points = 13, damage = 8)
        )
        val wizardTurn1 = initialState.cast(POISON)

        @Nested
        inner class `Wizard turn 1`() {
            @Test
            fun `starts poison effect for 6 turns`() {
                wizardTurn1.hasActive(POISON, 6)
            }

            @Test
            fun `wizard has 10 hit points, 77 mana, 0 armor`() {
                assertEquals(Wizard(points = 10, mana = 77), wizardTurn1.wizard)
            }

            @Test
            fun `boss not been damaged yet`() {
                assertEquals(initialState.boss, wizardTurn1.boss)
            }
        }

        val bossTurn1 = wizardTurn1.applyActiveSpells().attack()

        @Nested
        inner class `Boss turn 1` {
            @Test
            fun `boss takes 3 damage from poison`() {
                assertEquals(10, bossTurn1.boss.points)
            }

            @Test
            fun `wizard takes 8 damage from boss attack`() {
                assertEquals(2, bossTurn1.wizard.points)
            }

            @Test
            fun `poison timer is now 5`() {
                assertTrue(bossTurn1.hasActive(POISON, 5))
            }
        }

        val wizardTurn2 = bossTurn1.applyActiveSpells().cast(MAGIC_MISSILE)

        @Nested
        inner class `Wizard turn 2` {
            @Test
            fun `wizard has 2 hit points and 24 mana`() {
                assertEquals(Wizard(points = 2, mana = 24), wizardTurn2.wizard)
            }

            @Test
            fun `poison timer is now 4`() {
                assertTrue(wizardTurn2.hasActive(POISON, 4))
            }

            @Test
            fun `boss takes 3 damage from poison and 4 instant damage from Magic Missile`() {
                assertEquals(3, wizardTurn2.boss.points)
            }
        }

        val bossTurn2 = wizardTurn2.applyActiveSpells().attack()

        @Nested
        inner class `Boss turn 2` {
            @Test
            fun `wizard has 2 hit points and 24 mana`() {
                assertEquals(Wizard(points = 2, mana = 24), bossTurn2.wizard)
            }

            @Test
            fun `boss is dead from poison and wizard wins`() {
                assertTrue(bossTurn2.boss.isDead())
                assertTrue(bossTurn2.wizardWins())
            }

            @Test
            fun `win cost Magic Missile plus Poison costs`() {
                assertEquals(MAGIC_MISSILE.cost + POISON.cost, bossTurn2.cost())
            }
        }
    }

    @Nested
    inner class `Rules of Engagement` {
        private val boss = Boss(points = 13, damage = 8)
        private val wizard = Wizard(points = 10, mana = 250)

        @Test
        fun `wizard has enough buy any spell`() {
            val fight = Fight(wizard, boss)

            assertEquals(Spell.entries, fight.spellsAvailableToCast())
        }

        @Test
        fun `wizard loses when cannot afford to cast any spells`() {
            val poorWizard = wizard.copy(mana = 5)
            val fight = Fight(poorWizard, boss)

            assertTrue(fight.wizardLoses())
        }

        @Test
        fun `wizard cannot cast spells that are still in effect`() {
            val fight = Fight(wizard, boss, spells = listOf(Pair(POISON, 1)))

            assertFalse(fight.spellsAvailableToCast().contains(POISON))
        }

        @Test
        fun `wizard can cast a spell again if it is not in effect`() {
            val fight = Fight(wizard, boss, spells = listOf(Pair(POISON, 0)))

            assertTrue(fight.spellsAvailableToCast().contains(POISON))
        }

        @ParameterizedTest(name = "boss with {0} points is dead")
        @ValueSource(ints = [0, -1])
        fun `wizard wins when boss is dead`(bossPoints: Int) {
            val fight = Fight(wizard, boss.copy(points = bossPoints))

            assertTrue(fight.wizardWins())
        }
    }
}