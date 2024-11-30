package lacar.junilu.aoc2015.day25

/**
 * AoC 2015: Day 25 - Let it Snow
 *
 * https://adventofcode/2015/day/25
 */

object Day25 {
    fun codeAt(row: Int, col: Int): Long = codes().take(ordinalFor(row, col)).last()

    private fun codes(): Sequence<Long> = generateSequence(20151125) {
        (it * 252533) % 33554393
    }

    fun ordinalFor(row: Int, col: Int): Int =
        when {
            row == 1 && col == 1 -> 1
            row > 1 && col == 1 -> ordinalFor(row - 1, 1) + row - 1
            else -> ordinalFor(row + col - 1, 1) + col - 1
        }
}

