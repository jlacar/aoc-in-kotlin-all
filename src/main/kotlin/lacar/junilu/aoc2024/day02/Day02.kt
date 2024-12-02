package lacar.junilu.aoc2024.day02

import lacar.junilu.aoc2024.day02.Day02.reports
import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import kotlin.math.abs

/**
 * AoC 2024: Day 2 -
 */
object Day02 {
    val reports = readPuzzleInput("aoc2024/day02-gh").map { line ->
        line.split(" ").map(String::toInt)
    }

    fun part1(reports: List<List<Int>>) = reports.count { isSafe(it) }
//        .also { it.println() } }

    fun isSafe(report: List<Int>): Boolean {
        val diffs = report.windowed(2).map { pair -> pair.first() - pair.last() }
        return diffs.none { it == 0 } && diffs.all { abs(it) in 1..3 } && (diffs.all { it < 0 } || diffs.all { it > 0 })
    }

    fun part2(reports: List<List<Int>>): Int = reports.count { isSafe(it) || canBeMadeSafe(it) }

    fun canBeMadeSafe(report: List<Int>): Boolean {
        return report.indices.map { (report.take(it) + report.drop(it + 1)) }.any { isSafe(it) }
    }
}

fun main() {

    val reports = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().lines().map { line -> line.split(" ").map(String::toInt) }

//    Day02.canBeMadeSafe(listOf(1, 2, 7, 8, 9)).println()

//    Day02.part1(reports).println()
//    Day02.part2(reports).println()

//    Day02.part1(Day02.reports).println()
    Day02.part2(Day02.reports).println()
}