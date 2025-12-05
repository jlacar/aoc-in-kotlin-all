package lacar.junilu.aoc2025.day01

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    private fun puzzle(fileName: String) = using(readPuzzleInput(fileName))

    @Test
    fun `GitHub account input - Parts 1 and 2`() {
        val puzzle = puzzle("aoc2025/day01-gh")
        assertEquals(989, puzzle.solvePart1())
        assertEquals(5941, puzzle.solvePart2())
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

    private val example = using(
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

    companion object {
        fun using(input: List<String>): Day01 {
            fun toOffset(line: String) = (if (line.first() == 'R') 1 else -1) * line.drop(1).toInt()
            return Day01(input.map { toOffset(it) })
        }
    }
}