package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInput = readResource("day19")

class Day19Test {

    @Nested
    inner class Samples {
        private val sampleInput = """
            H => HO
            H => OH
            O => HH
            
            HOHOHO
        """.trimIndent().lines()

        @Test
        fun `Part 1 example`() {
            assertEquals(7, Day19.using(sampleInput).part1())
        }
    }

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 -`() {
            assertEquals(535, Day19.using(puzzleInput).part1())
        }
    }
}