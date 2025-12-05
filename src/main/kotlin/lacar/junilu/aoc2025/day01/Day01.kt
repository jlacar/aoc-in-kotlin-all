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

    fun solvePart1() = rotateAndCountZeroes(countIntermediate = false)
    fun solvePart2() = rotateAndCountZeroes(countIntermediate = true)

    private fun rotateAndCountZeroes(countIntermediate: Boolean): Int =
        offsets.fold(Pair(startingPoint, 0)) { acc, offset ->
            val (current, zeroes) = acc
            current.rotate(offset) to zeroes + current.timesRotatedToZero(offset, countIntermediate)
        }.zeroes
}

private fun Int.rotate(offset: Int) = wrap(max = 100, pos = this, diff = offset)

private fun Int.timesRotatedToZero(offset: Int, countIntermediate: Boolean): Int {
    return when {
        countIntermediate -> {
            val turningLeft = offset < 0
            val clicksToZero = if (this == 0) 100 else if (turningLeft) this else 100 - this
            val fullRevolutions = (abs(offset) - clicksToZero) / 100

            when {
                clicksToZero > abs(offset) -> 0
                else -> 1 + fullRevolutions
            }
        }
        rotate(offset) == 0 -> 1
        else -> 0
    }
}
