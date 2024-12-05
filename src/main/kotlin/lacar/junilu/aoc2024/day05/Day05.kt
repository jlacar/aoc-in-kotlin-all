package lacar.junilu.aoc2024.day05

import lacar.junilu.experimental.solution
import lacar.junilu.println

/**
 * AoC 2024 - Day 5: Print Queue
 *
 * https://adventofcode.com/2024/day/5
 */
object Day05 {
    val part1 get() = solution { input ->
        val (rules, pages) = parse(input)

        // What do you get if you add up the middle page number from those correctly-ordered updates?
        -1
    }

    val part2 get() = solution {
        TODO("Not implemented")
    }

    private fun parse(input: List<String>): Pair<Int, Int> {
        val (first, second) = input.joinToString("\n").split("\n\n")
        "First section: $first\nSecond section: $second".println()
        return Pair(0, 0)
    }
}