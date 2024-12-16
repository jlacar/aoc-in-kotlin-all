package lacar.junilu.aoc2024.day14

import lacar.junilu.common.Point
import lacar.junilu.println

// region ===== Puzzle's Ubiquitous Language

private data class Position(val point: Point) {
    val col get() = point.col
    val row get() = point.row
    override fun toString() = "Position[col=$col, row=$row]"
}

private data class Velocity(val props: Pair<Int, Int>) {
    val dx get() = props.first
    val dy get() = props.second
    override fun toString() = "Velocity[dx=$dx, dy=$dy]"
}

private typealias Robot = Pair<Position, Velocity>
private val Robot.position get() = first
private val Robot.velocity get() = second

// region ===== Solution

class Day14(private var robots: List<Robot>) {

    fun part1(): Int {
        robots.forEach { "${it.position}, ${it.velocity}".println() }
        return 0
    }

    companion object {

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