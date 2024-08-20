package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

private val puzzleInput = readResource("Day05")

class Day05Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(258, Day05(puzzleInput).countOfNiceStrings())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(53, Day05(puzzleInput).countOfNicerStrings())
        }
    }

    @Nested
    inner class Examples {

        @Nested
        inner class Nice {

            @ParameterizedTest
            @ValueSource(
                strings = [
                    "ugknbfddgicrmopn",
                    "aaa",
                ]
            )
            fun `Part 1 - Nice strings`(input: String) {
                assertEquals(1, Day05(listOf(input)).countOfNiceStrings())
            }

            @ParameterizedTest
            @ValueSource(
                strings = [
                    "qjhvhtzxzqqjkmpb",
                    "xxyxx"
                ]
            )
            fun `Part 2 - Nicer strings`(input: String) {
                assertEquals(1, Day05(listOf(input)).countOfNicerStrings())
            }
        }

        @Nested
        inner class Naughty {

            @ParameterizedTest
            @ValueSource(
                strings = [
                    "jchzalrnumimnmhp",
                    "haegwjzuvuyypxyu",
                    "dvszwmarrgswjxmb"
                ]
            )
            fun `Part 1 - Naughty strings`(input: String) {
                assertEquals(0, Day05(listOf(input)).countOfNiceStrings())
            }

            @ParameterizedTest
            @ValueSource(
                strings = [
                    "uurcxstgmygtbstg",
                    "ieodomkazucvgmuy",
                ]
            )
            fun `Part 2 - Naughty strings`(input: String) {
                assertEquals(0, Day05(listOf(input)).countOfNicerStrings())
            }
        }
    }
}