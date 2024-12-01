package lacar.junilu.aoc2024.day01

import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */
object Day01 {
    fun totalDistance(left: List<Int>, right: List<Int>): Int {
        val leftSorted = left.sorted()
        val rightSorted = right.sorted()

        return leftSorted.indices.sumOf { i -> abs(leftSorted[i] - rightSorted[i]) }
    }

    fun similarityScore(left: List<Int>, right: List<Int>): Int =
        left.sumOf { n -> right.count { it == n } * n }
}