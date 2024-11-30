package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInputDay19 = readPuzzleInput("aoc2015/day19-gm")
private val puzzleInputDay19Gh = readPuzzleInput("aoc2015/day19-gh")

class Day19Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(535, Day19.using(puzzleInputDay19).newMoleculesWithSingleReplacement())
            assertEquals(518, Day19.using(puzzleInputDay19Gh).newMoleculesWithSingleReplacement())
        }

        @Test
        fun part2() {
            assertEquals(212, Day19.using(puzzleInputDay19).fewestStepsFromE())
            assertEquals(200, Day19.using(puzzleInputDay19Gh).fewestStepsFromE())
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
            ).newMoleculesWithSingleReplacement())
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
            ).fewestStepsFromE())
        }
    }
}