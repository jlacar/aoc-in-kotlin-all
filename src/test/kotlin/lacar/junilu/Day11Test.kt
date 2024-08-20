package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals("cqjxxyzz", Day11().nextPassword("cqjxjnds"))
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals("cqkaabcc", Day11().nextPassword("cqjxxyzz"))
        }
    }

    @Nested
    inner class Examples {

        @ParameterizedTest(name = "{0} increments to {1}")
        @CsvSource(
            "a, b",
            "z, a",
            "czz, daa",
            "wxyzz, wxzaa",
            "azwzzz, azxaaa"
        )
        fun `Increment letters with z wrapping back to a`(str: String, incrementStr: String) {
            assertEquals(incrementStr, Day11().incr(str))
        }

        @Test
        fun `Valid password`() {
            assertTrue(Day11().isValid("bbjjqqrs"))
        }

        @ParameterizedTest(name = "{0} is invalid because it {1}")
        @CsvSource(
            "iijklmmn, contains 'i' and 'l'",
            "aabccmno, contains 'o'",
            "abbceffg, has no straight three increasing letters",
            "abbcefgx, has only one pair, bb",
            "abbbcxyz, has no different non-overlapping pairs",
            "abbcdbbk, has no different non-overlapping pairs",
            "abbcdbbA, has the uppercase letter 'A'",
        )
        fun `Invalid passwords`(password: String, reason: String) {
            assertFalse(Day11().isValid(password))
        }

        @ParameterizedTest(name = "{0} -> {1}")
        @CsvSource(
            "abcdefgh, abcdffaa",
            "ghijklmn, ghjaabcc",
        )
        fun `Next valid password increments`(input: String, expected: String) {
            assertEquals(expected, Day11().nextPassword(input))
        }
    }
}