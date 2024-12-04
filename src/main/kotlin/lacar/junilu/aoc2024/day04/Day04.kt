package lacar.junilu.aoc2024.day04

/**
 * AoC 2024 - Day 4: Ceres Search
 *
 * https://adventofcode.com/2024/day/4
 */
object Day04 {
    fun xmasCount(lines: List<String>) =
        xmasIn(lines) +
        xmasIn(columns(lines)) +
        xmasIn(diagonals(lines))

    private val xmas = "XMAS".toRegex()

    private fun xmasIn(lines: List<String>): Int =
        lines.sumOf { line ->
            xmas.findAll(line).count() + xmas.findAll(line.reversed()).count()
        }

    private fun diagonals(lines: List<String>): List<String> {
        val indices = diagonalIndices(lines)
        return indices.map { d -> d.map { (r, c) -> lines[r][c] }.joinToString("") }
    }

    private fun diagonalIndices(lines: List<String>): List<List<Pair<Int, Int>>> {
        val max = lines.lastIndex
        return (1..max-3).map { start -> (start..max).map { col -> Pair(col - start, col) } } +
               (0..max-3).map { start -> (0..max - start).map { col -> Pair(start + col, col) } } +
               (3..max).map { start -> (0..start).map { col -> Pair(start - col, col) } } +
               (1..max-3).map { start -> (start..max).map { col -> Pair(max - col + start, col) } }
    }

    fun columns(lines: List<String>): List<String> {
        val rowMax = lines.indices.last
        val colMax = lines.first().lastIndex
        return (0..rowMax).map { r ->
            (0..colMax).map { c -> lines[c][r] }.joinToString("")
        }
    }
}