package lacar.junilu.aoc2024.day08

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day08Test {
    @Test
    fun `Solution - Part 1`() {
        assertEquals(318, puzzleUsingGitHubInput.part1())
        assertEquals(354, puzzleUsingGmailInput.part1())
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(1126, puzzleUsingGitHubInput.part2())
        assertEquals(1263, puzzleUsingGmailInput.part2())
    }

    @Test
    fun `Example Part 1`() {
        assertEquals(14, puzzleUsingExample.part1())
    }

    companion object {
        val puzzleUsingGitHubInput = Day08.using(readPuzzleInput("aoc2024/day08-gh"))
        val puzzleUsingGmailInput = Day08.using(readPuzzleInput("aoc2024/day08-gm"))

        val example = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """.trimIndent().lines()

        var puzzleUsingExample = Day08.using(example)
    }
}