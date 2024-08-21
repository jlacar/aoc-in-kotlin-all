package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day21Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - `() {
            assertEquals(1, Day21().leastAmountOfGoldSpentToWin())
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
}