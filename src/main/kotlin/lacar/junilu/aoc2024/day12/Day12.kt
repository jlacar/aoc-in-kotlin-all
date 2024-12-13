package lacar.junilu.aoc2024.day12

import lacar.junilu.common.Grid
import lacar.junilu.common.Location
import lacar.junilu.common.Region
import lacar.junilu.println

class Day12(val grid: Grid) {

    fun part1(): Int = grid.getRegions().sumOf { it.perimeter * it.area }

    companion object {
        fun using(lines: List<String>) = Day12(
            Grid.parse(lines)
        )
    }
}

private val Region.area: Int
    get() = locations.size

private val Region.perimeter: Int
    get() = locations.fold(0) { acc, loc -> acc + 4 - locations.count { it.isAdjacentTo(loc) } }
