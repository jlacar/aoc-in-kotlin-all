package lacar.junilu.aoc2025.day11

private typealias DevicePath = Sequence<String>

/**
 * AoC 2025 Day 11 - Reactor
 *
 * Puzzle page: https://adventofcode.com/2025/day/11
 */
class Day11(val connections: Map<String, List<String>>) {

    private val countMemo = mutableMapOf<String, Int>()

    fun pathCount(from: String, to: String): Int =
        countMemo.getOrPut(from + to) {
            if (from == to) 1 else connections[from]!!.sumOf { pathCount(it, to) }
        }

    fun part2() = allPaths("svr", "out").count { it.contains("fft") && it.contains( "dac") }

    private fun allPaths(from: String, to: String): Sequence<DevicePath> {
        if (from == to) return sequenceOf(sequenceOf(from))
        return sequence {
            connections[from]!!.forEach { next ->
                sequence {
                    yield(from)

                }
            }
        }
    }

    fun pathCount(from: String, to: String, includes: List<String>): Int {
        return 0
    }
}