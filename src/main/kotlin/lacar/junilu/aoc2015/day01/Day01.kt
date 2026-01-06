package lacar.junilu.aoc2015.day01

/**
 * AoC 2015 Day 1 - Not Quite Lisp
 *
 * Puzzle page: https://adventofcode.com/2015/day/1
 */
class Day01(private val directions: String) {
    fun lastFloor() = floors().last()

    fun positionOfFirstTimeInBasement() = floors().indexOfFirst { it.isInBasement() }

    private fun floors(): Sequence<Int> = directions.iterator().let { directions ->
        generateSequence(0) {
            if (directions.hasNext()) {
                it.go(directions.next())
            } else null
        }
    }
}

private fun Char.isUp() = this == '('

private fun Int.go(next: Char): Int = if (next.isUp()) this.inc() else this.dec()

private fun Int.isInBasement() = this < 0