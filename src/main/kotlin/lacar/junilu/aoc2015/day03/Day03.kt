package lacar.junilu.aoc2015.day03

import lacar.junilu.common.isEven
import lacar.junilu.common.isOdd

/**
 * AoC 2015 Day 3 - Perfectly Spherical Houses in a Vacuum
 *
 * Puzzle page: https://adventofcode.com/2015/day/3
 */
class Day03(private val directions: String) {
    fun housesSantaVisited() = housesVisited(directions).count()

    fun housesSantaOrRobotSantaVisited(): Int {
        val bySanta = directions.filterIndexed { index, _ -> index.isEven() }
        val byRobotSanta = directions.filterIndexed { index, _ -> index.isOdd() }

        return (housesVisited(bySanta) union housesVisited(byRobotSanta)).count()
    }

    private fun housesVisited(directions: String): Set<HouseLocation> =
        directions.runningFold(initial = HouseLocation(0, 0)) { currentLocation, direction ->
            currentLocation.go(direction)
        }.toSet()
}

data class HouseLocation(val row: Int, val col: Int) {
    fun go(direction: Char) = when (direction) {
        '<' -> copy(col = col - 1)
        '>' -> copy(col = col + 1)
        '^' -> copy(row = row - 1)
        'v' -> copy(row = row + 1)
        else -> this
    }
}