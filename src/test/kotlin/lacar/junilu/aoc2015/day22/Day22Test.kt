package lacar.junilu.aoc2015.day22

import lacar.junilu.aoc2015.day22.Spell.*
import lacar.junilu.readPuzzleInput
import lacar.junilu.toPropsMap
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

private object PuzzleInput {
    // using modified puzzle input format:
    // gmail = points: ??, damage: ??
    // github = points: ??, damage: ??
    val bossFor = readPuzzleInput("day22").associate { line ->
        val (bossId, bossProps) = line.split(" = ")
        val props = toPropsMap(bossProps, itemDelimiter = ", ", keyValueDelimiter = ": ", transform = String::toInt)

        bossId to Boss(points = props["points"]!!, damage = props["damage"]!!)
    }
}

class Day22Test {

    @Nested
    inner class Solution {
        private val wizard = Wizard(points = 50, mana = 500)
        private val bossGmail = PuzzleInput.bossFor["gmail"]!!
        private val bossGitHub = PuzzleInput.bossFor["github"]!!

        @Test
        fun `Part 1 - github`() {
            assertEquals(953, Day22(wizard, bossGitHub).cheapestWin())
        }

        @Test
        fun `Part 2 - github`() {
            assertEquals(1289, Day22(wizard, bossGitHub, PlayMode.HARD).cheapestWin())
        }

        @Test
        fun `Part 1 - gmail`() {
            assertEquals(1269, Day22(wizard, bossGmail).cheapestWin())
        }

        @Test
        fun `Part 2 - gmail`() {
            assertEquals(1309, Day22(wizard, bossGmail, PlayMode.HARD).cheapestWin())
        }
    }

    @Nested
    inner class SimpleFights {
        @Test
        fun `using nonfunctional approach to find winners`() {
            assertEquals(
                226,
                Day22(
                    wizard = Wizard(points = 10, mana = 250),
                    boss = Boss(points = 13, damage = 8)
                ).cheapestWin()
            )
        }
    }

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