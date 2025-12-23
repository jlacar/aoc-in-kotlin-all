package lacar.junilu.aoc2025.day03

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day03Test {
    @Test
    fun `Solutions for Github input`() {
        val solution = Day03(readPuzzleLines("aoc2025/day03-gh"))

        assertEquals(17278L, solution.part1())
        assertEquals(171528556468625L, solution.part2())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val solution = Day03(readPuzzleLines("aoc2025/day03-gm"))

        assertEquals(17107L, solution.part1())
        assertEquals(169349762274117L, solution.part2())
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