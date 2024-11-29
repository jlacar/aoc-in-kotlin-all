package lacar.junilu.aoc2015.day24

import lacar.junilu.combinations

/**
 * AoC 2015 - Day 24: It Hangs in the Balance
 *
 * https://adventofcode.com/2015/day/24
 */
class Day24(private val weights: List<Int>) {
    fun smallestQuantumEntanglement(numberOfGroups: Int): Long {
        val balancedWeight = weights.sum() / numberOfGroups

        val fewestPossiblePackages = (2..weights.size).first { n ->
            weights.takeLast(n).sum() >= balancedWeight
        }

        val smallestGroupSize = (fewestPossiblePackages .. weights.size).first { groupSize ->
            weights.combinations(groupSize).filter { it.sum() == balancedWeight }.any()
        }

        return weights.combinations(smallestGroupSize)
            .filter { it.sum() == balancedWeight }
            .minOf { quantumEntanglement(it) }
    }

    private fun quantumEntanglement(groupWeights: List<Int>) =
        groupWeights.map(Int::toLong).reduce(Long::times)
}
