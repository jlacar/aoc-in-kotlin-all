package lacar.junilu.aoc2025.day10

import lacar.junilu.combinations
import lacar.junilu.common.dropEnds
import lacar.junilu.common.findInts

/**
 * AoC 2025 Day 10: Factory
 *
 * Puzzle page: https://adventofcode.com/2025/day/10
 */
class Day10(val machines: List<Machine>) {
    fun part1() = machines.sumOf { it.fewestConfigurationSteps() }

    companion object {
        fun using(input: List<String>) = Day10(
            input.map { description -> Machine.from(description) }
        )
    }
}

typealias LightPattern = Set<Int>

data class Machine(val lights: LightPattern, val buttons: List<LightPattern>, val joltages: List<Int>) {
    fun fewestConfigurationSteps(): Int =
        (1..buttons.size).first { n ->
            buttons.combinations(n).map { wires ->
                lights.toggleAll(wires)
            }.any { it.isEmpty() }
        }

    companion object {
        fun from(description: String) = description.split(" ")
            .let {
                Machine(
                    lights = it[0].dropEnds().toLightPattern(),

                    buttons = it.dropEnds().map { wiring ->
                        wiring.findInts().toLightPattern()
                    },

                    joltages = it.last().dropEnds().findInts()
                )
            }
    }
}

private fun LightPattern.toggleAll(buttons: List<LightPattern>): LightPattern {
    return buttons.fold(this) { pattern, button -> pattern.toggle(button) }
}

private fun LightPattern.toggle(button: LightPattern): LightPattern =
    button.map { light -> if (light in this) -1 else light }
.filter { it >= 0 }.toSet() + (this - button)

private fun String.toLightPattern() = this.mapIndexed { index, ch ->
    if (ch == '#') index else -1
}.filter { it >= 0 }.toSet()

private fun List<Int>.toLightPattern() = this.toSet()