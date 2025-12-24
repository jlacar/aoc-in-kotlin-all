package lacar.junilu.aoc2025.day09

import lacar.junilu.common.Point2D
import lacar.junilu.common.distance
import lacar.junilu.common.thinArea

/**
 * AoC 2025 Day 09 - Movie Theater
 *
 * Puzzle page: https://adventofcode.com/2025/day/9
 */
class Day09(val redTiles: List<Point2D>) {

    fun maxRectangularArea(): Long =
        redTiles.flatMapIndexed { i, corner1 ->
            redTiles.subList(i + 1, redTiles.size).map { corner2 ->
                Pair(corner1, corner2)
            }
        }.maxOf { it.thinArea() }

    fun part2(): Nothing = TODO()
}