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
}

data class Position(val x: Int, val y: Int) {
    val blocks: Int get() = Math.abs(x) + Math.abs(y)
}

data class Location(val facing: Direction, val position: Position) {
    fun move(instruction: String): Location {
        val turn = if (instruction.take(1) == "L") LEFT else RIGHT
        val distance = instruction.drop(1).toInt()
        val nowFacing = when (turn) {
            LEFT -> facing.left
            RIGHT -> facing.right
        }

        val (currentX, currentY) = position
        return Location(nowFacing, when (nowFacing) {
            NORTH -> position.copy(y = currentY + distance)
            SOUTH -> position.copy(y = currentY - distance)
            EAST -> position.copy(x = currentX + distance)
            WEST -> position.copy(x = currentX - distance)
        })
    }
}

enum class Turn { LEFT, RIGHT; }

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    val left: Direction get() = entries[(ordinal + 3) % 4]
    val right: Direction get() = entries[(ordinal + 1) % 4]
}