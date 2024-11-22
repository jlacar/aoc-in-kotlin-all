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

    //    private lateinit var turingLock: TuringLock
    private val turingLock = TuringLock()

    @Nested
        inner class Solution {
            @Test
            fun `Part 1`() {
            }
        }

    @Nested
    inner class Examples {
        @Test
        fun testSomeFunctionality() {

            assertNotNull(turingLock)
        }
    }
}

private fun puzzleInput(fileName: String = "day23"): List<Instruction> {
    readPuzzleInput(fileName)
}