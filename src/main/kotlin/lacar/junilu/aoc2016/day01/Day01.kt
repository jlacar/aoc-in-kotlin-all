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
        val start = Location(Position(0, 0), facing = NORTH)
        return instructions.split(", ")
            .fold(start) { location, instruction -> location.nextMove(instruction) }
            .position.manhattan
    }

    fun distanceToBunnyHQ(input: String): Int {
        val movesIt = input.split(", ").map { parse(it) }.iterator()
        val visited = mutableSetOf<Position>()
        val twiceVisited = mutableListOf<Position>()

        var location = Location(Position(0, 0), facing = NORTH)
        visited.add(location.position)
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
        visited: MutableSet<Position>,
        twiceVisited: MutableList<Position>
    ): Location {
        var nextLocation = location
        repeat(displacement) {
            nextLocation = nextLocation.nextMove(newDirection, 1)
            if (visited.contains(nextLocation.position))
                twiceVisited.add(nextLocation.position)
            else
                visited.add(nextLocation.position)
        }
        return nextLocation
    }
}

data class Position(val col: Int, val row: Int) {
    val manhattan: Int get() = Math.abs(col) + Math.abs(row)
}

data class Location(val position: Position, val symbol: Char = '.', val facing: Direction = NORTH) {
    fun nextMove(instruction: String): Location {
        val (turn, displacement) = parse(instruction)
        val newDirection = when (turn) {
            LEFT -> facing.left
            RIGHT -> facing.right
        }
        return nextMove(newDirection, displacement)
    }

    fun nextMove(direction: Direction, displacement: Int): Location {
        val (thisCol, thisRow) = position
        return Location(
            when (direction) {
                NORTH -> position.copy(row = thisRow + displacement)
                SOUTH -> position.copy(row = thisRow - displacement)
                EAST -> position.copy(col = thisCol + displacement)
                WEST -> position.copy(col = thisCol - displacement)
            }, facing = direction
        )
    }

    fun nextMove(displacement: Int): Location = nextMove(this.facing, displacement)

    fun isWithin(range: IntRange): Boolean = (position.row in range) && (position.col in range)
}

data class Grid(val locations: List<List<Location>>) {
    fun display(format: (Char) -> Char = { it }): Grid =
        locations.reversed().forEach { row ->
            row.forEach { location ->
                print(format(location.symbol))
            }
            println()
        }.let { this }

    companion object {
        fun parse(lines: List<String>) = Grid(
            lines.reversed().mapIndexed { y: Int, row: String ->
                row.mapIndexed { x: Int, ch: Char ->
                    Location(Position(row = y, col = x), symbol = ch)
                }
            }
        )
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