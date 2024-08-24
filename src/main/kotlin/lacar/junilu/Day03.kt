package lacar.junilu

private data class HouseLocation(val row: Int, val col: Int)

/**
 * AoC 2015 - Day 3: Perfectly Spherical Houses in a Vacuum
 *
 * https://adventofcode.com/2015/day/3
 */
class Day03(private val directions: String) {
    fun housesSantaVisited() = housesVisited(directions).count()

    fun housesSantaOrRobotSantaVisited() =
        (housesVisited(bySanta) union housesVisited(byRobotSanta)).count()

    private val evenIndicesOnly = { index: Int, _: Char -> index % 2 == 0 }
    private val oddIndicesOnly = { index: Int, _: Char -> index % 2 == 1 }

    private val bySanta = directions.filterIndexed(evenIndicesOnly)
    private val byRobotSanta = directions.filterIndexed(oddIndicesOnly)

    private val startLocation = HouseLocation(0, 0)

    private fun housesVisited(directions: String): Set<HouseLocation> =
        directions.runningFold(startLocation) { currentLocation, direction ->
            val (row, col) = currentLocation
            when (direction) {
                '<' -> currentLocation.copy(col = col - 1)
                '>' -> currentLocation.copy(col = col + 1)
                '^' -> currentLocation.copy(row = row + 1)
                else -> currentLocation.copy(row = row - 1)
            }
        }.toSet()
}