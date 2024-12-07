package lacar.junilu.aoc2024.day07

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day07Test {
    @Test
    fun `Example - Part 1`() {
        assertEquals(3749, Day07.using(example).part1())
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(0, Day07.using(inputForGitHub).part1())
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(0, Day07.using(inputForGitHub).part2())
    }

    companion object {
        val inputForGitHub = readPuzzleInput("aoc2024/day07-gh")

        val example = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent().lines()
    }
}