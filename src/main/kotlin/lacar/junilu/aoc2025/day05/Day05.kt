package lacar.junilu.aoc2025.day05

import lacar.junilu.common.combineOverlapping

class Day05(val ranges: List<LongRange>, val ids: List<Long>) {
    fun part1(): Int = ids.count { id -> ranges.any { id in it } }
    fun part2(): Long = ranges.combineOverlapping().sumOf { it.last - it.first + 1 }
}
