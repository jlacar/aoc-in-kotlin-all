package lacar.junilu.aoc2015.day23

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val gmailProgram = readPuzzleInput("day23")
private val githubProgram = readPuzzleInput("day23-gh")

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLockTest {

    private fun runLockProgram(source: List<String>, a: Int = 0): TuringLock {
        val lock = TuringLock.load(source).initialize(a = a)
        lock.runProgram()
        return lock
    }

    @Nested
    inner class SolutionPart1 {
        @Test
        fun `Part 1 - gmail`() {
            assertEquals(255, runLockProgram(gmailProgram).b)
        }

        @Test
        fun `Part 1 - github`() {
            assertEquals(307, runLockProgram(githubProgram).b)
        }
    }

    @Nested
    inner class SolutionPart2 {
        @Test
        fun `Part 2 - gmail`() {
            assertEquals(334, runLockProgram(gmailProgram, a = 1).b)
        }

        @Test
        fun `Part 2 - github`() {
            assertEquals(160, runLockProgram(githubProgram, a = 1).b)
        }
    }

    @Nested
    inner class Examples {
        @Test
        fun `Part 1 example with four instructions`() {
            val program = """
                inc a
                jio a, 2
                tpl a
                inc a
            """.trimIndent().lines()

            assertEquals(2, runLockProgram(program).a)
        }
    }
}