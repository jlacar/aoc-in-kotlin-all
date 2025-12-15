package lacar.junilu.aoc2024.day04

import lacar.junilu.println
import lacar.junilu.readPuzzleLines

/**
 * AoC 2024 Day 4 -
 *
 * AI-generated code
 */
object Day04ai {
    fun countXmasOccurrences(grid: List<String>): Int {
        val word = "XMAS"
        val directions = listOf(
            Pair(0, 1),     // Horizontal right
            Pair(0, -1),    // Horizontal left
            Pair(1, 0),     // Vertical down
            Pair(-1, 0),    // Vertical up
            Pair(-1, 1),    // Diagonal up-right
            Pair(-1, -1),   // Diagonal up-left
            Pair(1, 1),     // Diagonal down-right
            Pair(1, -1),    // Diagonal down-left
        )

        fun searchFrom(x: Int, y: Int, dx: Int, dy: Int): Int {
            for (i in word.indices) {
                val nx = x + i * dx
                val ny = y + i * dy
                if (nx !in grid.indices || ny !in grid.indices || grid[nx][ny] != word[i]) {
                    return 0
                }
            }
            return 1
        }

        var count = 0
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                for ((dx, dy) in directions) {
                    count += searchFrom(x, y, dx, dy)
                }
            }
        }
        return count
    }

    fun countXmasPatterns(grid: List<String>): Int {
        val n = grid.size
        val m = grid[0].length

        fun isXMasPatter(x: Int, y: Int): Boolean {
            val patterns = listOf(
                "MAS".split(""),
                "SAME".split(""),
            )

            for (pattern1 in patterns) {
                for (pattern2 in patterns) {
//                    if (grid[x][y] == pattern1[0] && grid[x + 1][y] == pattern1[1] &&
//                        grid[x + 2][y] == pattern1[2] && grid[x][y - 1] == pattern2[0] &&
//                        grid[x + 1][y] == pattern2[1] && grid[x + 2][y + 1] == pattern2[2]) {
                        return true
//                    }
                }
            }
            return false
        }

        var count = 0
        for (x in 0 until n- 2) {
            for (y in 0 until m - 1) {
                if (isXMasPatter(x, y)) {
                    count++
                }
            }
        }
        return count
    }
}

fun main() {
    val grid = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX""".trimIndent().lines()

    Day04ai.countXmasOccurrences(grid).also { "test grid count of XMAS: $it".println() }
    Day04ai.countXmasPatterns(grid).also { "test grid count of X-MAS: $it".println() }

    Day04ai.countXmasOccurrences(readPuzzleLines("aoc2024/day04-gh")).also {
        "Solution Part 1: $it".println()
    }
    Day04ai.countXmasPatterns(readPuzzleLines("aoc2024/day04-gh")).also {
        "Solution Part 2: $it".println()
    }
}