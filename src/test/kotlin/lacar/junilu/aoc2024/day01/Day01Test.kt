package lacar.junilu.aoc2024.day01

import lacar.junilu.experimental.Puzzle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `Solution - Part 1 using Puzzle DSL`() {
        assertEquals(2285373, usingInputForGithub.solve(Day01.part1))
        assertEquals(2031679, usingInputForGmail.solve(Day01.part1))
    }

    @Test
    fun `Solution - Part 2 using Puzzle DSL`() {
        assertEquals(21142653, usingInputForGithub.solve(Day01.part2))
        assertEquals(19678534, usingInputForGmail.solve(Day01.part2))
    }

    @Test
    fun `Example - Part 1 and Part 2`() {
        assertEquals(11, usingExampleInput.solve(Day01.part1))
        assertEquals(31, usingExampleInput.solve(Day01.part2))
    }

    companion object {
        private val usingInputForGithub = Puzzle.using("aoc2024/day01-gh")
        private val usingInputForGmail = Puzzle.using("aoc2024/day01-gm")

        private val usingExampleInput = Puzzle.usingText("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3            
            """.trimIndent()
        )
    }
}