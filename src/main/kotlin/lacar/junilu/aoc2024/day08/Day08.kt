package lacar.junilu.aoc2024.day08

import lacar.junilu.common.DirectedSegment
import lacar.junilu.common.Grid
import lacar.junilu.common.Point

class Day08(private val map: Grid) {

    // How many unique locations within the bounds of the map contain an antinode?
    fun part1(): Int = findAntiNodes().size

    // Using the updated model with resonance, how many unique locations
    // within the bounds of the map contain an antinode?
    fun part2(): Int = findAntiNodes(resonate = true).size - nonResonatingNodeCount()

    private val allAntenna = map.locations.flatten().filterNot { it.symbol == '.' }

    private fun nonResonatingNodeCount(): Int {
        return allAntenna.map { it.symbol }.toSet().associateWith { symbol ->
            allAntenna.count { it.symbol == symbol }
        }.count { it.value == 1 }
    }

    private fun findAntiNodes(resonate: Boolean = false): Set<Point> {

        fun resonantNodes(signal: DirectedSegment) = generateSequence(signal) {
            it.moveTo(it.end)
        }.takeWhile { map.isInbounds(it.end) }.map { it.end }.toSet()

        val allFrequencies = allAntenna.map { it.symbol }.toSet()
        return allFrequencies.map { frequency ->
            val allWithThisFrequency = allAntenna.filter { it.symbol == frequency }
            allWithThisFrequency.fold(listOf<Point>()) { antiNodes, antenna ->
                val others = allWithThisFrequency - antenna
                when (resonate) {
                    false -> antiNodes + others.map { other ->
                        antenna.lineSegmentTo(other).extend().endPoints
                    }.flatten()

                    true -> antiNodes + others.map { other ->
                        val directedSignal = DirectedSegment(start = antenna.point, end = other.point)
                        resonantNodes(directedSignal) + resonantNodes(directedSignal.reversed()).toSet()
                    }.flatten()
                }
            }
        }.flatten().filter { map.isInbounds(it) }.toSet()
    }

    companion object {
        fun using(lines: List<String>) = Day08(Grid.parse(lines))
    }
}