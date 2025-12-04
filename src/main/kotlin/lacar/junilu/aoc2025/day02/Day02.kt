package lacar.junilu.aoc2025.day02

private typealias IdFilter = (Long) -> Boolean

/**
 * AoC 2025: Day 02 - Gift Shop
 */
class Day02(val idRanges: List<LongRange>) {

    fun LongRange.invalidIds(selector: IdFilter) = filter(selector)

    fun part1() = idRanges.sumOf { it.invalidIds { it.repeats(2) }.sum() }

    fun part2() = idRanges.sumOf { it.invalidIds { it.hasAnyRepeats() }.sum() }

    companion object {
        fun using(input: String): Day02 {
            val rangeStrings = input.split(",")
            val idRanges = rangeStrings.map { rangeString ->
                val (start, end) = rangeString.split("-").map(String::toLong)
                start..end
            }
            return Day02(idRanges)
        }
    }
}

private fun Long.hasAnyRepeats() = (2..this.toString().length).any { n -> repeats(n) }

private fun List<String>.areRepeated() = all { it == first() }

private fun Long.repeats(times: Int): Boolean {
    val s = toString()
    return if (s.length % times == 0) s.chunked(s.length / times).areRepeated() else false
}
