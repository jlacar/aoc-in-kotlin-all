package lacar.junilu.aoc2024.day10

import lacar.junilu.common.Grid
import lacar.junilu.common.Location

class Day10(val map: Grid) {

    fun part1(): Int = map.getAll(TRAIL_HEAD)
        .sumOf { it.score() }

    private fun Location.score(): Int = map.getAll(TRAIL_END)
        .count { trailEnd -> this hasTrailUpTo trailEnd }

    fun part2(): Int = map.getAll(TRAIL_HEAD)
        .sumOf { it.rating() }

    private fun Location.rating(): Int = map.getAll(TRAIL_END)
        .sumOf { trailEnd -> this distinctTrailsUpTo trailEnd }

    private infix fun Location.hasTrailUpTo(otherLevel: Location): Boolean {
        if (this isNotLowerThan otherLevel) return false
        if (this isAdjacentTo otherLevel && this isOneBelow otherLevel) return true
        val allOneBelow = map.neighbors(otherLevel).filter { it isOneBelow otherLevel }
        return allOneBelow.any { this hasTrailUpTo it }
    }

    private infix fun Location.distinctTrailsUpTo(trailEnd: Location): Int {
        var uniqueTrails = 0
        //
        return uniqueTrails
    }

    private infix fun Location.isLowerThan(other: Location) = this.symbol < other.symbol
    private infix fun Location.isNotLowerThan(other: Location) = isLowerThan(other).not()
    private infix fun Location.isOneBelow(other: Location) = this.symbol.inc() == other.symbol

    private infix fun Location.isAdjacentTo(other: Location): Boolean {
        return other in map.neighbors(this)
    }

    companion object {
        const val TRAIL_HEAD = '0'
        const val TRAIL_END = '9'

        fun using(lines: List<String>) = Day10(
            Grid.parse(lines)
        )
    }
}

