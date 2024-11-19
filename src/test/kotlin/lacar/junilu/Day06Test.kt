package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private val puzzleInputDay06 = readPuzzleInput("day06")
private val puzzleInputDay06Gh = readPuzzleInput("day06-gh")

class Day06Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(543903, Day06(puzzleInputDay06).lightsLit())
            assertEquals(377891, Day06(puzzleInputDay06Gh).lightsLit())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(14687245, Day06(puzzleInputDay06).totalBrightness())
            assertEquals(14110788, Day06(puzzleInputDay06Gh).totalBrightness())
        }
    }

    @ParameterizedTest(name = "Instructions {0} -> expect {1} lights to be on")
    @MethodSource("lacar.junilu.Day06Test#part1Examples")
    fun `Part 1 examples`(input: List<String>, expected: Int) {
        assertEquals(expected, Day06(input).lightsLit())
    }

    companion object {
        @JvmStatic
        fun part1Examples() = Stream.of(
            Arguments.of("turn on 0,0 through 999,999".lines(), 1_000_000),

            Arguments.of(
                """
                turn on 499,0 through 500,999
                turn off 499,499 through 500,500
                turn off 498,0 through 498,999""".trimIndent().lines(), 1_996),

            Arguments.of("toggle 0,0 through 1,1".lines(), 4),

            Arguments.of("toggle 0,0 through 999,999".lines(), 1_000_000),
        )
    }
}