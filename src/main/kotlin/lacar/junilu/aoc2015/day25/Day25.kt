package lacar.junilu.aoc2015.day25

/**
 * AoC 2015: Day 25 - Let it Snow
 *
 * https://adventofcode/2015/day/25
 */

object Day25 {
    fun codeAt(row: Int, col: Int): Long = codes.take(ordinalFor(row, col)).last()

    private val codes: Sequence<Long>
        get() = generateSequence(20151125) {
            (it * 252533) % 33554393
        }
}

// Similar to calculating the sum of the numbers from 1..N
fun ordinalFor(row: Int, col: Int): Int {
    val diagonalIntersect = row + col - 1
    val ordAtTopOfDiagonal = diagonalIntersect * (diagonalIntersect + 1) / 2
    return ordAtTopOfDiagonal - row + 1
}

// Recursive alternative
fun recursiveOrdFor(row: Int, col: Int): Int =
    when {
        row == 1 && col == 1 -> 1
        row > 1 && col == 1 -> ordinalFor(row - 1, 1) + row - 1
        else -> ordinalFor(row + col - 1, 1) + col - 1
    }
