package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

private val puzzleInput = readResource("Day05")

class Day05Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(258, Day05(puzzleInput).howManyNiceStringsPart1())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(53, Day05(puzzleInput).howManyNiceStringsPart2())
        }
    }

    @Nested
    inner class Examples {

        @Nested
        inner class Part1 {
            @ParameterizedTest(name = "{0}")
            @ValueSource(
                strings = [
                    "ugknbfddgicrmopn",
                    "aaa",
                ]
            )
            fun `These are nice `(input: String) {
                assertEquals(1, Day05(listOf(input)).howManyNiceStringsPart1())
            }

            @ParameterizedTest(name = "{0} - because it {1}")
            @CsvSource(
                "jchzalrnumimnmhp, has no double letter",
                "haegwjzuvuyypxyu, contains the string 'xy'",
                "dvszwmarrgswjxmb, contains only one vowel",
            )
            fun `These are naughty `(input: String, reason: String) {
                assertEquals(0, Day05(listOf(input)).howManyNiceStringsPart1())
            }
        }

        @Nested
        inner class Part2 {
            @ParameterizedTest(name = "{0} - because it has {1}")
            @CsvSource(
                "qjhvhtzxzqqjkmpb, pair that appears twice (qj) and repeat with letter in between (zxz)",
                "xxyxx, pair that appears twice (xx) and repeat with letter in between (xyx)",
            )
            fun `These are nice `(input: String, reason: String) {
                assertEquals(1, Day05(listOf(input)).howManyNiceStringsPart2())
            }

            @ParameterizedTest(name = "{0} - because it {1}")
            @CsvSource(
                "uurcxstgmygtbstg, has a repeating pair (tg) but no repeat with a letter in between",
                "ieodomkazucvgmuy, has repeat with a letter in between (odo) but no repeating pair",
            )
            fun `These are naughty `(input: String, reason: String) {
                assertEquals(0, Day05(listOf(input)).howManyNiceStringsPart2())
            }
        }
    }
}