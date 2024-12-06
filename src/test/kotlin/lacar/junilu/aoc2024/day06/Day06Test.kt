package lacar.junilu.aoc2024.day06

import lacar.junilu.experimental.Puzzle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day06Test {
    @Test
    fun `Example - Part 1`() {
        assertEquals(41, usingExample.solve(Day06.part1))
        assertEquals(0, usingExample.solve(Day06.part2))
    }

    @Disabled
    @Test
    fun `Solution - Part 1`() {
        assertEquals(0, usingInputForGitHub.solve(Day06.part1))
    }

    @Disabled
    @Test
    fun `Solution - Part 2`() {
        assertEquals(0, usingInputForGitHub.solve(Day06.part2))
    }

    companion object {

        val usingInputForGitHub = Puzzle.using("aoc2024/day06-gh")

        val exampleInput = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent()

        val usingExample = Puzzle.usingText(exampleInput)
    }
}