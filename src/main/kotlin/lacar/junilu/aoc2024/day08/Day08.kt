package lacar.junilu.aoc2024.day08

import lacar.junilu.common.DirectedSegment
import lacar.junilu.common.Grid
import lacar.junilu.common.Point
import lacar.junilu.println

class Day08(private val map: Grid) {

    // How many unique locations within the bounds of the map contain an antinode?
    fun part1(): Int = findAntiNodes().size

    fun part2(): Int {
        val antiNodeCount = findAntiNodes(resonate = true).size
        val allAntenna = map.locations.flatten().filterNot { it.symbol == '.' }
        val symbols = allAntenna.map { it.symbol }.toSet()
        val singles = symbols.associateWith { symbol ->
            allAntenna.count { it.symbol == symbol }
        }.count { it.value == 1 }
        return antiNodeCount - singles
    }

    private fun findAntiNodes(resonate: Boolean = false): Set<Point> {
        val allAntenna = map.locations.flatten().filterNot { it.symbol == '.' }
        val symbols = allAntenna.map { it.symbol }.toSet()

        fun resonantNodes(directedSegment: DirectedSegment) = generateSequence(directedSegment) {
            it.moveTo(it.end)
        }.takeWhile { map.isInbounds(it.end) }.map { it.end }.toSet()

        return symbols.map { symbol ->
            val allLikeThis = allAntenna.filter { it.symbol == symbol }
            allLikeThis.fold(listOf<Point>()) { antiNodes, thisAntenna ->
                val others = allLikeThis - thisAntenna
                when (resonate) {
                    false -> antiNodes + others.map { other ->
                                thisAntenna.lineSegmentTo(other).extend().endPoints
                             }.flatten()
                    true ->  antiNodes + others.map { other ->
                                val toOther = DirectedSegment(start = thisAntenna.point, end = other.point)
                                resonantNodes(toOther) + resonantNodes(toOther.reversed()).toSet()
                             }.flatten()
                }
            }
        }.flatten().filter { map.isInbounds(it) }.toSet()
    }

    companion object {
        fun using(lines: List<String>) = Day08 (
            Grid.parse(lines).also { it.displayString().println() }
        )
    }
}