package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

private val puzzleInput = readResource("day09")

class Day09Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(141, Day09.using(puzzleInput).shortestRoute())
        }

        @Test
        fun `Part 2 - SOLVED `() {
            assertEquals(736, Day09.using(puzzleInput).longestRoute())
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