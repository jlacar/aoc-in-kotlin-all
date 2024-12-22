package lacar.junilu.aoc2024.day14

import lacar.junilu.common.findInts
import lacar.junilu.common.wrap
import lacar.junilu.println

private typealias Quadrant = Pair<IntRange, IntRange>

private val Quadrant.columnIndices get() = first
private val Quadrant.rowIndices get() = second

class Day14(private val robots: List<Robot>, private val maxCols: Int, private val maxRows: Int) {

    fun part1(): Int {
        val afterMove = robots.map { it.move(100, maxCols, maxRows) }
        return quadrants().map { quadrant ->
            afterMove.count { it.isIn(quadrant) }
        }.reduce(Int::times)
    }

    fun part2(): Int = generateSequence(robots) { bots ->
        bots.map { it.move(1, maxCols, maxRows) }
    }.mapIndexed { sec, map -> Pair(sec, map) }
        .first { (_, map) -> map.mightHaveTree() }
        .also { (secs, map) ->
            map.display()
            "second = $secs\n".println()
        }.first

    data class Robot(val col: Int, val row: Int, val dx: Int, val dy: Int) {

        override fun toString() = "Robot[col=$col, row=$row, dx=$dx, dy=$dy]"

        fun move(times: Int, maxCols: Int, maxRows: Int) = copy(
            col = wrap(max = maxCols, pos = col, diff = dx * times),
            row = wrap(max = maxRows, pos = row, diff = dy * times)
        )

        fun isIn(quadrant: Quadrant) =
            col in quadrant.columnIndices &&
                    row in quadrant.rowIndices
    }

    private fun List<Robot>.rows() =
        (0..<maxRows).map { row -> filter { it.row == row } }

    private fun List<Robot>.mightHaveTree(): Boolean =
        rows().any { row -> consecutiveBots.containsMatchIn(row.plot()) }

    private val consecutiveBots = "@{10,}".toRegex()
    private fun List<Robot>.plot() =
        (0..<maxCols).map { col ->
            if (count { it.col == col } > 0) '@' else '.'
        }.joinToString("")

    private fun List<Robot>.display() {
        rows().forEach { it.plot().println() }
        "".println()
    }

    private fun quadrants(): List<Quadrant> {
        val medianCol = maxCols / 2
        val medianRow = maxRows / 2

        val upper = 0..<medianRow
        val lower = (medianRow + 1)..<maxRows
        val left = 0..<medianCol
        val right = (medianCol + 1)..<maxCols

        return listOf(left to upper, right to upper, left to lower, right to lower)
    }

    companion object {
        fun using(lines: List<String>, columns: Int = 101, rows: Int = 103): Day14 {
            return Day14(
                lines.map { line ->
                    val (col, row, dx, dy) = line.findInts()
                    Robot(col = col, row = row, dx = dx, dy = dy)
                },
                maxCols = columns,
                maxRows = rows
            )
        }
    }
}