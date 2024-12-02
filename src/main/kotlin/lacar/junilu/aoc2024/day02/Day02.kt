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
        val changeInLevels: (Int, Int) -> Int = { thisLevel, nextLevel -> nextLevel - thisLevel }
        val decreasing: (Int) -> Boolean = { it < 0 }
        val increasing: (Int) -> Boolean = { it > 0 }
        val inSafeRange: (Int) -> Boolean = { abs(it) in 1..3 }

        val changes = zipWithNext(changeInLevels)
        return changes.all(inSafeRange) && (changes.all(decreasing) || changes.all(increasing))
    }

    private fun List<Int>.canBeDampened() =
        indices.map { take(it) + drop(it + 1) }.any { it.isSafe() }
}