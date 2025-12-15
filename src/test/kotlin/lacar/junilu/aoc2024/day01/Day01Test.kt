package lacar.junilu.aoc2024.day01

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `Solution - Part 1 - total distance`() {
        assertEquals(2285373, usingInputForGithub.totalDistance())
        assertEquals(2031679, usingInputForGmail.totalDistance())
    }

    @Test
    fun `Solution - Part 2 - total similarity`() {
        assertEquals(21142653, usingInputForGithub.totalSimilarity())
        assertEquals(19678534, usingInputForGmail.totalSimilarity())
    }

    @Test
    fun `Example - Part 1 and Part 2`() {
        assertEquals(11, usingExampleInput.totalDistance())
        assertEquals(31, usingExampleInput.totalSimilarity())
    }

    companion object {
        private val usingInputForGithub = Day01.using(readPuzzleLines("aoc2024/day01-gh"))
        private val usingInputForGmail = Day01.using(readPuzzleLines("aoc2024/day01-gm"))

        private val usingExampleInput = Day01.using(
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """.trimIndent().lines()
        )
    }
}