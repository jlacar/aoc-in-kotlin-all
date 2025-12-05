package lacar.junilu.aoc2025.day05

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `Example - Part 1 & 2`() {
        val input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32""".trimIndent()

        val example = Day05.using(input.lines())

        assertEquals(3, example.part1())
        assertEquals(14, example.part2())
    }

    @Test
    fun `Github input solutions`() {
        val puzzle = Day05.using(readPuzzleInput("aoc2025/day05-gh"))

        assertEquals(840, puzzle.part1())
        assertEquals(359913027576322L, puzzle.part2())    }
}