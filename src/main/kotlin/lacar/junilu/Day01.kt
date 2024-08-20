package lacar.junilu

/**
 * AoC 2015 - Day 1: Not Quite Lisp
 *
 * https://adventofcode.com/2015/day/1
 */
class Day01(private val directions: String) {

    // Solutions
    fun lastFloor() = directions.lastFloor()
    fun positionOfFirstTimeInBasement() = directions.positionOfFirstTimeInBasement()

    // Extension functions
    private fun CharSequence.lastFloor() =
        fold(0, nextFloor)

    private fun CharSequence.positionOfFirstTimeInBasement() =
        asSequence().runningFold(0, nextFloor).indexOfFirst { it == -1 }

    // Shared calculation
    private val nextFloor: (Int, Char) -> Int = { currentFloor, direction ->
        if (direction == '(') currentFloor.inc() else currentFloor.dec()
    }

    companion object {
        fun using(input: List<String>) = Day01(input.first())
    }
}