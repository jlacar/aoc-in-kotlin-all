package lacar.junilu.aoc2015.day22

import lacar.junilu.aoc2015.day22.Spell.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class Day22Test {

//    @Disabled
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - `() {
            assertEquals(
                0,  // 985 < ? < 1006  - google 1256
                Day22(
                    Wizard(points = 50, mana = 500),
//                    Boss(points = 58, damage = 9), // @gmail
                    Boss(points = 55, damage = 8), // @github
                ).cheapestWin()
            )
        }
    }

//    @Disabled
    @Nested
    inner class SimpleFights {
        @Test
        fun `new approach`() {
            assertEquals(
                0,
                Day22(
                    wizard = Wizard(points = 10, mana = 250),
                    boss = Boss(points = 13, damage = 8)
                ).cheapestWin()
            )
        }
    }

//    @Nested
//    inner class Spells {
//        private val boss = Boss(points = 13, damage = 8)
//        private val wizard = Wizard(points = 10, mana = 250)
//        private val fight = Fight(wizard, boss)
//
//        @Nested
//        inner class `Magic Missile` {
//            private val outcome = fight.cast(MAGIC_MISSILE)
//
//            @Test
//            fun `costs wizard 53 mana`() {
//                assertEquals(53, wizard.mana - outcome.wizard.mana)
//            }
//
//            @Test
//            fun `instantly does 4 damage to boss`() {
//                assertEquals(4, boss.points - outcome.boss.points)
//            }
//
//            @Test
//            fun `does not start a timed effect`() {
//                assertFalse(outcome.hasActive(MAGIC_MISSILE))
//            }
//        }
//
//        @Nested
//        inner class Drain {
//            private val outcome = fight.cast(DRAIN)
//
//            @Test
//            fun `costs wizard 73 mana`() {
//                assertEquals(-73, outcome.wizard.mana - wizard.mana)
//            }
//
//            @Test
//            fun `heals wizard for 2 hit points`() {
//                assertEquals(2, outcome.wizard.points - wizard.points)
//            }
//
//            @Test
//            fun `does not start a timed effect`() {
//                assertFalse(outcome.hasActive(DRAIN))
//            }
//        }
//
//        @Nested
//        inner class Shield {
//            private val afterCast = fight.cast(SHIELD)
//
//            @Test
//            fun `costs wizard 113 mana`() {
//                assertEquals(-113, afterCast.wizard.mana - wizard.mana)
//            }
//
//            @Test
//            fun `starts an effect that lasts for 6 turns`() {
//                assertTrue(afterCast.hasActive(SHIELD, 6))
//            }
//
//            @Test
//            fun `does not affect boss when cast`() {
//                assertEquals(boss, afterCast.boss)
//            }
//
//            @Nested
//            inner class `When boss attacks`() {
//                private val afterAttack = afterCast.attack()
//
//                @Test
//                fun `gives wizard 7 armor`() {
//                    assertEquals(7, afterAttack.wizard.armor)
//                }
//
//                @Test
//                fun `does not affect boss`() {
//                    assertEquals(afterCast.boss, afterAttack.boss)
//                }
//
//                @Test
//                fun `decreases its timer`() {
//                    assertTrue(afterAttack.hasActive(SHIELD, 5))
//                }
//            }
//        }
//
//        @Nested
////        inner class Poison {
////            private val afterCast = fight.cast(POISON)
////
////            @Test
////            fun `costs wizard 173 mana`() {
////                assertEquals(-173, afterCast.wizard.mana - wizard.mana)
////            }
////
////            @Test
////            fun `starts an effect that lasts for 6 turns`() {
////                assertTrue(afterCast.hasActive(POISON, 6))
////            }
////
////            @Test
////            fun `does not immediately deal damage to boss when cast`() {
////                assertEquals(boss, afterCast.boss)
////            }
////
////            @Nested
////            inner class `When boss attacks`() {
////                private val afterAttack = afterCast.attack()
////
////                @Test
////                fun `deals 3 damage to boss`() {
////                    assertEquals(-3, afterAttack.boss.points - boss.points)
////                }
////
////                @Test
////                fun `decreases timer to 5`() {
////                    assertTrue(afterAttack.hasActive(POISON, 5))
////                }
////            }
////        }
//
//        @Nested
////        inner class Recharge {
////            private val afterCast1 = fight.cast(RECHARGE)
////
////            @Test
////            fun `costs wizard 229 mana`() {
////                assertEquals(-229, afterCast1.wizard.mana - wizard.mana)
////            }
////
////            @Test
////            fun `starts an effect that lasts for 5 turns`() {
////                assertTrue(afterCast1.hasActive(RECHARGE, 5))
////            }
////
////            @Test
////            fun `does not affect boss when cast`() {
////                assertEquals(boss, afterCast1.boss)
////            }
////
////            @Nested
////            inner class `Lasts 5 turns then wears off` {
////                val afterAttack1 = afterCast1.attack()
////                val afterCast2 = afterAttack1.cast(DRAIN)
////                val afterAttack2 = afterCast2.attack()
////                val afterCast3 = afterAttack2.cast(DRAIN)
////                val afterAttack3 = afterCast3.attack()
////                val afterCast4 = afterAttack3.cast(DRAIN)
////
////                @Test
////                fun `timer reduced on every turn`() {
////                    assertAll(
////                        { assertTrue(afterCast1.hasActive(RECHARGE, 5)) },
////                        { assertTrue(afterAttack1.hasActive(RECHARGE, 4)) },
////                        { assertTrue(afterCast2.hasActive(RECHARGE, 3)) },
////                        { assertTrue(afterAttack2.hasActive(RECHARGE, 2)) },
////                        { assertTrue(afterCast3.hasActive(RECHARGE, 1)) },
////                        { assertFalse(afterAttack3.hasActive(RECHARGE)) }
////                    )
////                }
////
////                @Test
////                fun `adds 101 new mana on every turn while active`() {
////                    assertAll(
////                        { assertEquals(21, afterCast1.wizard.mana) },
////                        { assertEquals(101 + 21, afterAttack1.wizard.mana) },
////                        { assertEquals(101 + 122 - DRAIN.cost, afterCast2.wizard.mana) },
////                        { assertEquals(101 + 150, afterAttack2.wizard.mana) },
////                        { assertEquals(101 + 251 - DRAIN.cost, afterCast3.wizard.mana) },
////                        { assertEquals(101 + 279, afterAttack3.wizard.mana) },
////                        { assertEquals(380 - DRAIN.cost, afterCast4.wizard.mana) }
////                    )
////                }
////            }
////        }
//    }

//    @Nested
//    inner class `Part 1 Example 1 scenarios` {
//
//        val initialState = Fight(
//            wizard = Wizard(points = 10, mana = 250),
//            boss = Boss(points = 13, damage = 8)
//        )
//        val wizardTurn1 = initialState.cast(POISON)
//
//        @Nested
//        inner class `Wizard turn 1`() {
//            @Test
//            fun `starts poison effect for 6 turns`() {
//                wizardTurn1.hasActive(POISON, 6)
//            }
//
//            @Test
//            fun `wizard has 10 hit points, 77 mana, 0 armor`() {
//                assertEquals(Wizard(points = 10, mana = 77, spent = POISON.cost), wizardTurn1.wizard)
//            }
//
//            @Test
//            fun `boss not been damaged yet`() {
//                assertEquals(initialState.boss, wizardTurn1.boss)
//            }
//        }
//
//        val bossTurn1 = wizardTurn1.attack()
//
//        @Nested
//        inner class `Boss turn 1` {
//            @Test
//            fun `boss takes 3 damage from poison`() {
//                assertEquals(10, bossTurn1.boss.points)
//            }
//
//            @Test
//            fun `wizard takes 8 damage from boss attack`() {
//                assertEquals(2, bossTurn1.wizard.points)
//            }
//
//            @Test
//            fun `poison timer is now 5`() {
//                assertTrue(bossTurn1.hasActive(POISON, 5))
//            }
//        }
//
//        val wizardTurn2 = bossTurn1.cast(MAGIC_MISSILE)
//
//        @Nested
//        inner class `Wizard turn 2` {
//            @Test
//            fun `wizard has 2 hit points and 24 mana`() {
//                assertEquals(
//                    Wizard(points = 2, mana = 24, spent = POISON.cost + MAGIC_MISSILE.cost),
//                    wizardTurn2.wizard
//                )
//            }
//
//            @Test
//            fun `poison timer is now 4`() {
//                assertTrue(wizardTurn2.hasActive(POISON, 4))
//            }
//
//            @Test
//            fun `boss takes 3 damage from poison and 4 instant damage from Magic Missile`() {
//                assertEquals(3, wizardTurn2.boss.points)
//            }
//        }
//
//        val bossTurn2 = wizardTurn2.attack()
//
//        @Nested
//        inner class `Boss turn 2` {
//            @Test
//            fun `wizard has 2 hit points and 24 mana`() {
//                assertEquals(wizardTurn2.wizard.copy(points = 2, mana = 24), bossTurn2.wizard)
//            }
//
//            @Test
//            fun `boss is dead from poison and wizard wins`() {
//                assertTrue(bossTurn2.boss.isDead())
//                assertTrue(bossTurn2.wizardWins())
//            }
//
//            @Test
//            fun `win cost Magic Missile plus Poison costs`() {
//                assertEquals(MAGIC_MISSILE.cost + POISON.cost, bossTurn2.cost)
//            }
//        }
//    }

//    @Nested
//    inner class `Part 1 Example 2 scenarios` {
//        val initialState = Fight(
//            wizard = Wizard(points = 10, mana = 250),
//            boss = Boss(points = 14, damage = 8)
//        )
//
//        val wizardTurn1 = initialState.cast(RECHARGE)
//        val bossTurn1 = wizardTurn1.attack()
//        val wizardTurn2 = bossTurn1.cast(SHIELD)
//        val bossTurn2 = wizardTurn2.attack()
//        val wizardTurn3 = bossTurn2.cast(DRAIN)
//        val bossTurn3 = wizardTurn3.attack()
//        val wizardTurn4 = bossTurn3.cast(POISON)
//        val bossTurn4 = wizardTurn4.attack()
//        val wizardTurn5 = bossTurn4.cast(MAGIC_MISSILE)
//        val bossTurn5 = wizardTurn5.attack()
//
//        @Nested
//        inner class `After boss turn 4` {
//            @Test
//            fun `wizard has 1 point 7 armor 167 mana`() {
//                assertEquals(wizardTurn4.wizard.copy(points = 1, armor = 7, mana = 167), bossTurn4.wizard)
//            }
//
//            @Test
//            fun `boss has 9 hit points`() {
//                assertEquals(9, bossTurn4.boss.points)
//            }
//
//        }
//
//        @Nested
//        inner class `After wizard turn 5` {
//            @Test
//            fun `shield effect ends`() {
//                assertFalse(wizardTurn5.hasActive(SHIELD))
//            }
//
//            @Test
//            fun `poison timer is 4`() {
//                assertTrue(wizardTurn5.hasActive(POISON, 4))
//            }
//
//            @Test
//            fun `boss has 2 hit points left`() {
//                assertEquals(2, wizardTurn5.boss.points)
//            }
//
//            @Test
//            fun `wizard has 114 mana left`() {
//                assertEquals(114, wizardTurn5.wizard.mana)
//            }
//
//            @Test
//            fun `wizard has 0 armor`() {
//                assertEquals(0, wizardTurn5.wizard.armor)
//            }
//        }
//
//        @Nested
//        inner class `After boss turn 5` {
//            @Test
//            fun `wizard wins`() {
//                assertTrue(bossTurn5.wizardWins())
//            }
//
//            @Test
//            fun `wizard spent 641 and has 1 hit point and 114 mana left`() {
//                assertAll(
//                    { assertEquals(641, bossTurn5.wizard.spent) },
//                    { assertEquals(1, bossTurn5.wizard.points) },
//                    { assertEquals(114, bossTurn5.wizard.mana) }
//                )
//            }
//        }
//    }

    @Nested
    inner class `Rules of Engagement` {
        private val boss = Boss(points = 13, damage = 8)
        private val wizard = Wizard(points = 10, mana = 250)

        @Test
        fun `wizard with enough mana can cast any spell when no active spells`() {
            val fight = Fight(wizard, boss, spells = emptyList())

            assertEquals(Spell.entries, fight.availableSpells())
        }

        @Test
        fun `wizard can only cast spells they can afford`() {
            val lessThanRechargeCost = RECHARGE.cost - 1
            val spellsWizardCanCast = Fight(
                wizard = Wizard(mana = lessThanRechargeCost, points = 10),
                boss = boss,
                spells = emptyList()
            ).availableSpells()

            assertFalse(spellsWizardCanCast.contains(RECHARGE))
        }

        @Test
        fun `wizard loses when they cannot afford to cast any spells`() {
            val cheapest = Spell.entries.minOf { it.cost }
            val poorWizard = wizard.copy(mana = cheapest - 1)
            val fight = Fight(poorWizard, boss)

            assertTrue(fight.wizardCantCast())
        }

        @Test
        fun `wizard can cast a spell that is ending`() {
            val fight = Fight(wizard, boss, spells = listOf(ActiveSpell(POISON, 1)))

            assertTrue(fight.availableSpells().contains(POISON))
        }

        @Test
        fun `wizard can cast any affordable spell that is not active or is ending`() {
            val fight = Fight(
                wizard = Wizard(mana = 180, armor = 7, points = 10),
                boss = boss,
                spells = listOf(ActiveSpell(POISON, 1), ActiveSpell(SHIELD, 2)),
            )
            val availableSpells = fight.availableSpells()

            assertAll(
                // can cast these
                { assertTrue(availableSpells.contains(MAGIC_MISSILE)) },
                { assertTrue(availableSpells.contains(DRAIN)) },
                { assertTrue(availableSpells.contains(POISON)) },
                // can't cast these
                { assertFalse(availableSpells.contains(SHIELD)) },  // still active
                { assertFalse(availableSpells.contains(RECHARGE)) } // not enough mana
            )
        }

        @Test
        fun `wizard cannot cast spells that are not ending`() {
            val fight = Fight(wizard, boss, spells = listOf(ActiveSpell(POISON, 2)))

            assertFalse(fight.availableSpells().contains(POISON))
        }

        @ParameterizedTest(name = "boss with {0} points is dead")
        @ValueSource(ints = [0, -1])
        fun `wizard wins when boss is dead`(bossPoints: Int) {
            val fight = Fight(wizard, boss.copy(points = bossPoints))

            assertTrue(fight.wizardWins())
        }
    }
}