package lacar.junilu.aoc2015.day01

/**
 * AoC 2015 Day 1 - Not Quite Lisp
 *
 * Puzzle page: https://adventofcode.com/2015/day/1
 */
class Day01(private val directions: String) {
    fun lastFloor() = floors().last()

    fun positionOfFirstTimeInBasement() = floors().indexOfFirst { it < 0 }

    private fun floors(): Sequence<Int> {
        val iterator = directions.iterator()
        return generateSequence(0) {
            it.nextFloor(iterator)
        }
    }
}

private fun Int.nextFloor(directions: CharIterator): Int? =
    when {
        directions.hasNext() -> {
            if (directions.next() == '(') this.inc() else this.dec()
        }
        else -> null
    }