package lacar.junilu.aoc2024.day08

import lacar.junilu.aoc2016.day01.Grid

class Day08(private val antennas: Grid) {

    // How many unique locations within the bounds of the map contain an antinode?
    fun part1(): Int = antennas.findAntiNodes().locations.flatten().count { it.symbol == '#' }

    private fun Grid.findAntiNodes(): Grid {
        return antennas
    }

    companion object {
        fun using(lines: List<String>) = Day08 (
            Grid.parse(lines).display()
        )
    }
}