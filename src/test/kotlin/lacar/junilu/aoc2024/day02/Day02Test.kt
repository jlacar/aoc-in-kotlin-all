package lacar.junilu.aoc2024.day02

import lacar.junilu.experimental.Puzzle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun `Solution - Part 1 Puzzle DSL`() {
        assertEquals(516, usingGitHubInput.solve(Day02.part1))
        assertEquals(282, usingGmailInput.solve(Day02.part1))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(561, usingGitHubInput.solve(Day02.part2))
        assertEquals(349, usingGmailInput.solve(Day02.part2))
    }

    @Test
    fun `Example - Part 1 & 2`() {
        assertEquals(2, usingExampleInput.solve(Day02.part1))
        assertEquals(4, usingExampleInput.solve(Day02.part2))
    }

    companion object {
        private val exampleInput = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """.trimIndent()

        private val usingGitHubInput = Puzzle.using("aoc2024/day02-gh")
        private val usingGmailInput = Puzzle.using("aoc2024/day02-gm")
        private val usingExampleInput = Puzzle.usingText(exampleInput)
    }
}