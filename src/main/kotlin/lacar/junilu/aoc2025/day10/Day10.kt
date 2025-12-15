package lacar.junilu.aoc2025.day10

import lacar.junilu.common.dropEnds
import lacar.junilu.common.findInts
import java.util.BitSet

/**
 * AoC 2025 Day 10: Factory
 *
 * Puzzle page: https://adventofcode.com/2025/day/10
 */
class Day10(val machines: List<Machine>) {
    fun part1() = machines.sumOf { it.fewestConfigurationSteps() }

    companion object {
        fun using(input: List<String>) = Day10(
            input.map { Machine.from(it) }
        )
    }
}

class Machine(val lights: BitSet, val buttons: List<BitSet>, val joltages: List<Int>) {
    fun fewestConfigurationSteps(): Int {

    }

    companion object {
        fun from(description: String) = description.split(" ")
            .let {
                Machine(
                    lights = it[0].dropEnds().foldIndexed(BitSet()) { index, bits, ch ->
                        bits.set(index, ch == '#')
                        bits
                    },
                    buttons = it.dropEnds().map { wiring ->
                        wiring.dropEnds().findInts().fold(BitSet()) { bits, index ->
                            bits.set(index, true)
                            bits
                        }
                    },
                    joltages = it.last().dropEnds().findInts()
                )
            }
    }
}
