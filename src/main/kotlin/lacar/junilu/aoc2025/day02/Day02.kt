package lacar.junilu.aoc2025.day02

/**
 * AoC 2025 Day 02 - Gift Shop
 *
 * Puzzle page: https://adventofcode.com/2025/day/2
 */
class Day02(val idRanges: List<LongRange>) {
    fun part1() = idRanges.sumOf { range -> range.filter { it.repeats(2) }.sum() }
    fun part2() = idRanges.sumOf { range -> range.filter { it.hasAnyRepeats() }.sum() }
}

private fun Long.repeats(times: Int): Boolean {
    val s = toString()
    return if (s.length % times == 0) s.chunked(s.length / times).areRepeated() else false
}

private fun Long.hasAnyRepeats() = (2..this.toString().length).any { n -> repeats(n) }

private fun List<String>.areRepeated() = all { it == first() }