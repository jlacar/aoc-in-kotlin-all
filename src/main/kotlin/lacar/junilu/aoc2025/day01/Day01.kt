package lacar.junilu.aoc2025.day01

import lacar.junilu.common.wrap
import kotlin.math.abs

/**
 * AoC 2025 Day 01 - Secret Entrance
 *
 * Puzzle page: https://adventofcode.com/2025/day/1
 */
class Day01(val offsets: List<Int>) {
    fun zeroesInCombination() = offsets.runningFold(50) { current, offset ->
        current.rotate(offset)
    }.count { it == 0 }

    fun timesTurnedToZero() = offsets.runningFold(Pair(50, 0)) { (current, _), offset ->
        Pair(current.rotate(offset), current.timesRotatedToZero(offset))
    }.sumOf { it.zeroes }
}

private val Pair<Int, Int>.zeroes get() = second

private fun Int.rotate(offset: Int) = wrap(max = 100, pos = this, diff = offset)

private fun Int.timesRotatedToZero(offset: Int): Int {
    val clicksToZero = if (this == 0) 100 else if (offset < 0) this else 100 - this
    return if (clicksToZero > abs(offset)) 0 else 1 + (abs(offset) - clicksToZero) / 100
}