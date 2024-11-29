package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInputDay17 = readPuzzleInput("day17")
private val puzzleInputDay17Gh = readPuzzleInput("day17-gh")

class Day17Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(1638, Day17.using(puzzleInputDay17, 150).waysToFill())
            assertEquals(1304, Day17.using(puzzleInputDay17Gh, 150).waysToFill())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(17, Day17.using(puzzleInputDay17, 150).waysToFillFewestContainers())
            assertEquals(18, Day17.using(puzzleInputDay17Gh, 150).waysToFillFewestContainers())
        }
    }

    @Nested
    inner class Examples {

        private val containers = listOf(20, 15, 10, 5, 5)

        @Test
        fun `Ways to fill containers`() {
            assertEquals(4, Day17(containers, 25).waysToFill())
        }

        @Test
        fun `Ways to fill with fewest containers`() {
            assertEquals(3, Day17(containers, 25).waysToFillFewestContainers())
        }
    }
}