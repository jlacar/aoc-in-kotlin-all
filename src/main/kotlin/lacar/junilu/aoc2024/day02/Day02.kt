package lacar.junilu.aoc2024.day02

import kotlin.math.abs

/**
 * AoC 2024: Day 2 -
 */
object Day02 {
    fun howManyAreSafe(reports: List<List<Int>>) =
        reports.count { it.isSafe() }

    fun howManyAreSafeWithDampener(reports: List<List<Int>>) =
        reports.count { it.isSafe() || it.canBeDampened() }

    private fun List<Int>.isSafe(): Boolean {
        val changes = windowed(2).map { pair -> pair.first() - pair.last() }
        return changes.areInSafeRange() && changes.areConsistent()
    }

    private fun List<Int>.areInSafeRange() =
        all { abs(it) in 1..3 }

    private fun List<Int>.areConsistent() =
        all { it < 0 } || all { it > 0 }

    private fun List<Int>.canBeDampened() =
        indices.map { (take(it) + drop(it + 1)) }.any { it.isSafe() }
}