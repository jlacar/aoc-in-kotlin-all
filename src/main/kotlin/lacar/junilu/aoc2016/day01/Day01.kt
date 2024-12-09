package lacar.junilu.aoc2016.day01

import lacar.junilu.common.Direction
import lacar.junilu.common.Direction.*
import lacar.junilu.common.Turn.*
import lacar.junilu.common.Location
import lacar.junilu.common.Point
import lacar.junilu.common.Point.Companion.ORIGIN

/**
 * AoC 2016: Day 1 - No Time for a Taxicab
 *
 * https://adventofcode.com/2016/day/1
 */

object Day01 {
    fun distanceTraveled(instructions: String): Int {
        val start = Location(ORIGIN, facing = NORTH)
        return instructions.split(", ")
            .fold(start) { location, instruction -> location.nextMove(instruction) }
            .point.manhattan
    }

    fun distanceToBunnyHQ(input: String): Int {
        val movesIt = input.split(", ").map { parse(it) }.iterator()
        val visited = mutableSetOf<Point>()
        val twiceVisited = mutableListOf<Point>()

        var location = Location(ORIGIN, facing = NORTH)
        visited.add(location.point)
        while (twiceVisited.isEmpty() && movesIt.hasNext()) {
            val (turn, displacement) = movesIt.next()
            val newDirection = when (turn) {
                LEFT -> location.facing.left
                RIGHT -> location.facing.right
            }
            location = trackStepsTo(location, newDirection, displacement, visited, twiceVisited)
        }
        return twiceVisited.first().manhattan
    }

    private fun trackStepsTo(
        location: Location,
        newDirection: Direction,
        displacement: Int,
        visited: MutableSet<Point>,
        twiceVisited: MutableList<Point>
    ): Location {
        var nextLocation = location
        repeat(displacement) {
            nextLocation = nextLocation.nextMove(newDirection, 1)
            if (visited.contains(nextLocation.point))
                twiceVisited.add(nextLocation.point)
            else
                visited.add(nextLocation.point)
        }
        return nextLocation
    }
}

fun parse(instruction: String) = Pair(
    if (instruction.take(1) == "L") LEFT else RIGHT,
    instruction.drop(1).toInt()
)