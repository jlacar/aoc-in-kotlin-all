package lacar.junilu.aoc2025.day05

import lacar.junilu.common.combineOverlapping

/**
 * AoC 2025 Day 5 - Cafeteria
 *
 * Puzzle page: https://adventofcode.com/2025/day/5
 */
class Day05(val ranges: List<LongRange>, val ids: List<Long>) {
    fun part1(): Int = ids.count { id -> ranges.any { id in it } }
    fun part2(): Long = ranges.combineOverlapping().sumOf { it.count() }
}

// Developer Note: The standard Iterable<T>.count() uses Ints and will overflow.
// Adding this to preserve semantics but get around the type limitation
private fun LongRange.count() = endInclusive - start + 1
