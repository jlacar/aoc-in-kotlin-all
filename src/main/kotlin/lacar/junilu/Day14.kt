package lacar.junilu

import kotlin.math.min

/**
 * AoC 2015 - Day 14: Reindeer Olympics
 *
 * https://adventofcode.com/2015/day/14
 */
class Day14(private val reindeer: List<Reindeer>, private val raceTime: Int) {

    fun part1() = reindeer.maxOf { it.distanceFlownAt(raceTime) }

    fun part2() = racePoints().values.max()

    private fun racePoints(): Map<Reindeer, Int> =
        (1..raceTime).fold(reindeer.associateWith { 0 }) { points, second ->
            award(points, reindeer.associateWith { it.distanceFlownAt(second) })
        }

    private fun award(points: Map<Reindeer, Int>, distances: Map<Reindeer, Int>): Map<Reindeer, Int>
    {
        val leadersDistance = distances.values.max()
        return points.map { (reindeer, leaderPoints) ->
            reindeer to if (distances[reindeer] == leadersDistance) leaderPoints + 1 else leaderPoints
        }.toMap()
    }

    companion object {
        fun using(input: List<String>, raceTime: Int) = Day14(reindeerFrom(input), raceTime)

        private fun reindeerFrom(input: List<String>) = input.map { line ->
            val parts = line.split(" ")
            Reindeer(
                speed = parts[3].toInt(),
                flightTime = parts[6].toInt(),
                restTime = parts[13].toInt()
            )
        }
    }

    data class Reindeer(val speed: Int, val flightTime: Int, val restTime: Int) {
        private val cycleTime = flightTime + restTime
        fun distanceFlownAt(raceTime: Int) = speed * secondsFlyingAt(raceTime)

        private fun secondsFlyingAt(raceTime: Int) = fullFlightTime(raceTime) + partialFlightTime(raceTime)
        private fun fullFlightTime(raceTime: Int) = raceTime / cycleTime * flightTime
        private fun partialFlightTime(raceTime: Int) = min(flightTime, raceTime % cycleTime)

        fun isFlyingAt(raceTime: Int) = (raceTime % cycleTime) in (1..flightTime)
    }
}