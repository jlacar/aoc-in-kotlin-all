package lacar.junilu.aoc2015.day01

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val puzzleDay01 = Day01(readPuzzleLines("aoc2015/day01").first())

class Day01Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            Assertions.assertEquals(280, puzzleDay01.lastFloor())
        }

        @Test
        fun part2() {
            Assertions.assertEquals(1797, puzzleDay01.positionOfFirstTimeInBasement())
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
            Assertions.assertEquals(expectedLevel, Day01(input).lastFloor())
        }

        @ParameterizedTest(name = "{1} with directions {0}")
        @CsvSource(
            "),     1",
            "()()), 5",
            "()))())(()), 3",
        )
        fun `Position for first time in basement should be`(input: String, expectedPosition: Int) {
            Assertions.assertEquals(expectedPosition, Day01(input).positionOfFirstTimeInBasement())
        }
    }
}