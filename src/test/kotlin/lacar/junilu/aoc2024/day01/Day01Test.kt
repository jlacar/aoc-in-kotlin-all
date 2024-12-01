package lacar.junilu.aoc2024.day01

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `Solutions - Part 1`() {
        val (leftGh, rightGh) = splitByColumn(puzzleInputGitHub)
        assertEquals(2285373, Day01.totalDistance(leftGh, rightGh))

        val (leftGm, rightGm) = splitByColumn(puzzleInputGmail)
        assertEquals(2031679, Day01.totalDistance(leftGm, rightGm))
    }

    @Test
    fun `Solutions - Part 2`() {
        val (leftGh, rightGh) = splitByColumn(puzzleInputGitHub)
        assertEquals(21142653, Day01.similarityScore(leftGh, rightGh))

        val (leftGm, rightGm) = splitByColumn(puzzleInputGmail)
        assertEquals(19678534, Day01.similarityScore(leftGm, rightGm))
    }

    companion object {
        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day01-gh")
        private val puzzleInputGmail = readPuzzleInput("aoc2024/day01-gm")

        private fun splitByColumn(lines: List<String>): Pair<List<Int>, List<Int>> {
            val columnPairs = lines.map { line ->
                val (left, right) = line.split("   ").map { it.toInt() }
                Pair(left, right)
            }
            return Pair(columnPairs.map { it.first }, columnPairs.map { it.second })
        }
    }
}