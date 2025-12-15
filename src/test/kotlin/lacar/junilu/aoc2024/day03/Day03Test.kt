package lacar.junilu.aoc2024.day03

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `Solution - Part 1`() {
        assertEquals(173731097, Day03.part1(puzzleInputGitHub))
        assertEquals(182619815, Day03.part1(puzzleInputGmail))
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(93729253, Day03.part2(puzzleInputGitHub))
        assertEquals(80747545, Day03.part2(puzzleInputGmail))
    }

    @Test
    fun `Example - Part 1`() {
        assertEquals(161, Day03.part1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"))
    }

    @Test
    fun `Example - Part 2`() {
        assertEquals(48, Day03.part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }

    companion object {
        val puzzleInputGitHub = readPuzzleLines("aoc2024/day03-gh").joinToString("")
        val puzzleInputGmail = readPuzzleLines("aoc2024/day03-gm").joinToString("")
    }
}
