package lacar.junilu.aoc2024.day13

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {
    private val part2Correction = 10_000_000_000_000L

    @Test
    fun `Solution - Part 1`() {
        assertEquals(37128L, Day13.using(puzzleInputForGitHub).tokensToWinAllPrizes())
        assertEquals(30973L, Day13.using(puzzleInputForGmail).tokensToWinAllPrizes())
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(74914228471331L, Day13.using(puzzleInputForGitHub, part2Correction).tokensToWinAllPrizes())
        assertEquals(95688837203288L, Day13.using(puzzleInputForGmail, part2Correction).tokensToWinAllPrizes())
    }

    @Test
    fun `Example - Part 1 `() {
        assertEquals(480L, Day13.using(fourClawMachines).tokensToWinAllPrizes())
    }

    @Test
    fun `Example - Part 2 `() {
        assertEquals(875318608908L, Day13.using(fourClawMachines, part2Correction).tokensToWinAllPrizes())
    }

    companion object {
        private val puzzleInputForGmail = readPuzzleInput("aoc2024/day13-gm")
        private val puzzleInputForGitHub = readPuzzleInput("aoc2024/day13-gh")

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