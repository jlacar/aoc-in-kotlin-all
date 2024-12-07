package lacar.junilu.aoc2024.day06

import lacar.junilu.experimental.solution

object Day06Gpt {
    val part1 get() = solution { lines ->
        unblockedPathPositions(lines).size
    }

    val part2 get() = solution { lines ->
        loopyPositions(lines).size
    }

    private fun unblockedPathPositions(map: List<String>): List<Pair<Int, Int>> {
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

        fun outOfBounds(x: Int, y: Int) = x !in map.indices || y !in map[0].indices

        var x = startX
        var y = startY
        visited.add(Pair(x, y))

        while (true) {
            // Calculate the next position in the current direction
            val nextX = x + directions[currentDirection].first
            val nextY = y + directions[currentDirection].second

            if (outOfBounds(nextX, nextY)) break

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
            if (x !in map.indices || y !in map[0].indices) break
        }

        // Output the result
        println("Distinct positions visited: ${visited.size}")
        return visited.toList()
    }

    private fun loopyPositions(map: List<String>): List<Pair<Int, Int>> {
        val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left
        var startX = 0
        var startY = 0
        var startDirection = 0 // Initially facing up (0)

        // Find the starting position and direction of the guard
        for (i in map.indices) {
            val index = map[i].indexOf('^')
            if (index != -1) {
                startX = i
                startY = index
                break
            }
        }

        fun outOfBounds(x: Int, y: Int) = x !in map.indices || y !in map[0].indices

        // Function to check if a given obstruction causes a loop
        fun causesLoop(obstructionX: Int, obstructionY: Int): Boolean {
            val visitedStates = mutableSetOf<Triple<Int, Int, Int>>()
            var x = startX
            var y = startY
            var direction = startDirection

            while (true) {
                // Check if the current state (position + direction) has been visited
                val state = Triple(x, y, direction)
                if (state in visitedStates) return true // Loop detected
                visitedStates.add(state)

                // Calculate the next position in the current direction
                val nextX = x + directions[direction].first
                val nextY = y + directions[direction].second

                // Stop if the guard will go out of bounds
                if (outOfBounds(nextX, nextY)) break

                // Check if the next position is out of bounds or blocked (including the new obstruction)
                if (map[nextX][nextY] == '#' || (nextX == obstructionX && nextY == obstructionY)) {
                    // Turn 90 degrees to the right
                    direction = (direction + 1) % 4
                } else {
                    // Move forward
                    x = nextX
                    y = nextY
                }

                // Stop if the guard moves out of bounds
                if (outOfBounds(x, y)) break
            }

            return false // No loop detected
        }

        // Find all valid positions for the new obstruction
        val validPositions = mutableListOf<Pair<Int, Int>>()
        for (i in map.indices) {
            for (j in map[i].indices) {
                // Skip invalid obstruction positions
                if (map[i][j] == '#' || (i == startX && j == startY)) continue

                // Check if placing an obstruction here causes a loop
                if (causesLoop(i, j)) {
                    validPositions.add(Pair(i, j))
                }
            }
        }

        // Output the result
        // println("Number of valid positions for the obstruction: ${validPositions.size}")
        // println("Valid positions: $validPositions")

        return validPositions.toList()
    }
}
