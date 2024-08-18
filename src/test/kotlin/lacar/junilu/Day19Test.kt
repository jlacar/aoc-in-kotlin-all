package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInput = readResource("day19")

class Day19Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(535, Day19.using(puzzleInput).part1())
        }

        @Test
        fun part2() {
            assertEquals(212, Day19.using(puzzleInput).part2())
        }
    }

    @Nested
    inner class Examples {
        @Test
        fun `Number of new molecules that can be produced by a single replacement`() {
            assertEquals(7, Day19.using(
                """
                    H => HO
                    H => OH
                    O => HH
                    
                    HOHOHO
                """.trimIndent().lines()
            ).part1())
        }

        @Test
        fun `Fewest steps to create molecule from e`() {
            assertEquals(6, Day19.using(
                """
                    e => H
                    e => O
                    H => HO
                    H => OH
                    O => HH
                    
                    HOHOHO
                """.trimIndent().lines()
            ).part2())
        }
    }
}