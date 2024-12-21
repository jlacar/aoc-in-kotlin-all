package lacar.junilu.aoc2024.day14

import lacar.junilu.common.findInts
import lacar.junilu.common.wrap
import lacar.junilu.println

private typealias Quadrant = Pair<IntRange, IntRange>
private val Quadrant.columnIndices get() = first
private val Quadrant.rowIndices get() = second

class Day14(private var robots: List<Robot>) {

    data class Robot(val col: Int, val row: Int, val dx: Int, val dy: Int) {
        override fun toString() = "Robot[col=$col, row=$row, dx=$dx, dy=$dy]"

        fun move(times: Int) = copy(
            col = wrap(max = COLUMNS, pos = col, diff = dx * times),
            row = wrap(max = ROWS, pos = row, diff = dy * times)
        )

        fun isIn(quadrant: Quadrant) = col in quadrant.columnIndices && row in quadrant.rowIndices
    }

    fun part1(): Int = quadrants().map { it.safetyFactor(100) }.reduce(Int::times)

    private fun Quadrant.safetyFactor(times: Int) = robots
        .map { it.move(times) }
        .count { it.isIn(this) }

    fun part2(): Int = generateSequence(robots) { bots ->
            bots.map { it.move(1) }
        }
        .mapIndexed { sec, map -> Pair(sec, map) }
        .first { (_, map) -> map.mightHaveTree() }
        .also {
            it.second.display()
            "second = ${it.first}\n".println()
        }.first

    private fun List<Robot>.mightHaveTree(): Boolean =
        (0..<ROWS).any { row ->
           val botsInRow = filter { it.row == row }
           consecutiveBots.containsMatchIn(botsInRow.plot(row))
        }

    private val consecutiveBots = "@{10,}".toRegex()
    private fun List<Robot>.plot(row: Int) =
        (0..<COLUMNS).map { col ->
            if (count { it.col == col && it.row == row } > 0) '@' else '.'
        }.joinToString("")

    private fun List<Robot>.display() {
        (0..<ROWS).forEach { row ->
            plot(row).println()
        }
        "".println()
    }

    private fun quadrants(): List<Quadrant> {
        val medianCol = COLUMNS / 2
        val medianRow = ROWS / 2

        val upper = 0..<medianRow
        val lower = (medianRow + 1)..<ROWS
        val left = 0..<medianCol
        val right = (medianCol + 1)..<COLUMNS

        return listOf(left to upper, right to upper, left to lower, right to lower)
    }

    companion object {
        const val COLUMNS = 101
        const val ROWS = 103

        fun using(lines: List<String>): Day14 {
            return Day14(
                lines.map { line ->
                    val (col, row, dx, dy) = line.findInts()
                    Robot(col = col, row = row, dx = dx, dy = dy)
                }
            )
        }
    }
}
