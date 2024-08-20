package lacar.junilu

private typealias RouteSegment = Pair<String, Int>

/**
 * AoC 2015 - Day 9: All in a Single Night
 *
 *  https://adventofcode/2015/day/10
 */
class Day09(private val segments: List<RouteSegment>) {

    private val allCities = segments.flatMap { it.first.split(" to ") }.distinct()
    private val allPossiblePaths = allCities.permutations()

    fun shortestRoute() = allPossiblePaths.minOf { eachPath -> distanceThrough(eachPath) }

    fun longestRoute() = allPossiblePaths.maxOf { eachPath -> distanceThrough(eachPath) }

    private fun distanceThrough(path: List<String>) = path.windowed(2)
        .sumOf { (city1, city2) -> distanceBetween(city1, city2) }

    private fun distanceBetween(city1: String, city2: String) =
        segments.find { it.first == "$city1 to $city2" || it.first == "$city2 to $city1" }?.second ?: 0

    companion object {
        fun using(input: List<String>) = Day09(input.map { segment ->
            val (cities, distance) = segment.split(" = ")
            RouteSegment(cities, distance.toInt())
        })
    }
}