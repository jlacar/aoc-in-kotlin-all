package lacar.junilu.aoc2024.day01

import kotlin.math.abs

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */
object Day01 {
    fun totalDistance(column1: List<Int>, column2: List<Int>) =
        (column1.indices).map { i -> abs(column1[i] - column2[i]) }.sum()

    fun similarityScore(column1: List<Int>, column2: List<Int>): Int =
        column1.map { n -> column2.count { it == n } * n }.sum()
}