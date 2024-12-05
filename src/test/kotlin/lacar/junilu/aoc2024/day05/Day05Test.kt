package lacar.junilu.aoc2024.day05

import lacar.junilu.experimental.Puzzle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `Example - Part 1`() {
        assertEquals(0, usingExampleInput.solve(Day05.part1))
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(-1, usingInputForGithub.solve(Day05.part1))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(-1, usingInputForGithub.solve(Day05.part2))
    }

    companion object {
        val usingInputForGithub = Puzzle.using("aoc2024/day05-gh")

        val usingExampleInput = Puzzle.usingText("""
            
        """.trimIndent())
    }
}