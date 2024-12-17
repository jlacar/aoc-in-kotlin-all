package lacar.junilu.aoc2024.day14

import lacar.junilu.common.Point

// region ===== Puzzle's Ubiquitous Language

data class Position(val point: Point) {
    val col get() = point.col
    val row get() = point.row
    override fun toString() = "Position[col=$col, row=$row]"
}

data class Velocity(val props: Pair<Int, Int>) {
    val dx get() = props.first
    val dy get() = props.second
    override fun toString() = "Velocity[dx=$dx, dy=$dy]"
}

private typealias Robot = Pair<Position, Velocity>
private val Robot.position get() = first
private val Robot.velocity get() = second

private typealias Quadrant = Pair<IntRange, IntRange>
private val Quadrant.columnIndices get() = first
private val Quadrant.rowIndices get() = second

// region ===== Solution

class Day14(private var robots: List<Robot>) {

    fun part1(): Int = robots.move(100).safetyFactor()

    private fun List<Robot>.safetyFactor() =
        quadrants().map { q -> count { q.contains(it) } }.reduce { a, b -> a * b }

    private fun List<Robot>.move(times: Int) = map { it.move(times) }

    private fun Robot.move(times: Int): Robot {
        fun wrap(max: Int, pos: Int, diff: Int) = ((pos + diff) % max + max) % max

        val newCol = wrap(COLUMNS, position.col, times * velocity.dx)
        val newRow = wrap(ROWS, position.row, times * velocity.dy)
        return Robot(Position(Point(newCol, newRow)), velocity)
    }

    private fun Quadrant.contains(robot: Robot): Boolean =
        robot.position.col in columnIndices &&
        robot.position.row in rowIndices

    private fun quadrants(): List<Quadrant> {
        val medianCol = COLUMNS / 2
        val medianRow = ROWS / 2

        val upper = 0..<medianRow
        val lower = (medianRow + 1)..<ROWS
        val left = 0..<medianCol
        val right = (medianCol + 1)..<COLUMNS

        return listOf( left to upper, right to upper, left to lower, right to lower )
    }

    companion object {
        const val COLUMNS = 101
        const val ROWS = 103

        fun using(lines: List<String>): Day14 {
            fun String.toIntPair(): Pair<Int, Int> {
                val (x, y) = drop(2).split(",")
                return Pair(x.toInt(), y.toInt())
            }

            return Day14(
                lines.map { line ->
                    val (p, v) = line.split(" ")
                    val (px, py) = p.toIntPair()
                    Robot(Position(Point(col=px, row=py)), Velocity(v.toIntPair()))
                }
            )
        }
    }
}

fun main() {
    val puzzle = Day14.using("""
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3        
        """.trimIndent().lines())

    check(puzzle.part1() == 0)
}