package lacar.junilu.common

import lacar.junilu.aoc2016.day01.*
import lacar.junilu.println
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Calculates the next position when moving diff
 * positions from pos, with wrapping.
 *
 * @param max  the number of positions, with the
 *             last position index being max-1
 * @param pos  the current position
 * @param diff the number of positions to move,
 *             positive moves in the direction of 0 to max
 *             negative moves in the direction of max to 0
 */
fun wrap(max: Int, pos: Int, diff: Int) = ((pos + diff) % max + max) % max

// region ===== Grid

fun String.toGrid(originInFirst: Boolean = false) =
    Grid.parse(lines = this.lines(), originInFirst = originInFirst)

data class Grid(val locations: List<List<Location>>, val originInFirst: Boolean = false) {

    fun displayString() =
        (if (originInFirst) locations else locations.reversed())
        .map { row ->
            row.map { it.symbol }.joinToString("")
        }.joinToString("\n")

    fun getDistinctSymbols() = locations.flatten().map { it.symbol }.distinct()

    fun getAll(symbol: Char) = locations.flatten().filter { it.symbol == symbol }

    fun isInbounds(point: Point) =
        point.row in locations.indices && point.col in locations[0].indices

//    fun isInbounds(location: Location) = isInbounds(location.point)

    fun locationAt(point: Point) = locations[point.row][point.col]

    fun neighbors(location: Location, predicate: (Location) -> Boolean = { true }): List<Location> =
        location.point
            .allAdjacent()
            .filter { isInbounds(it) && predicate(locationAt(it)) }
            .map { point -> locationAt(point) }

    private fun isRegular(): Boolean = locations.all { line -> line.size == locations.first().size }

    fun getRegions(): List<Region> = Region.allIn(this)

    companion object {
        fun parse(lines: List<String>, originInFirst: Boolean = false) = Grid(
            (if (originInFirst) lines else lines.reversed())
                .mapIndexed { y: Int, row: String ->
                    row.mapIndexed { x: Int, ch: Char ->
                        Location(Point(row = y, col = x), symbol = ch)
                    }
                },
            originInFirst
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

data class Region(val locations: List<Location>, private val map: Grid) {
    companion object {
        fun allIn(grid: Grid): List<Region> = grid
            .getDistinctSymbols().flatMap { symbol ->
                val allSameSymbol = grid.getAll(symbol).map { it.point }
                val clusters = allSameSymbol.findClusters()

                clusters.fold(clusters) { acc, nextCluster ->
                    val (connected, disconnected) = acc.partition { it.intersect(nextCluster).isNotEmpty() }
                    val gatheredClusters = connected.reduce { gathered, cluster -> gathered + cluster }
                    listOf(gatheredClusters) + disconnected
                }
                    .map { it.toLocationsOn(grid) }
                    .map { Region(it, grid) }
            }

        private fun Set<Point>.toLocationsOn(grid: Grid) = map { grid.locationAt(it) }

        private fun List<Point>.findClusters() =
            fold(emptyList<Set<Point>>()) { acc, point ->
                val cluster = setOf(point) + filter { it.isAdjacentTo(point) }.toSet()
                acc + listOf(cluster)
            }
    }
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

    fun isAdjacentTo(other: Location) = point.isAdjacentTo(other.point)

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

    fun slope(): Double = rise.toDouble() / run.toDouble()

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

    fun isAdjacentTo(other: Point) = Direction.entries.any { shift(it) == other }

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