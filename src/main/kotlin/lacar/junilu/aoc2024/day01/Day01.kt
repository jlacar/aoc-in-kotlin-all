package lacar.junilu.aoc2024.day01

import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */
object Day01 {
    fun totalDistance(left: List<Int>, right: List<Int>): Int {
        val leftSorted = left.sorted()
        val rightSorted = right.sorted()

        return leftSorted.indices.map { i -> abs(leftSorted[i] - rightSorted[i]) }.sum()
    }

    fun similarityScore(left: List<Int>, right: List<Int>): Int =
        left.map { n -> right.count { it == n } * n }.sum()
}