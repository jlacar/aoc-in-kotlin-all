package lacar.junilu.aoc2025.day04

/**
 * AoC 2025 Day 4 - Printing Department
 *
 * Puzzle page: https://adventofcode.com/2025/day/4
 */
class Day04(val rolls: List<String>) {
    fun part1(): Int = cleanUp(rolls).take(2).last().rollsRemoved
    fun part2(): Int = cleanUp(rolls).drop(1).takeWhile { it.rollsRemoved > 0 }.sumOf { it.rollsRemoved }
}

private fun cleanUp(rolls: List<String>) = generateSequence(Pair(rolls, 0)) { prev ->
    val before = prev.first
    val width = before.first().length
    val after = pad(before).windowed(3) { (rowAbove, thisRow, rowBelow) ->
        fun tryToRemove(it: Int): Char {
            val above = rowAbove.windowed(3)
            val beside = thisRow.windowed(3)
            val below = rowBelow.windowed(3)
            return if (hasTooMany(above[it], below[it], beside[it])) '@' else 'X'
        }
        (0..<width).map {
            when (thisRow[it + 1]) {
                '@' -> tryToRemove(it)
                else -> '.'
            }
        }.joinToString("")
    }

    if (visualize) displayRolls(after)

    Pair(after, before.rollCount() - after.rollCount())
}

// Pad grid so we don't have to deal with edge cases
private fun pad(rolls: List<String>): List<String> {
    val padRow = ".".repeat(rolls.first().length + 2)
    return listOf(padRow) + rolls.map { ".$it." } + padRow
}

// Control visualization of grid
private const val visualize = false

private fun displayRolls(rolls: List<String>) {
    println(rolls.joinToString("\n"))
    println()
}

// region DSL for problem

private val Pair<List<String>, Int>.rollsRemoved get() = this.second

private fun hasTooMany(above: String, below: String, beside: String) =
    above.rollCount() + below.rollCount() + beside.countBeside() >= 4

private fun Char.isRoll() = this == '@'

private fun String.rollCount() = this.count { it.isRoll() }

private fun String.countBeside(): Int {
    fun count(c: Char) = if (c.isRoll()) 1 else 0
    return count(first()) + count(last())
}

private fun List<String>.rollCount() = sumOf { it.count { it.isRoll() } }

// endregion