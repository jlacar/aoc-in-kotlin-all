package lacar.junilu.aoc2024.day01

import lacar.junilu.experimental.solution
import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */
object Day01 {

    /* What is the total distance between your lists? */
    val part1 get() = solution { input ->
        val (left, right) = toColumns(input)

        // answer:
        val leftSorted = left.sorted()
        val rightSorted = right.sorted()
        leftSorted.indices.sumOf { i -> abs(leftSorted[i] - rightSorted[i]) }
    }

    /* What is the total similarity score? */
    val part2 get() = solution { input ->
        val (left, right) = toColumns(input)

        // answer:
        left.sumOf { n -> right.count { it == n } * n }
    }

    // parse Input
    private fun toColumns(input: List<String>): Pair<List<Int>, List<Int>> {
        val (left, right) = input.map { it.split("   ") }
            .map { (left, right) -> left.toInt() to right.toInt() }
            .unzip()

        return Pair(left, right)
    }
}