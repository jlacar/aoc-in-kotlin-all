package lacar.junilu.aoc2025.day01

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class Day01Test {
    @ParameterizedTest
    @CsvSource(
        "9, 0, -999",
        "9, 49, -948",
        "10, 48, -948",
        "10, 47, -948",
        "0, 52, 47",
        "1, 52, 48",
        "1, 52, 49",
        "1, 52, 147",
        "2, 52, 148",
        "0, 52, -51",
        "1, 52, -52",
        "1, 52, -151",
        "2, 52, -152",
        "1, 14, -82",
        "10,50, 1000",
        "1,  0, 100",
        "2,  0, 200",
        "1,  1, 100",
        "2,  1, -101",
        "1,  4, -5",
        "0,  0, -1",
        "0,  0, 99",
        "1,  0, 100",
        "1,  0, -100",
        "2,  0, 200",
        "2,  0, 299",
        "2,  0, -200",
        "2,  0, -299",
    )
    fun `Times pointed at zero`(expected: Int, starting: Int, rotation: Int) {
        assertEquals(expected, Day01.zeroCount(starting, rotation))
    }

    private fun puzzle(fileName: String) = Day01.using(readPuzzleInput(fileName))

    @Test
    fun `GitHub account input - Parts 1 and 2`() {
        val puzzle = puzzle("aoc2025/day01-gh")
        assertEquals(989, puzzle.solvePart1())
        assertEquals(5941, puzzle.solvePart2()) // too high
    }

    @Test
    fun `GMail account input - Parts 1 and 2`() {
        val puzzle = puzzle("aoc2025/day01-gm")
        assertEquals(1031, puzzle.solvePart1())
        assertEquals(5831, puzzle.solvePart2())
    }

    @Test
    fun `Examples - Parts 1 and 2`() {
        assertEquals(3, example.solvePart1())
        assertEquals(6, example.solvePart2())
    }

    private val example = Day01.using(
        """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82""".trimIndent().lines()
    )
}