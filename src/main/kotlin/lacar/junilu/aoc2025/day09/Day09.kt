package lacar.junilu.aoc2025.day09

import lacar.junilu.common.Point2D
import lacar.junilu.common.distance
import lacar.junilu.common.thinArea

class Day09(val redTiles: List<Point2D>) {

    fun maxRectangularArea(): Long =
        redTiles.flatMapIndexed { i, corner1 ->
            redTiles.subList(i + 1, redTiles.size).map { corner2 ->
                Pair(corner1, corner2)
            }
        }.maxBy { it.distance() }.thinArea()

    fun part2(): Nothing = TODO()
}