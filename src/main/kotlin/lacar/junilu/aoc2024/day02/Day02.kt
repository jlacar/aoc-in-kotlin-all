package lacar.junilu.aoc2024.day02

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
        val safelyDecreasing = { change: Int -> change in -3..-1 }
        val safelyIncreasing = { change: Int -> change in 1..3 }

        return levelChanges.all(safelyDecreasing) || levelChanges.all(safelyIncreasing)
    }

    private fun List<Int>.canBeDampened() =
        indices.map { take(it) + drop(it + 1) }.any { it.isSafe() }
}