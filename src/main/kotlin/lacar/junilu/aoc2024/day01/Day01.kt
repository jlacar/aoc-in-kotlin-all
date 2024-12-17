package lacar.junilu.aoc2024.day01

import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 *
 * https://adventofcode.com/2024/day/1
 */
class Day01(columns: Pair<List<Int>, List<Int>>) {
    private val leftColumn = columns.first.sorted()
    private val rightColumn = columns.second.sorted()

    fun totalDistance(): Int = leftColumn.zip(rightColumn)
        .sumOf { (left, right) -> abs(left - right) }

    fun totalSimilarity(): Int = leftColumn.sumOf { left ->
        left * rightColumn.count { right -> left == right }
    }

    companion object {
        fun using(input: List<String>) = Day01(
            input
            .map { s -> s.split(" ".repeat(3)).map(String::toInt) }
            .map { (left, right) -> left to right }
            .unzip()
        )
    }
}