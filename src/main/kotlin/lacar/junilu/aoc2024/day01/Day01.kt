package lacar.junilu.aoc2024.day01

import lacar.junilu.experimental.solution
import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 *
 * https://adventofcode.com/2024/day/1
 */
object Day01 {

    val part1 get() = solution { input ->
        val (left, right) = toColumns(input)

        // What is the total distance between your lists?
        val leftSorted = left.sorted()
        val rightSorted = right.sorted()
        leftSorted.indices.sumOf { i -> abs(leftSorted[i] - rightSorted[i]) }
    }

    val part2 get() = solution { input ->
        val (left, right) = toColumns(input)

        // What is the total similarity score?
        left.sumOf { n -> right.count { it == n } * n }
    }

    /**
     * Separate input into two columns of numbers.
     */
    private fun toColumns(input: List<String>) = input
        .map { s -> s.split(" ".repeat(3)) }
        .map { (left, right) -> left.toInt() to right.toInt() }
        .unzip()
}