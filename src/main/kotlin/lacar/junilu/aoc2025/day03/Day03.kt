package lacar.junilu.aoc2025.day03

/**
 * AoC 2025 Day 03 - Lobby
 *
 * Puzzle page: https://adventofcode.com/2025/day/3
 */
class Day03(val banks: List<String>) {
    fun part1(): Long = banks.sumOf { it.joltage(2) }
    fun part2(): Long = banks.sumOf { it.joltage(12) }
}

private fun String.joltage(digits: Int) =
    (1..digits).fold(Pair(this, "")) { acc, i ->
        val (remaining, result) = acc
        val max = remaining.dropLast(digits - i).maxOf { it }
        remaining.drop(remaining.indexOf(max) + 1) to result + max
    }.second.toLong()
