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

    private val evenIndices = { index: Int, _: Char -> index % 2 == 0 }
    private val oddIndices = { index: Int, _: Char -> index % 2 == 1 }

    private val bySanta = directions.filterIndexed(evenIndices)
    private val byRobotSanta = directions.filterIndexed(oddIndices)

    private fun housesVisited(directions: String): Set<HouseLocation> =
        directions.runningFold(initial = HouseLocation(0, 0)) { currentLocation, direction ->
            val (row, col) = currentLocation
            when (direction) {
                '<' -> currentLocation.copy(col = col - 1)
                '>' -> currentLocation.copy(col = col + 1)
                '^' -> currentLocation.copy(row = row + 1)
                else -> currentLocation.copy(row = row - 1)
            }
        }.toSet()
}