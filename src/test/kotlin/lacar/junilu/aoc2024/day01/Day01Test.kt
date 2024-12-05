package lacar.junilu.aoc2024.day01

import lacar.junilu.experimental.Puzzle
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.abs

class Day01Test {

    @Test
    fun `Solution - Part 1 using Puzzle DSL`() {
        assertEquals(2285373, usingInputForGithub.solve(Day01.part1))
        assertEquals(2031679, usingInputForGmail.solve(Day01.part1))
    }

    @Test
    fun `Solution - Part 2 using Puzzle DSL`() {
        assertEquals(2285373, usingInputForGithub.solve(Day01.part2))
        assertEquals(2031679, usingInputForGmail.solve(Day01.part2))
    }

    @Test
    fun `Solutions - Part 1`() {
        val (leftGh, rightGh) = splitByColumn(puzzleInputGitHub)
        val leftSorted = leftGh.sorted()
        val rightSorted = rightGh.sorted()
        assertEquals(2285373, leftSorted.indices.sumOf { i -> abs(leftSorted[i] - rightSorted[i]) })

        val (leftGm, rightGm) = splitByColumn(puzzleInputGmail)
        val leftSorted1 = leftGm.sorted()
        val rightSorted1 = rightGm.sorted()
        assertEquals(2031679, leftSorted1.indices.sumOf { i -> abs(leftSorted1[i] - rightSorted1[i]) })
    }

    @Test
    fun `Solutions - Part 2`() {
        val (leftGh, rightGh) = splitByColumn(puzzleInputGitHub)
        assertEquals(21142653, leftGh.sumOf { n -> rightGh.count { it == n } * n })

        val (leftGm, rightGm) = splitByColumn(puzzleInputGmail)
        assertEquals(19678534, leftGm.sumOf { n -> rightGm.count { it == n } * n })
    }

    companion object {
        private val usingInputForGithub = Puzzle.using("aoc2024/day01-gh")
        private val usingInputForGmail = Puzzle.using("aoc2024/day01-gm")

        private val puzzleInputGitHub = readPuzzleInput("aoc2024/day01-gh")
        private val puzzleInputGmail = readPuzzleInput("aoc2024/day01-gm")

        private fun splitByColumn(lines: List<String>): Pair<List<Int>, List<Int>> =
            lines.map { line ->
                val (left, right) = line.split("   ").map { it.toInt() }
                Pair(left, right)
            }.unzip()

    }
}