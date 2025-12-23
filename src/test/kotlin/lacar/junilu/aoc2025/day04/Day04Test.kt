package lacar.junilu.aoc2025.day04

import lacar.junilu.readPuzzleLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `Solutions for Github input`() {
        val solution = Day04(readPuzzleLines("aoc2025/day04-gh"))

        assertEquals(1395, solution.part1())
        assertEquals(8451, solution.part2())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val solution = Day04(readPuzzleLines("aoc2025/day04-gm"))

        assertEquals(1370, solution.part1())
        assertEquals(8437, solution.part2())
    }

    @Test
    fun `Examples - Parts 1 & 2`() {
        val example = Day04(
            """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.""".trimIndent().lines()
        )

        assertEquals(13, example.part1())
        assertEquals(43, example.part2())
    }
}