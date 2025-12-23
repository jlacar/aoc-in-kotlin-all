package lacar.junilu.aoc2025.day01

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun `Solutions for GitHub input`() {
        val solution = using(readPuzzleLines("aoc2025/day01-gh"))
        assertEquals(989, solution.zeroesInCombination())
        assertEquals(5941, solution.timesTurnedToZero())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val solution = using(readPuzzleLines("aoc2025/day01-gm"))
        assertEquals(1031, solution.zeroesInCombination())
        assertEquals(5831, solution.timesTurnedToZero())
    }

    @Test
    fun `Examples - Parts 1 and 2`() {
        val example = using(
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

        assertEquals(3, example.zeroesInCombination())
        assertEquals(6, example.timesTurnedToZero())
    }
}

private fun using(input: List<String>): Day01 {
    fun toOffset(line: String) = (if (line.first() == 'R') 1 else -1) * line.drop(1).toInt()
    return Day01(input.map { toOffset(it) })
}
