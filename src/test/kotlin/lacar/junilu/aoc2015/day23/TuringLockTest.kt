package lacar.junilu.aoc2015.day23

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLockTest {

    @Nested
    inner class SolutionPart1 {
        private val lock = TuringLock()

        @Test
        fun `Part 1 - gmail`() {
            assertEquals(255, lock.run(gmailProgram).b)
        }

        @Test
        fun `Part 1 - github`() {
            assertEquals(307, lock.run(githubProgram).b)
        }
    }

    @Nested
    inner class SolutionPart2 {
        private val lock = TuringLock(initialLockState(a = 1))

        @Test
        fun `Part 2 - gmail`() {
            assertEquals(334, lock.run(gmailProgram).b)
        }

        @Test
        fun `Part 2 - github`() {
            assertEquals(160, lock.run(githubProgram).b)
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
            """.trimIndent().lines().map { Instruction.parse(it) }

            val lock = TuringLock()
            assertEquals(2, lock.run(program).a)
        }
    }
}

private val gmailProgram = puzzleInputDay23("day23")
private val githubProgram = puzzleInputDay23("day23-gh")

private fun puzzleInputDay23(fileName: String) = readPuzzleInput(fileName).map { line -> Instruction.parse(line) }