package lacar.junilu.aoc2016.day01

import lacar.junilu.aoc2016.day01.Direction.*
import lacar.junilu.aoc2016.day01.Turn.*

/**
 * AoC 2016: Day 1 - No Time for a Taxicab
 *
 * https://adventofcode.com/2016/day/1
 */

object Day01 {
    fun distance(instructions: String): Int {
        val start = Location(NORTH, Position(0, 0))
        return instructions.split(", ")
            .fold(start) { location, instruction -> location.move(instruction) }
            .position.blocks
    }

    fun distanceToBunnyHQ(input: String): Int {
        val movesIt = input.split(", ").map { parse(it) }.iterator()
        val visited = mutableSetOf<Position>()
        val twiceVisited = mutableListOf<Position>()

        var location = Location(NORTH, Position(0, 0))
        visited.add(location.position)
        while (twiceVisited.isEmpty() && movesIt.hasNext()) {
            val (turn, displacement) = movesIt.next()
            val newDirection = when (turn) {
                LEFT -> location.facing.left
                RIGHT -> location.facing.right
            }
            location = trackStepsTo(location, newDirection, displacement, visited, twiceVisited)
        }
        return twiceVisited.first().blocks
    }

    private fun trackStepsTo(
        location: Location,
        newDirection: Direction,
        displacement: Int,
        visited: MutableSet<Position>,
        twiceVisited: MutableList<Position>
    ): Location {
        var nextLocation = location
        repeat(displacement) {
            nextLocation = nextLocation.move(newDirection, 1)
            if (visited.contains(nextLocation.position))
                twiceVisited.add(nextLocation.position)
            else
                visited.add(nextLocation.position)
        }
        return nextLocation
    }
}

data class Position(val x: Int, val y: Int) {
    val blocks: Int get() = Math.abs(x) + Math.abs(y)
}

data class Location(val facing: Direction, val position: Position) {
    fun move(instruction: String): Location {
        val (turn, displacement) = parse(instruction)
        val newDirection = when (turn) {
            LEFT -> facing.left
            RIGHT -> facing.right
        }
        return move(newDirection, displacement)
    }

    fun move(direction: Direction, displacement: Int): Location {
        val (currentX, currentY) = position
        return Location(direction, when (direction) {
            NORTH -> position.copy(y = currentY + displacement)
            SOUTH -> position.copy(y = currentY - displacement)
            EAST -> position.copy(x = currentX + displacement)
            WEST -> position.copy(x = currentX - displacement)
        })
    }
}

fun parse(instruction: String) = Pair(
    if (instruction.take(1) == "L") LEFT else RIGHT,
    instruction.drop(1).toInt()
)

enum class Turn { LEFT, RIGHT; }

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    val left: Direction get() = entries[(ordinal + 3) % 4]
    val right: Direction get() = entries[(ordinal + 1) % 4]
}