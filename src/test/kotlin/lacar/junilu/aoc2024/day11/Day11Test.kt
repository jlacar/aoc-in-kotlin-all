package lacar.junilu.aoc2024.day11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {
    @ParameterizedTest
    @CsvSource(
        useHeadersInDisplayName = true,
        textBlock = """
        stones,        times, count, first5
        125 7,         5,    13,     1036288 7 2 20 24
        125 7,         6,    22,     2097446912 14168 4048 2 0
        0 1 10 99 999, 1,     7,     1 2024 1 0 9"""
    )
    fun `Example 1 - Part 1`(stones: String, times: Int, count: Int, first5: String) {
        val puzzle = Day11.using(stones)
        val listOfStones = puzzle.part1(times)

        assertEquals(count, listOfStones.size)
        assertEquals(first5, listOfStones.take(5).joinToString(" "))
    }
}