package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day10Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(329356, Day10().lookSay("3113322113", 40).length)
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(4666278, Day10().lookSay("3113322113", 50).length)
        }
    }

    @ParameterizedTest(name = "lookSay({0}).times(1) is {1}")
    @CsvSource(
        "211, 1221",
        "1, 11",
        "11, 21",
        "21, 1211",
        "1211, 111221",
        "111221, 312211"
    )
    fun examples(input: String, expected: String) {
        assertEquals(expected, Day10().say(input))
    }
}