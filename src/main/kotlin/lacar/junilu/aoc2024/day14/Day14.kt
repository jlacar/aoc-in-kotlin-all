package lacar.junilu.aoc2024.day14

import lacar.junilu.common.wrap
import lacar.junilu.common.findInts
import lacar.junilu.println
import lacar.junilu.readPuzzleInput

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

    fun part1(): Int = quadrants()
        .map { quadrant -> robots
            .map { it.move(100) }
            .count { it.isIn(quadrant) }
        }.reduce(Int::times)

    fun part2() {
        generateSequence(robots) { bots ->
            bots.map { it.move(1) }
        }.mapIndexed { gen, map -> Pair(gen, map) }
        .filter { it.second.mightHaveTree() }
        .take(1).forEach {
            it.second.display()
            "s = ${it.first}\n".println()
        } // 7344
    }

    private fun List<Robot>.mightHaveTree(): Boolean {
        val tree = "@{10,}".toRegex()
        for (row in 0..<ROWS) {
            val botsInRow = filter { it.row == row }
            val s = (0..<COLUMNS).map { col ->
                if (botsInRow.count { it.col == col } > 0) '@' else '.'
            }.joinToString("")
            if (tree.containsMatchIn(s)) return true
        }
        return false
    }

    private fun List<Robot>.display() {
        (0..<ROWS).forEach { row ->
            (0..<COLUMNS).forEach { col ->
                if (count { it.row == row && it.col == col } > 0)
                    print("*")
                else
                    print(".")
            }
            "".println()
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

fun main() {
    Day14.using(readPuzzleInput("aoc2024/day14-gh")).part2()  // 7344
    Day14.using(readPuzzleInput("aoc2024/day14-gm")).part2()  // 7861
}
