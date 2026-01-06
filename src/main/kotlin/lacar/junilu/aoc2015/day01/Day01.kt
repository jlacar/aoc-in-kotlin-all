package lacar.junilu.aoc2015.day01

/**
 * AoC 2015 Day 1 - Not Quite Lisp
 *
 * Puzzle page: https://adventofcode.com/2015/day/1
 */
class Day01(private val directions: String) {
    fun lastFloor() = floors().last()

    fun positionOfFirstTimeInBasement() = floors().indexOfFirst { it.isInBasement() }

    private fun floors(): Sequence<Int> = directions.iterator().let { iterator ->
        generateSequence(0) {
            if (iterator.hasNext()) {
                if (iterator.next() == '(') it.inc() else it.dec()
            } else null
        }
    }
}

private fun Int.isInBasement() = this < 0