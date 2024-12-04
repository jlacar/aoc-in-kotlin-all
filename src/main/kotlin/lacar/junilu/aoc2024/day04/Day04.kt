package lacar.junilu.aoc2024.day04

/**
 * AoC 2024 - Day 4: Ceres Search
 *
 * https://adventofcode.com/2024/day/4
 */
object Day04 {

    fun part1(lines: List<String>) =
        xmasIn(lines) +
                xmasIn(lines.columns()) +
                xmasIn(lines.diagonals())

    private val xmas = "XMAS".toRegex()

    private fun xmasIn(lines: List<String>): Int =
        lines.sumOf { line ->
            xmas.findAll(line).count() + xmas.findAll(line.reversed()).count()
        }

    private fun List<String>.columns(): List<String> {
        val rowMax = indices.last
        val colMax = first().lastIndex
        return (0..rowMax).map { r ->
            (0..colMax).map { c -> this[c][r] }.joinToString("")
        }
    }

    private fun List<String>.diagonals(): List<String> {
        val dcs = diagonalCoordinates(this)
        return dcs.map { dc -> dc.map { (row, col) -> this[row][col] }.joinToString("") }
    }

    private fun diagonalCoordinates(lines: List<String>): List<List<Pair<Int, Int>>> {
        val last = lines.lastIndex
        return (1..last - 3).map { first -> (first..last).map { col -> Pair(col - first, col) } } +
                (1..last - 3).map { first -> (first..last).map { col -> Pair(last - col + first, col) } } +
                (0..last - 3).map { first -> (0..last - first).map { col -> Pair(first + col, col) } } +
                (3..last).map { first -> (0..first).map { col -> Pair(first - col, col) } }
    }

    fun part2(lines: List<String>): Int =
        lines.windowed(3).sumOf { trie -> xCount(trie) }

    private fun xCount(lines: List<String>) =
        (1..<lines[0].lastIndex).count { midX ->
            xDiagonals(lines, midX).all { it == "MAS" || it == "SAM" }
        }

    private fun xDiagonals(lines: List<String>, mid: Int): List<String> {
        val diagonalDn = listOf(0 to mid - 1, 1 to mid, 2 to mid + 1)
            .map { (r, c) -> lines[r][c] }.joinToString("")
        val diagonalUp = listOf(0 to mid + 1, 1 to mid, 2 to mid - 1)
            .map { (r, c) -> lines[r][c] }.joinToString("")

        return listOf(diagonalDn, diagonalDn.reversed(), diagonalUp, diagonalUp.reversed())
    }
}