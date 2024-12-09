package lacar.junilu.aoc2024.day06

import lacar.junilu.common.Direction
import lacar.junilu.common.Location
import lacar.junilu.common.Point
import lacar.junilu.experimental.solution
import lacar.junilu.print
import lacar.junilu.println

/**
 * https://adventofcode.com/2024/day/6
 */
object Day06 {

    val part1 = solution { lines ->
        val (obstacles, start) = parse(lines)

        // How many distinct positions will the guard visit before leaving the mapped area?
        val visitedPositions = trackPositions(lines.indices, start, obstacles)
        visitedPositions.size
    }

    val part2 = solution { lines ->
        val (obstacles, start) = parse(lines)

        // How many different positions could you choose for this obstruction?
        val visitedPositions = trackPositions(lines.indices, start, obstacles)

        0
    }


//        countLoops(
//            bounds = lines.indices,
//            oldObstacles = obstacles,
//            start = start
//        )

    val loopCount get() = solution { lines ->
        val (obstacles, start) = parse(lines)
        val bounds = lines.indices

        if (hasLoop(bounds, obstacles, Point(0, 0), start)) 1 else 0
//        countLoops(bounds, obstacles, start)
    }

    private fun printMap(bounds: IntRange, obstacles: Set<Point>, start: Point, currentLocation: Location? = null) {
        val loc = currentLocation?.point ?: Point(-1, -1)

        println("map:")
        bounds.reversed().forEach { row ->
            bounds.forEach { col ->
                val pos = Point(col, row)
                when (pos) {
                    loc -> when (currentLocation!!.facing) {
                        Direction.NORTH -> "^"
                        Direction.SOUTH -> "v"
                        Direction.EAST -> ">"
                        Direction.WEST -> "<"
                    }
                    start -> "S"
                    in obstacles -> "#"
                    else -> "."
                }.print()
            }
            println("")
        }
        println("")
    }

    private fun trackPositions(
        bounds: IntRange,
        start: Point,
        obstacles: Set<Point>
    ): MutableSet<Point> {
        val visited = mutableSetOf<Point>()
        var currentLoc = Location(start, facing = Direction.NORTH)
        while (currentLoc.isWithin(bounds)) {
            visited.add(currentLoc.point)
            val nextLoc = currentLoc.nextMove(1)
            if (nextLoc.point in obstacles) {
                currentLoc = currentLoc.nextMove("R1")
            } else {
                currentLoc = nextLoc
            }
        }
        return visited // .also { printMap(bounds, visited, start) }
    }

    private fun countLoops(
        bounds: IntRange,
        oldObstacles: Set<Point>,
        start: Point
    ): Int {
        val newObstacles = trackPositions(
            bounds = bounds,
            obstacles = oldObstacles,
            start = start
        )
        return newObstacles.count { newObstacle ->
            hasLoop(bounds, oldObstacles, newObstacle, start)
        }
    }

    fun hasLoop(
        bounds: IntRange,
        obstacles: Set<Point>,
        blocker: Point,
        start: Point
    ): Boolean {
        val blockedPath = obstacles + blocker

        var currentLoc = Location(start, facing = Direction.NORTH)
        val cornersTurned = mutableListOf<Location>()

        while (!inLoop(cornersTurned) && currentLoc.isWithin(bounds)) {
            val nextLoc = currentLoc.nextMove(1)
            if (nextLoc.point in blockedPath) {
                cornersTurned.add(currentLoc)
                currentLoc = currentLoc.nextMove("R1")
            } else {
                currentLoc = nextLoc
            }
        }

        return (inLoop(cornersTurned) && currentLoc.isWithin(bounds))
            .also {
                if (it) {
                    printMap(bounds, blockedPath, start, currentLoc)
                    """loop (${cornersTurned.size - 1})
                    |${cornersTurned.toSet().joinToString("\n")}""".trimMargin().println()
                }
            }
    }

    private fun inLoop(cornersTurned: MutableList<Location>): Boolean {
        val uniqueTurns = cornersTurned.toSet()
        return uniqueTurns.map { corner -> cornersTurned.count { it == corner } }.any { it >= 2 }
    }

    private fun checkBounds(outOfBounds: Location) {
        val (col, row) = outOfBounds.point
        (when (outOfBounds.facing) {
            Direction.NORTH -> { if (row == 130) "ok" else "BAD"}
            Direction.SOUTH -> { if (row == -1) "ok" else "BAD"}
            Direction.EAST -> { if (col == 130) "ok" else "BAD"}
            Direction.WEST -> { if (col == -1) "ok" else "BAD"}
        } + " $outOfBounds").println()
    }

    private fun parse(lines: List<String>): Pair<Set<Point>, Point> {
        val obstacles = mutableSetOf<Point>()
        val max = lines.lastIndex

        lateinit var guard: Point
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { col, ch ->
                val row = max - y
                when (ch) {
                    '#' -> obstacles.add(Point(col, row))
                    '^' -> guard = Point(col, row)
                    else -> {}
                }
            }
        }

        return Pair(obstacles, guard)
    }
}