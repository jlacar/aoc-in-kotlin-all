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
    fun part1() = machines.sumOf { it.fewestStepsToConfigureIndicatorLights() }

    fun part2() = machines.sumOf { it.fewestStepsToConfigureJoltageCounters() }

    companion object {
        fun using(input: List<String>) = Day10(
            input.map { description -> Machine.from(description) }
        )
    }
}

typealias ConfigurationPattern = Set<Int>

data class Machine(val lights: ConfigurationPattern, val buttons: List<ConfigurationPattern>, val joltages: List<Int>) {
    fun fewestStepsToConfigureIndicatorLights(): Int =
        (1..buttons.size).first { k ->
            buttons.combinations(k).map { wires ->
                lights.toggleAll(wires)
            }.any { it.isEmpty() }
        }

    fun fewestStepsToConfigureJoltageCounters(): Int =
        optimalJoltageSetupSteps(joltages, buttons, 0, Integer.MAX_VALUE)

    private fun optimalJoltageSetupSteps(
        joltages: List<Int>,
        buttons: List<ConfigurationPattern>,
        steps: Int,
        fewest: Int
    ): Int {
        if (steps >= fewest) return fewest
        if (joltages.all { it == 0 }) return steps
        if (buttons.isEmpty()) return fewest

        val wires = buttons.first()
        return (wires.minOf { joltages[it] } downTo 0).minOf { k ->
            optimalJoltageSetupSteps(
                joltages = joltages.mapIndexed { i, remaining -> if (i in wires) remaining - k else remaining },
                buttons = buttons.drop(1),
                steps = steps + k,
                fewest = fewest
            )
        }
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

private fun ConfigurationPattern.toggleAll(buttons: List<ConfigurationPattern>): ConfigurationPattern {
    return buttons.fold(this) { pattern, button -> pattern.toggleWith(button) }
}

private fun ConfigurationPattern.toggleWith(button: ConfigurationPattern): ConfigurationPattern =
    (this subtract (button intersect this)) union (button subtract this)

private fun String.toLightPattern() = this.mapIndexed { index, ch ->
    if (ch == '#') index else -1
}.filter { it >= 0 }.toSet()

private fun List<Int>.toLightPattern() = this.toSet()