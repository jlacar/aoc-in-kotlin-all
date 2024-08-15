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
            assertEquals(280, day01.part1())
        }

        @Test
        fun part2() {
            assertEquals(1797, day01.part2())
        }
    }

    @Nested
    inner class Examples {
        @ParameterizedTest(name = "{0} should give level {1}")
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
        fun part1(input: String, expectedLevel: Int) {
            assertEquals(expectedLevel, Day01(input).part1())
        }

        @ParameterizedTest(name = "{0} should give level {1}")
        @CsvSource(
            "),     1",
            "()()), 5",
            "()))())(()), 3",
        )
        fun part2(input: String, expectedPosition: Int) {
            assertEquals(expectedPosition, Day01(input).part2())
        }
    }
}