package lacar.junilu.aoc2025.day03

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `Github input solutions`() {
        val puzzle = Day03(readPuzzleInput("aoc2025/day03-gh"))

        assertEquals(17278L, puzzle.part1())
        assertEquals(171528556468625L, puzzle.part2())
    }

    @Test
    fun `Examples - Parts 1 & 2`() {
        val example = Day03(
            """
                    987654321111111
                    811111111111119
                    234234234234278
                    818181911112111""".trimIndent().lines()
        )

        assertEquals(357L, example.part1())
        assertEquals(3121910778619L, example.part2())
    }
}