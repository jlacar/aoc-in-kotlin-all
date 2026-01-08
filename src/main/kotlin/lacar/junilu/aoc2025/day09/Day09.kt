package lacar.junilu.aoc2025.day09

import lacar.junilu.common.Point2D
import lacar.junilu.common.thinArea

/**
 * AoC 2025 Day 09 - Movie Theater
 *
 * Puzzle page: https://adventofcode.com/2025/day/9
 */
class Day09(val redTiles: List<Point2D>) {

    fun biggestRectangle(): Long =
        redTiles.flatMapIndexed { i, corner ->
            allRectanglesWith(corner, i)
        }.maxOf { it.thinArea() }

    private fun allRectanglesWith(corner: Point2D, i: Int) =
        redTiles.drop(i).map { other ->
            Pair(corner, other)
        }

    fun part2(): Nothing = TODO()
}