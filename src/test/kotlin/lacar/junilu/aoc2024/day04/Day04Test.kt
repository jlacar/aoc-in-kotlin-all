package lacar.junilu.aoc2024.day04

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun `Solution - Part 1`() {
        assertEquals(2507, Day04.part1(puzzleInputGitHub))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(1969, Day04.part2(puzzleInputGitHub))
    }

    companion object {
        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day04-gh")
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

    @Test
    fun `column-wise traversal`() {
        val rows = """
            ABC
            ABC
            ABC
        """.trimIndent().lines()

        val cols = """
            AAA
            BBB
            CCC
        """.trimIndent().lines()

        assertEquals(cols, Day04.columns(rows))
    }
}