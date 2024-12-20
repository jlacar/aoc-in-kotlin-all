package lacar.junilu.aoc2024.day05

import lacar.junilu.experimental.Puzzle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `Solution - Part 1`() {
        assertEquals(5166, usingInputForGithub.solve(Day05.part1))
        assertEquals(4959, usingInputForGmail.solve(Day05.part1))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(4679, usingInputForGithub.solve(Day05.part2))
        assertEquals(4655, usingInputForGmail.solve(Day05.part2))
    }

    @Test
    fun `Example - Part 1 & 2`() {
        assertEquals(143, usingExampleInput.solve(Day05.part1))
        assertEquals(123, usingExampleInput.solve(Day05.part2))
    }

    companion object {
        val usingInputForGithub = Puzzle.using("aoc2024/day05-gh")
        val usingInputForGmail = Puzzle.using("aoc2024/day05-gm")

        val usingExampleInput = Puzzle.usingText(
        """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
        """.trimIndent())
    }
}