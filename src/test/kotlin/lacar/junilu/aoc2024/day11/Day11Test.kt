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
            stones,        times, count, first5
            0 1 10 99 999, 1,      7,    1 2024 1 0 9
            125 17,         5,     13,    1036288 7 2 20 24
            125 17,         6,     22,    2097446912 14168 4048 2 0"""
    )
    fun `Example 1 - Part 1`(stones: String, times: Int, count: Int, first5: String) {
        val puzzle = Day11.using(stones)
        val listOfStones = puzzle.part1(times)
        listOfStones.println()
        assertAll(
            { assertEquals(count, listOfStones.size) },
            { assertEquals(first5, listOfStones.take(5).joinToString(" ")) }
        )
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(218956, puzzleUsingGmailInput.part1(25).size)
        assertEquals(186175, puzzleUsingGitHubInput.part1(25).size)
    }

    companion object {
        val puzzleUsingGitHubInput = Day11.using(readPuzzleInput("aoc2024/day11-gh").first())
        val puzzleUsingGmailInput = Day11.using(readPuzzleInput("aoc2024/day11-gm").first())
    }
}