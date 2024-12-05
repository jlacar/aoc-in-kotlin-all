package lacar.junilu.aoc2024.day04

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun `Solution - Part 1`() {
        assertEquals(2507, Day04.part1(puzzleInputGitHub))
        assertEquals(2593, Day04.part1(puzzleInputGmail))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(1969, Day04.part2(puzzleInputGitHub))
        assertEquals(1950, Day04.part2(puzzleInputGmail))
    }

    companion object {
        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day04-gh")
        private val puzzleInputGmail = readPuzzleInput("aoc2024/day04-gm")
    }

    @Test
    fun `Example - Part 1 & 2`() {
        val example1Input = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX""".trimIndent().lines()

        assertEquals(18, Day04.part1(example1Input))
        assertEquals(9, Day04.part2(example1Input))
    }

    @Test
    fun `Small X-MAS`() {
        val test = """
            M.S..
            .A...
            M.S..
            .A...
            M.S..
        """.trimIndent().lines()
        assertEquals(2, Day04.part2(test))
    }
}