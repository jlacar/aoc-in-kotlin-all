package lacar.junilu.aoc2015.day24

import lacar.junilu.combinations

/**
 * AoC 2015 - Day 24: It Hangs in the Balance
 *
 * https://adventofcode.com/2015/day/24
 */
class Day24(private val weights: List<Int>) {
    fun smallestQuantumEntanglement(numberOfCompartments: Int): Long {
        val balancedWeight = weights.sum() / numberOfCompartments

        val fewestPossiblePackages = (2..weights.size).first { n ->
            weights.takeLast(n).sum() >= balancedWeight
        }

        val smallestGroupSize = (fewestPossiblePackages .. weights.size).first { groupSize ->
            weights.combinations(groupSize).any { it.sum() == balancedWeight }
        }

        return weights.combinations(smallestGroupSize)
            .filter { it.sum() == balancedWeight }
            .minOf { it.quantumEntanglement() }
    }

    private fun List<Int>.quantumEntanglement() =
        map(Int::toLong).reduce(Long::times)
}
