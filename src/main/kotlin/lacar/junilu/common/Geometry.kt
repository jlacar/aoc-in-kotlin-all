package lacar.junilu.common

import lacar.junilu.aoc2016.day01.*
import lacar.junilu.println
import kotlin.math.abs
import kotlin.math.sqrt

data class Grid(val locations: List<List<Location>>) {

    fun displayString() = locations.reversed().map { row ->
        row.map { it.symbol }.joinToString("")
    }.joinToString("\n")

    fun getDistinctSymbols() = locations.flatten().map { it.symbol }.distinct()

    fun getAll(symbol: Char) = locations.flatten().filter { it.symbol == symbol }

    fun isInbounds(point: Point) =
        point.row in locations.indices && point.col in locations[0].indices

//    fun isInbounds(location: Location) = isInbounds(location.point)

    fun locationAt(point: Point) = locations[point.row][point.col]

    fun neighbors(location: Location): List<Location> = location.point
        .allAdjacent()
        .filter { isInbounds(it) }
        .map { point -> locationAt(point) }

    private fun isRegular(): Boolean = locations.all { line -> line.size == locations.first().size }

    fun getRegions(): List<Region> = getDistinctSymbols().map { symbol ->
        val locs = getAll(symbol)
        Region(locs, this)
    }

    companion object {
        fun parse(lines: List<String>) = Grid(
            lines.reversed().mapIndexed { y: Int, row: String ->
                row.mapIndexed { x: Int, ch: Char ->
                    Location(Point(row = y, col = x), symbol = ch)
                }
            }
        ).also {
            check(it.isRegular()) {
                val display = lines.mapIndexed { lineNo: Int, s: String ->
                    "$lineNo: [$s]"
                }.joinToString("\n", prefix = "\n")
                "Grid input is not regular: $display".println()
            }
        }
    }
}

data class Region(val Locations: List<Location>, private val map: Grid) {

}

data class Location(val point: Point, val symbol: Char = '.', val facing: Direction = Direction.NORTH) {

    constructor(x: Int, y: Int, symbol: Char) : this(
        point = Point(col = x, row = y),
        symbol = symbol
    )

    fun nextMove(instruction: String): Location {
        val (turn, displacement) = parse(instruction)
        val newDirection = when (turn) {
            Turn.LEFT -> facing.left
            Turn.RIGHT -> facing.right
        }
        return nextMove(newDirection, displacement)
    }

    fun nextMove(direction: Direction, displacement: Int): Location {
        val (thisCol, thisRow) = point
        return Location(
            when (direction) {
                Direction.NORTH -> point.copy(row = thisRow + displacement)
                Direction.SOUTH -> point.copy(row = thisRow - displacement)
                Direction.EAST -> point.copy(col = thisCol + displacement)
                Direction.WEST -> point.copy(col = thisCol - displacement)
            }, facing = direction
        )
    }

    fun nextMove(displacement: Int): Location = nextMove(this.facing, displacement)

    fun isWithin(range: IntRange): Boolean = (point.row in range) && (point.col in range)
    fun lineSegmentTo(other: Location): LineSegment = LineSegment(this.point, other.point)
}

enum class Turn { LEFT, RIGHT; }

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    val left: Direction get() = entries[(ordinal + 3) % 4]
    val right: Direction get() = entries[(ordinal + 1) % 4]
}

data class LineSegment(val p1: Point, val p2: Point) {
    constructor(x1: Int, y1: Int, x2: Int, y2: Int) : this(Point(x1, y1), Point(x2, y2))
    constructor(point: Point, x: Int, y: Int) : this(point, Point(x, y))

    private val distance = DirectedSegment(p1, p2)

    val manhattan: Int get() = distance.manhattan
    val length: Double get() = distance.direct
    val endPoints: List<Point> get() = listOf(p1, p2)

    fun extend(): LineSegment =
        DirectedSegment(p1, p2).let { directed ->
            LineSegment(
                p1 = directed.extend().end,
                p2 = directed.reversed().extend().end
            )
        }
}

data class DirectedSegment(val start: Point, val end: Point) {
    private val rise: Int get() = end.row - start.row
    private val run: Int get() = end.col - start.col

    val direct: Double get() = sqrt(rise.toDouble() * rise + run.toDouble() * run)
    val manhattan: Int get() = abs(rise) + abs(run)

    fun reversed(): DirectedSegment = DirectedSegment(start = end, end = start)

    fun moveTo(newStart: Point) = DirectedSegment(
        start = newStart,
        end = Point(col = newStart.col + run, row = newStart.row + rise)
    )

    fun extend(): DirectedSegment = this.copy(
        end = Point(
            col = this.end.col + run,
            row = this.end.row + rise
        )
    )
}

data class Point(val col: Int, val row: Int) {
    fun directedLineTo(other: Point, direct: Boolean = false): Double =
        when (direct) {
            false -> DirectedSegment(this, other).manhattan.toDouble()
            true -> DirectedSegment(this, other).direct
        }

    fun allAdjacent(): List<Point> {
        val result = Direction.entries.map { this.shift(it) }
        return result
    }

    fun shift(direction: Direction) = when (direction) {
        Direction.NORTH -> this.copy(row = row + 1)
        Direction.SOUTH -> this.copy(row = row - 1)
        Direction.EAST -> this.copy(col = col + 1)
        Direction.WEST -> this.copy(col = col - 1)
    }

    val manhattan: Int get() = DirectedSegment(ORIGIN, this).manhattan

    companion object {
        val ORIGIN = Point(0, 0)
    }
}