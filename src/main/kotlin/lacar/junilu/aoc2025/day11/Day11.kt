package lacar.junilu.aoc2025.day11

class Day11(val connections: Map<String, List<String>>) {

    private val memo = mutableMapOf<String, Int>()

    fun part1() = "you".pathsTo("out")

    private fun String.pathsTo(other: String) = pathCount(this, other)

    private fun pathCount(from: String, to: String): Int =
        memo.getOrPut(from + to) {
            if (from == to) 1 else connections[from]!!.sumOf { pathCount(it, to) }
        }
}