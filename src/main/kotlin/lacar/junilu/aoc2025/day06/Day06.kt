package lacar.junilu.aoc2025.day06

/**
 * AoC 2025: Day 06 - Trash Compactor
 *
 * https://adventofcode.com/2025/day/6
 */
class Day06(val numbers: List<List<Long>>, val operations: List<String>) {
    fun solve(): Long = operations.mapIndexed { index, operation ->
        when (operation) {
            "+" -> numbers[index].sum()
            "*" -> numbers[index].reduce { product, n -> product * n }
            else -> throw IllegalArgumentException("Unknown operation $operation")
        }
    }.sum()
}

/*
 * Developer Notes:
 *
 * 1. The crux of this problem is really the input parsing and shaping it into a format that's usable
 *    in the code above. The parsing logic is in Day06Test.kt to keep the concern separated from the
 *    calculations.
 */