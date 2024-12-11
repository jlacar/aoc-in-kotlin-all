package lacar.junilu.aoc2024.day10

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun `Examples - Part 1`() {
        assertEquals(2, Day10.using(egScoreOf2).part1())
        assertEquals(4, Day10.using(egScoreOf4).part1())
        assertEquals(36, Day10.using(egScoreOf36).part1())
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(841, day10UsingGmailInput.part1())
        assertEquals(0, day10UsingGitHubInput.part1())
    }

    companion object {
        private val day10UsingGmailInput = Day10.using(readPuzzleInput("aoc2024/day10-gm"))
        private val day10UsingGitHubInput = Day10.using(readPuzzleInput("aoc2024/day10-gh"))

        private val egScoreOf2 = """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
            """.trimIndent().lines()

        private val egScoreOf4 = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
            """.trimIndent().lines()

        private val egScoreOf36 = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """.trimIndent().lines()
    }
}