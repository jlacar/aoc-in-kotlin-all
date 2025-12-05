package lacar.junilu.aoc2025.day01

import lacar.junilu.common.wrap
import kotlin.math.abs

/**
 * AoC 2025: Day 01 - Secret Entrance
 *
 * https://adventofcode.com/2025/day/1
 */
class Day01(val offsets: List<Int>) {
    private val Pair<Int, Int>.zeroes get() = second

    private val startingPoint = 50

    fun solvePart1() = offsets.fold(Pair(startingPoint, 0)) { acc, offset ->
        val (current, zeroes) = acc
        val next = nextPoint(current, offset)
        next to zeroes + if (next == 0) 1 else 0
    }.zeroes

    fun solvePart2() = offsets.fold(Pair(startingPoint, 0)) { acc, offset ->
        val (current, zeroes) = acc
        val next = nextPoint(current, offset)
        next to zeroes + zeroCount(current, offset)
    }.zeroes
}

private fun nextPoint(current: Int, offset: Int) = wrap(max = 100, pos = current, diff = offset)

private fun zeroCount(current: Int, offset: Int): Int {
    val turningLeft = offset < 0
    val clicksToZero = if (current == 0) 100 else if (turningLeft) current else 100 - current
    val fullRevolutions = (abs(offset) - clicksToZero) / 100

    return when {
        clicksToZero > abs(offset) -> 0
        else -> 1 + fullRevolutions
    }
}
