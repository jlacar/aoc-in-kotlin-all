package lacar.junilu.aoc2025.day05

import lacar.junilu.common.intersects

class Day05(val ranges: List<LongRange>, val ids: List<Long>) {

    fun part1(): Int = ids.count { id -> ranges.any { id in it } }

    fun part2(): Long = ranges.combineOverlapping().sumOf { it.last - it.first + 1 }

    companion object {
        fun using(lines: List<String>): Day05 {
            val blank = lines.indexOfFirst { it.isBlank() }
            return Day05(idRangesFrom(lines.take(blank)), idsFrom(lines.drop(blank + 1)))
        }

        private fun idsFrom(lines: List<String>): List<Long> =
            lines.map { it.toLong() }

        private fun idRangesFrom(lines: List<String>): List<LongRange> =
            lines.map { it.split("-").map { it.toLong() }.let { (start, end) -> start..end } }
    }
}

private fun List<LongRange>.combineOverlapping() =
    sortedBy { it.first }.let { sorted ->
        sorted.fold(listOf(sorted.first())) { acc, range ->
            val prev = acc.last()
            when {
                (range.intersects(prev)) -> when {
                    range.last > prev.last -> acc.dropLast(1) + listOf(prev.first..range.last)
                    else -> acc
                }
                else -> acc + listOf(range)
            }
        }
    }
