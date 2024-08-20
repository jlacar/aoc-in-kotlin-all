package lacar.junilu

/**
 * AoC 2015 - Day 3: Perfectly Spherical Houses in a Vacuum
 *
 * https://adventofcode.com/2015/day/3
 */
class Day03(private val directions: String) {
    fun part1() = housesVisited(directions).distinct().count()

    fun part2() =
        (housesVisited(directions.filterIndexed { index, _ -> index % 2 == 0 }) +
         housesVisited(directions.filterIndexed { index, _ -> index % 2 == 1 }))
        .distinct()
        .count()

    private fun housesVisited(directions: String) =
        directions.runningFold(Pair(0, 0)) { currentHouse, ch ->
            val (horizontal, vertical) = currentHouse
            when (ch) {
                '<' -> currentHouse.copy(first = horizontal - 1)
                '>' -> currentHouse.copy(first = horizontal + 1)
                '^' -> currentHouse.copy(second = vertical + 1)
                else -> currentHouse.copy(second = vertical - 1)
            }
        }

    companion object {
        fun using(input: List<String>) = Day03(input.first())
    }
}