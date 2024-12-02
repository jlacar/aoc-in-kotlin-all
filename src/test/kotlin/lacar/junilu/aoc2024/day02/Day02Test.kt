package lacar.junilu.aoc2024.day02

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun `Solution - Part 1`() {
        assertEquals(516, Day02.howManyAreSafe(puzzleInputGitHub))
        assertEquals(282, Day02.howManyAreSafe(puzzleInputGmail))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(561, Day02.howManyAreSafeWithDampener(puzzleInputGitHub))
        assertEquals(349, Day02.howManyAreSafeWithDampener(puzzleInputGmail))
    }

    private val exampleReports = parse("""
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().lines())

    @Test
    fun `Example - Part 1`() {
        assertEquals(2, Day02.howManyAreSafe(exampleReports))
    }

    @Test
    fun `Example - Part 2`() {
        assertEquals(4, Day02.howManyAreSafeWithDampener(exampleReports))
    }

    companion object {
        private val puzzleInputGitHub = parse(readPuzzleInput("aoc2024/day02-gh"))
        private val puzzleInputGmail = parse(readPuzzleInput("aoc2024/day02-gm"))

        @JvmStatic
        fun parse(input: List<String>) = input.map { line ->
            line.split(" ").map(String::toInt)
        }
    }
}