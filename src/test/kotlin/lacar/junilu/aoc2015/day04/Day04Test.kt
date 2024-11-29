package lacar.junilu

import lacar.junilu.aoc2015.day04.Day04
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day04Test {

    @Nested
    inner class Solution {
        private val puzzleInput = "ckczppom"
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(117946, Day04(puzzleInput).lowestHashWith(5))
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(3938038, Day04(puzzleInput).lowestHashWith(6))
        }
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
        "abcdef, 609043",
        "pqrstuv, 1048970",
    )
    fun `Part 1 examples`(input: String, expected: Int) {
        assertEquals(expected, Day04(input).lowestHashWith(5))
    }
}
