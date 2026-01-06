package lacar.junilu.aoc2015.day01

import lacar.junilu.readPuzzleText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val solution = Day01(readPuzzleText("aoc2015/day01"))

class Day01Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(280, solution.lastFloor())
        }

        @Test
        fun part2() {
            assertEquals(1797, solution.positionOfFirstTimeInBasement())
        }
    }

    @Nested
    inner class Examples {
        @ParameterizedTest(name = "given directions {0} last floor should be {1}")
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
        fun `Last floor `(input: String, expectedLevel: Int) {
            assertEquals(expectedLevel, Day01(input).lastFloor())
        }

        @ParameterizedTest(name = "given directions {0}, position should be {1}")
        @CsvSource(
            "),     1",
            "()()), 5",
            "()))())(()), 3",
        )
        fun `Position for first time in basement `(input: String, expectedPosition: Int) {
            assertEquals(expectedPosition, Day01(input).positionOfFirstTimeInBasement())
        }
    }
}