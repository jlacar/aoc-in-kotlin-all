package lacar.junilu.aoc2024.day01

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `Solutions - Part 1`() {
        val (column1gh, column2gh) = splitIntoSortedColumns(puzzleInputGitHub)
        assertEquals(2285373, Day01.totalDistance(column1gh, column2gh))

        val (column1gm, column2gm) = splitIntoSortedColumns(puzzleInputGmail)
        assertEquals(2031679, Day01.totalDistance(column1gm, column2gm))
    }

    @Test
    fun `Solutions - Part 2`() {
        val (column1gh, column2gh) = splitIntoSortedColumns(puzzleInputGitHub)
        assertEquals(21142653, Day01.similarityScore(column1gh, column2gh))

        val (column1gm, column2gm) = splitIntoSortedColumns(puzzleInputGmail)
        assertEquals(19678534, Day01.similarityScore(column1gm, column2gm))
    }

    companion object {
        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day01-gh")
        private val puzzleInputGmail = readPuzzleInput("aoc2024/day01-gm")

        private fun splitIntoSortedColumns(lines: List<String>): Pair<List<Int>, List<Int>> {
            val (column1, column2) = splitIntoRawColumns(lines)
            return Pair(column1.sorted(), column2.sorted())
        }

        private fun splitIntoRawColumns(lines: List<String>): Pair<List<Int>, List<Int>> {
            val columnPairs = lines.map { line ->
                val (n1, n2) = line.split("   ").map { it.toInt() }
                Pair(n1, n2)
            }
            return Pair(columnPairs.map { it.first }, columnPairs.map { it.second })
        }
    }
}