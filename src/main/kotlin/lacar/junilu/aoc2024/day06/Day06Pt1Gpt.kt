package lacar.junilu.aoc2024.day06

import lacar.junilu.experimental.solution

object Day06GptDebugged {
    val part1 get() = solution { lines ->
        positionsVisitedByGuard(lines).size
    }

    fun List<String>.outOfBounds(x: Int, y: Int) =
        x !in indices || y !in this[0].indices

    fun positionsVisitedByGuard(map: List<String>): List<Pair<Int, Int>> {

        val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left
        var currentDirection = 0 // 0 = Up, 1 = Right, 2 = Down, 3 = Left
        val visited = mutableSetOf<Pair<Int, Int>>()

        // Find the guard's starting position
        var startX = 0
        var startY = 0
        for (i in map.indices) {
            val index = map[i].indexOf('^')
            if (index != -1) {
                startX = i
                startY = index
                break
            }
        }

        var x = startX
        var y = startY
        visited.add(Pair(x, y))

        while (true) {
            // Calculate the next position in the current direction
            val nextX = x + directions[currentDirection].first
            val nextY = y + directions[currentDirection].second

            if (map.outOfBounds(nextX, nextY)) break

            // Check if the next position is out of bounds or blocked
            if (map[nextX][nextY] == '#') {
                // Turn 90 degrees to the right
                currentDirection = (currentDirection + 1) % 4
            } else {
                // Move forward
                x = nextX
                y = nextY
                visited.add(Pair(x, y))
            }

            // Stop when the guard moves out of bounds
            if (map.outOfBounds(x, y)) break
        }

        // Output the result
        // println("Distinct positions visited: ${visited.size}")
        return visited.toList()
    }
}
