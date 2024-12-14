package lacar.junilu.aoc2024.day13

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun `Example - Part 1 `() {
//        assertEquals(2, Day13.using(fourClawMachines).part1())
        assertEquals(480, Day13.using(fourClawMachines).part1())
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(0, puzzleUsingGitHubInput.part1())
    }

    companion object {
        private val puzzleUsingGitHubInput = Day13.using(readPuzzleInput("aoc2024/day13-gh"))
//        private val puzzleUsingGmailInput = Day13.using(readPuzzleInput("aoc2024/day13-gm"))

        private val fourClawMachines = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """.trimIndent().lines()
    }
}