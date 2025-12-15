package lacar.junilu.aoc2025.day04

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `Github input solutions`() {
        val puzzle = Day04(readPuzzleLines("aoc2025/day04-gh"))

        assertEquals(1395, puzzle.part1())
        assertEquals(8451, puzzle.part2())
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