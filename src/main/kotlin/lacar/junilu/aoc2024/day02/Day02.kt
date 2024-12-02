package lacar.junilu.aoc2024.day02

import kotlin.math.abs

/**
 * AoC 2024 - Day 2: Red-Nosed Reports
 */
object Day02 {
    fun howManyAreSafe(reports: List<List<Int>>, useDampener: Boolean = false) =
        when (useDampener) {
            false -> reports.count { it.isSafe() }
            true -> reports.count { it.canBeDampened() }
        }

    private fun List<Int>.isSafe(): Boolean {
        val changeInLevels: (List<Int>) -> Int = { levels -> levels[1] - levels[0] }
        val inRange: (Int) -> Boolean = { abs(it) in 1..3 }
        val decreasing: (Int) -> Boolean = { it < 0 }
        val increasing: (Int) -> Boolean = { it > 0 }

        val changes = windowed(2).map(changeInLevels)
        return changes.all(inRange) && (changes.all(decreasing) || changes.all(increasing))
    }

    private fun List<Int>.canBeDampened() =
        indices.map { (take(it) + drop(it + 1)) }.any { it.isSafe() }
}