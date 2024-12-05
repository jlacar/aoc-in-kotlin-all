package lacar.junilu.aoc2024.day02

import lacar.junilu.experimental.Puzzle
import lacar.junilu.experimental.solution
import lacar.junilu.println
import kotlin.math.abs

fun main() {
    val aoc2024 = Puzzle.using("aoc2024/day02-gh")

    "Answer for AoC 2024 Day 2, Part 1: ${aoc2024.solve(Day02.part1)}".println()
    "Answer for AoC 2024 Day 2, Part 2: ${aoc2024.solve(Day02.part2)}".println()
}

/**
 * AoC 2024 - Day 2: Red-Nosed Reports
 */
object Day02 {
    val part1
        get() = solution { input ->
            val reports = parse(input)

            // How many reports are safe?
            howManyAreSafe(reports)
        }

    val part2
        get() = solution { input ->
            val reports = parse(input)

            // (With dampening) How many reports are now safe?
            howManyAreSafe(reports, useDampener = true)
        }

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

    private fun parse(input: List<String>) =
        input.map { line -> line.split(" ").map(String::toInt) }
}