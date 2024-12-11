package lacar.junilu.aoc2024.day11

import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {
    @ParameterizedTest
    @CsvSource(
        useHeadersInDisplayName = true,
        textBlock = """
            stones,        times, expectCount
            0 1 10 99 999, 1,      7   
            125 17,         5,     13
            125 17,         6,     22"""
    )
    fun `Example 1 - Part 1`(stones: String, times: Int, expectedCount: Long) {
        val puzzle = Day11.using(stones)
        assertEquals(expectedCount, puzzle.part1(times))
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(218956L, puzzleUsingGmailInput.part1(25))
        assertEquals(186175L, puzzleUsingGitHubInput.part1(25))
    }

    @Test
    fun `Solution - Part 2`() {
//        assertEquals(218956, puzzleUsingGmailInput.part1(75))
        assertEquals(0L, puzzleUsingGitHubInput.part1(75))
    }

    companion object {
        val puzzleUsingGitHubInput = Day11.using(readPuzzleInput("aoc2024/day11-gh").first())
        val puzzleUsingGmailInput = Day11.using(readPuzzleInput("aoc2024/day11-gm").first())
    }
}