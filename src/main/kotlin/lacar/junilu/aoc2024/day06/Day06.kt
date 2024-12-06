package lacar.junilu.aoc2024.day06

import lacar.junilu.aoc2016.day01.Position
import lacar.junilu.experimental.solution

object Day06 {

    val part1: (List<String>) -> Any get() = solution { lines ->
        val (obstacles, start) = parse(lines)
        val max = lines[0].lastIndex

        // How many distinct positions will the guard visit before leaving the mapped area?
        0
    }

    val part2: (List<String>) -> Any get() = solution {
        TODO()
    }

    private fun parse(lines: List<String>): Pair<Set<Position>, Position> {

        return Pair(emptySet(), Position(0, 0))
    }
}