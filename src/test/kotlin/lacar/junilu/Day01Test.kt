package lacar.junilu

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val day01 = Day01.using(readResource("day01"))

class Day01Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(280, day01.lastFloor())
        }

        @Test
        fun part2() {
            assertEquals(1797, day01.positionOfFirstTimeInBasement())
        }
    }

    @Nested
    inner class Examples {
        @ParameterizedTest(name = "{1} with directions {0}")
        @CsvSource(
            "(()),     0",
            "()(),     0",
            "(((,      3",
            "(()(()(,  3",
            "))(((((,  3",
            "()),     -1",
            "))(,     -1",
            "))),     -3",
            ")())()), -3",
        )
        fun `Last floor should be`(input: String, expectedLevel: Int) {
            assertEquals(expectedLevel, Day01(input).lastFloor())
        }

        @ParameterizedTest(name = "{1} with directions {0}")
        @CsvSource(
            "),     1",
            "()()), 5",
            "()))())(()), 3",
        )
        fun `Position for first time in basement should be`(input: String, expectedPosition: Int) {
            assertEquals(expectedPosition, Day01(input).positionOfFirstTimeInBasement())
        }
    }
}