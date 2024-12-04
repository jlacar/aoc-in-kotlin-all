package lacar.junilu.aoc2024.day04

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day04Test {

    private val example1Input = """
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

    @Test
    fun `Example - Part 1`() {
        assertEquals(18, Day04.xmasCount(example1Input))
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(2507, Day04.xmasCount(puzzleInputGitHub))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(0, Day04.part2(puzzleInputGitHub))
    }

    @Test
    fun `Example - Part 2`() {
        val test = """
            M.S..
            .A...
            M.S..
            .A...
            M.S..
        """.trimIndent().lines()
        assertEquals(9, Day04.part2(example1Input))
    }

    @Test
    fun column() {
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

    companion object {
        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day04-gh")
    }
}