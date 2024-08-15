package lacar.junilu

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val day02 = Day02.using(readResource("day02"))

class Day02Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(1588178, day02.part1())
        }

        @Test
        fun part2() {
            assertEquals(3783758, day02.part2())
        }
    }

    @Nested
    inner class Examples {

        @ParameterizedTest(name = "{0}x{1}x{2} should have an area of {3}")
        @CsvSource(
            "2, 3, 4, 58",
            "1, 1, 10, 43"
        )
        fun part1(w: Int, l: Int, h: Int, expectedArea: Int) {
            val dimensions = listOf(intArrayOf(w, l, h))
            assertEquals(expectedArea, Day02(dimensions).part1())
        }

        @ParameterizedTest(name = "{0}x{1}x{2} needs {3} length of ribbon")
        @CsvSource(
            "2, 3, 4, 34",
            "1, 1, 10, 14",
        )
        fun part2(w: Int, l: Int, h: Int, expectedLength: Int) {
            val dimensions = listOf(intArrayOf(w, l, h))
            assertEquals(expectedLength, Day02(dimensions).part2())
        }
    }
}
