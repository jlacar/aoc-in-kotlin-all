package lacar.junilu

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val puzzleInput = readResource("day02")
    .map { line ->
        line.split("x").map { it.toInt() }.toIntArray()
    }

class Day02Test {

    @Nested
    inner class Samples {

        @ParameterizedTest(name = "{0}x{1}x{2} should have an area of {3}")
        @CsvSource(
            "2, 3, 4, 58",
            "1, 1, 10, 43"
        )
        fun `Part 1`(w: Int, l: Int, h: Int, expectedArea: Int) {
            val dimensions = listOf(intArrayOf(w, l, h))
            assertEquals(expectedArea, Day02(dimensions).part1())
        }

        @ParameterizedTest(name = "{0}x{1}x{2} needs {3} length of ribbon")
        @CsvSource(
            "2, 3, 4, 34",
            "1, 1, 10, 14",
        )
        fun `Part 2`(w: Int, l: Int, h: Int, expectedLength: Int) {
            val dimensions = listOf(intArrayOf(w, l, h))
            assertEquals(expectedLength, Day02(dimensions).part2())
        }
    }

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(1588178, Day02(puzzleInput).part1())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(3783758, Day02(puzzleInput).part2())
        }
    }
}
