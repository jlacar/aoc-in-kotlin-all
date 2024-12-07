package lacar.junilu.aoc2024.day06

import lacar.junilu.readPuzzleInput

fun main() {

//    val map = readPuzzleInput("aoc2024/day06-gh")

//    val map = listOf(
//        "....#.....",
//        ".........#",
//        "..........",
//        "..#.......",
//        ".......#..",
//        "..........",
//        ".#..^.....",
//        "........#.",
//        "#.........",
//        "......#..."
//    )

//    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left
//
//    // Find the starting position and direction of the guard
//    val (startX, startY) = map.withIndex()
//        .flatMap { (row, line) -> line.withIndex().map { (col, char) -> Triple(row, col, char) } }
//        .first { it.third == '^' }
//        .let { Pair(it.first, it.second) }
//    val startDirection = 0 // Facing up (index 0 in directions)
//
//    fun causesLoop(obstructionX: Int, obstructionY: Int): Boolean {
//        val visitedStates = mutableSetOf<Triple<Int, Int, Int>>()
//        var x = startX
//        var y = startY
//        var direction = startDirection
//
//        while (true) {
//            // Check if the current state (position + direction) has been visited
//            val state = Triple(x, y, direction)
//            if (state in visitedStates) return true // Loop detected
//            visitedStates.add(state)
//
//            // Calculate the next position in the current direction
//            val nextX = x + directions[direction].first
//            val nextY = y + directions[direction].second
//
//            // Check if the next position is out of bounds
//            if (nextX !in map.indices || nextY !in map[0].indices) return false // Guard leaves the map
//
//            // Check if the next position is blocked (including the new obstruction)
//            if (map[nextX][nextY] == '#' || (nextX == obstructionX && nextY == obstructionY)) {
//                // Turn 90 degrees to the right
//                direction = (direction + 1) % 4
//            } else {
//                // Move forward
//                x = nextX
//                y = nextY
//            }
//        }
//    }

    // Simulate the guard's movement and check if an obstruction causes a loop
//    fun causesLoop(obstructionX: Int, obstructionY: Int): Boolean {
//        tailrec fun simulate(
//            x: Int,
//            y: Int,
//            direction: Int,
//            visited: Set<Triple<Int, Int, Int>>
//        ): Boolean {
//            // Check for out-of-bounds or loop detection
//            val state = Triple(x, y, direction)
//            if (state in visited) return true // Loop detected
//
//            // Calculate the next position
//            val nextX = x + directions[direction].first
//            val nextY = y + directions[direction].second
//
//            return when {
//                nextX !in map.indices || nextY !in map[0].indices -> false // Guard leaves the map
//                map[nextX][nextY] == '#' || (nextX == obstructionX && nextY == obstructionY) ->
//                    simulate(x, y, (direction + 1) % 4, visited + state) // Turn right
//                else -> simulate(nextX, nextY, direction, visited + state) // Move forward
//            }
//        }
//
//        return simulate(startX, startY, startDirection, emptySet())
    }

    // Find all valid positions for the obstruction
//    val validPositions = map.withIndex()
//        .flatMap { (row, line) -> line.withIndex().map { (col, _) -> Pair(row, col) } }
//        .filter { (x, y) -> map[x][y] != '#' && (x != startX || y != startY) } // Exclude starting position and obstacles
//        .filter { (x, y) -> causesLoop(x, y) } // Check if obstruction causes a loop
//
//    // Output the result
//    println("Number of valid positions for the obstruction: ${validPositions.size}")
////    println("Valid positions: $validPositions")
//}
