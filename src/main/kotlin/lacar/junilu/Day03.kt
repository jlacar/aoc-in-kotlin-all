package lacar.junilu

private typealias HouseLocation = Pair<Int, Int>

/**
 * AoC 2015 - Day 3: Perfectly Spherical Houses in a Vacuum
 *
 * https://adventofcode.com/2015/day/3
 */
class Day03(private val directions: String) {
    fun housesSantaVisited() = housesVisited(directions).distinct().count()

    fun housesSantaOrRobotSantaVisited() =
        (housesVisited(bySanta) + housesVisited(byRobotSanta)).distinct().count()

    private val evenIndicesOnly = { index: Int, _: Char -> index % 2 == 0 }
    private val oddIndicesOnly = { index: Int, _: Char -> index % 2 == 1 }

    private val bySanta = directions.filterIndexed(evenIndicesOnly)
    private val byRobotSanta = directions.filterIndexed(oddIndicesOnly)

    private val startLocation = Pair(0, 0)

    private fun housesVisited(directions: String): List<HouseLocation> =
        directions.runningFold(startLocation) { currentLocation, direction ->
            val (horizontal, vertical) = currentLocation
            when (direction) {
                '<' -> currentLocation.copy(first = horizontal - 1)
                '>' -> currentLocation.copy(first = horizontal + 1)
                '^' -> currentLocation.copy(second = vertical + 1)
                else -> currentLocation.copy(second = vertical - 1)
            }
        }
}