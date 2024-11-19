package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

private val puzzleInputDay09 = readPuzzleInput("day09")

class Day09Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(141, Day09.using(puzzleInputDay09).shortestRoute())
        }

        @Test
        fun `Part 2 - SOLVED `() {
            assertEquals(736, Day09.using(puzzleInputDay09).longestRoute())
        }
    }

    @Nested
    inner class Examples {

        @Test
        fun `Shortest route`() {

            val segments = """
            London to Dublin = 464
            London to Belfast = 518
            Dublin to Belfast = 141
            """.trimIndent()

            assertEquals(605, Day09.using(segments.lines()).shortestRoute())
        }

        @TestFactory
        fun `Longest route`() {

            val segments = """
            London to Dublin = 464
            London to Belfast = 518
            Dublin to Belfast = 141
            """.trimIndent()

            assertEquals(982, Day09.using(segments.lines()).longestRoute())
        }
    }
}