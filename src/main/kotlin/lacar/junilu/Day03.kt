package lacar.junilu

private typealias HouseLocation = Pair<Int, Int>

/**
 * AoC 2015 - Day 3: Perfectly Spherical Houses in a Vacuum
 *
 * https://adventofcode.com/2015/day/3
 */
class Day03(private val directions: String) {
    fun housesSantaVisited() = housesVisited(directions).distinct().count()

    fun housesSantaOrRoboSantaVisited() =
        (housesVisited(directions.filterIndexed { index, _ -> index % 2 == 0 }) +
         housesVisited(directions.filterIndexed { index, _ -> index % 2 == 1 }))
        .distinct()
        .count()

    private val Origin = Pair(0, 0)

    private fun housesVisited(directions: String): List<HouseLocation> =
        directions.runningFold(Origin) { currentLocation, ch ->
            val (horizontal, vertical) = currentLocation
            when (ch) {
                '<' -> currentLocation.copy(first = horizontal - 1)
                '>' -> currentLocation.copy(first = horizontal + 1)
                '^' -> currentLocation.copy(second = vertical + 1)
                else -> currentLocation.copy(second = vertical - 1)
            }
        }

    companion object {
        fun using(input: List<String>) = Day03(input.first())
    }
}