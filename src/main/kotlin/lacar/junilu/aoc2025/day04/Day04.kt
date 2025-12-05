package lacar.junilu.aoc2025.day04

class Day04(val rolls: List<String>) {
    fun part1(): Int = cleanUp(rolls).take(2).last().rollsRemoved
    fun part2(): Int = cleanUp(rolls).drop(1).takeWhile { it.rollsRemoved > 0 }.sumOf { it.rollsRemoved }
}

private fun cleanUp(rolls: List<String>) = generateSequence(Pair(rolls, 0)) { prev ->
    val before = prev.first
    val width = before.first().length
    val after = pad(before).windowed(3) { (rowBefore, currentRow, rowBelow) ->
        fun tryToRemove(it: Int): Char {
            val above = rowBefore.windowed(3)
            val beside = currentRow.windowed(3)
            val below = rowBelow.windowed(3)
            return if (hasTooMany(above[it], below[it], beside[it])) '@' else 'X'
        }
        (0..<width).map {
            when (currentRow[it + 1]) {
                '@' -> tryToRemove(it)
                else -> '.'
            }
        }.joinToString("")
    }

    // uncomment to visualize the cleanup process
    // displayRolls(after)

    Pair(after, before.rollCount() - after.rollCount())
}

// Match language used in problem and improve semantic clarity
private val Pair<List<String>, Int>.rollsRemoved get() = this.second

// Visualize the grid
private fun displayRolls(rolls: List<String>) {
    println(rolls.joinToString("\n"))
    println()
}

// Pad grid so we don't have to deal with edge cases
private fun pad(rolls: List<String>): List<String> {
    val padRow = ".".repeat(rolls.first().length + 2)
    return listOf(padRow) + rolls.map { ".$it." } + padRow
}

private fun hasTooMany(above: String, below: String, beside: String) =
    above.rollCount() + below.rollCount() + beside.countBeside() >= 4

// Add for semantic clarity
private fun Char.isRoll() = this == '@'

private fun String.rollCount() = this.count { it.isRoll() }

private fun String.countBeside(): Int {
    fun count(c: Char) = if (c.isRoll()) 1 else 0
    return count(first()) + count(last())
}

private fun List<String>.rollCount() = sumOf { it.count { it.isRoll() } }