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
        val levelChanges = zipWithNext { thisLevel, nextLevel -> nextLevel - thisLevel }
        val decreasing = { change: Int -> change < 0 }
        val increasing = { change: Int -> change > 0 }
        val inSafeRange = { change: Int -> abs(change) in 1..3 }

        return levelChanges.all(inSafeRange) && (levelChanges.all(decreasing) || levelChanges.all(increasing))
    }

    private fun List<Int>.canBeDampened() =
        indices.map { take(it) + drop(it + 1) }.any { it.isSafe() }
}