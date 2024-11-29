package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private fun bossDay21(fileName: String): Player {
    val bossProperties = toPropsMap(readPuzzleInput(fileName).first(),
        itemDelimiter = ", ",
        keyValueDelimiter = ": ",
        transform = String::toInt)

    return Player(
        points = bossProperties.getOrDefault("Points", 0),
        damage = bossProperties.getOrDefault("Damage", 0),
        armor = bossProperties.getOrDefault("Armor", 0)
    )
}

class Day21Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(91, Day21(bossDay21("day21")).leastAmountOfGoldSpentToWin())
            assertEquals(121, Day21(bossDay21("day21-gh")).leastAmountOfGoldSpentToWin())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(158, Day21(bossDay21("day21")).mostAmountOfGoldSpentJustToLose())
            assertEquals(201, Day21(bossDay21("day21-gh")).mostAmountOfGoldSpentJustToLose())
        }
    }

    @Test
    fun `Part 1 - example where player barely wins`() {
        assertTrue(
            RolePlayingGame(
                player = Player(points = 8, damage = 8, armor = 5),
                boss = Player(points = 12, damage = 7, armor = 2)
            )
            .playerWins()
        )
    }

    @Test
    fun `Debug player wins`() {
        assertTrue(
            RolePlayingGame(
                player = Player(points = 100, damage = 5, armor = 5),
                boss = Player(points = 100, damage = 8, armor = 2)
            )
            .playerWins()
        )
    }
}