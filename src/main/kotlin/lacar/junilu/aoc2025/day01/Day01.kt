package lacar.junilu.aoc2025.day01

import lacar.junilu.common.wrap
import kotlin.math.abs

/**
 * AoC 2025: Day 01 - Secret Entrance
 *
 * https://adventofcode.com/2025/day/1
 */
class Day01(val offsets: List<Int>) {

    private val startingPoint = 50

    fun solvePart1() = offsets.fold(listOf(startingPoint)) { acc, offset ->
        acc + nextPoint(acc.last(), offset)
    }.count { it == 0 }

    fun solvePart2() = offsets.fold(listOf(Pair(startingPoint, 0))) { acc, offset ->
        acc + nextPointWithZeroCount(acc.last().first, offset)
    }.sumOf { it.second }

    private fun nextPoint(current: Int, offset: Int) = wrap(max=100, pos=current, diff=offset)

    private fun nextPointWithZeroCount(current: Int, offset: Int) =
        Pair(nextPoint(current, offset), zeroCount(current, offset))

    companion object {
        fun using(input: List<String>): Day01 {
            fun toOffset(line: String) = (if (line.first() == 'R') 1 else -1) * line.drop(1).toInt()
            return Day01(input.map { toOffset(it) })
        }

        fun zeroCount(current: Int, offset: Int): Int {
            val clicks = abs(offset)
            val turningLeft = offset < 0
            val clicksToZero = if (current == 0) 100 else if (turningLeft) current else 100 - current
            return when {
                clicksToZero > clicks -> 0
                else -> 1 + when {
                    turningLeft -> (clicks - clicksToZero) / 100
                    else -> (offset - clicksToZero) / 100
                }
            }
        }
    }
}

