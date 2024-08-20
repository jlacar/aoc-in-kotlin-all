package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private val puzzleInput = readResource("day07")

class Day07Test {

    @Nested
    inner class Solutions {

        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(3176, Day07(puzzleInput).signalOnWireA())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(14710, Day07(puzzleInput).signalOnWireAAfterReplacingB())
        }
    }

    companion object {
        @JvmStatic
        fun part1Examples() = Stream.of(
            Arguments.of(
                """
                123 -> a
                """.trimIndent().lines(), 123),

            Arguments.of(
                """
                x -> a
                123 -> x
                """.trimIndent().lines(), 123),

            Arguments.of(
                """
                NOT x -> a
                123 -> x
                """.trimIndent().lines(), 65412),

            Arguments.of(
                """
                NOT y -> a
                456 -> y
                """.trimIndent().lines(), 65079),

            Arguments.of(
                """
                x AND y -> a
                123 -> x
                456 -> y
                """.trimIndent().lines(), 72),

            Arguments.of(
                """
                1 AND x -> a
                123 -> x
                """.trimIndent().lines(), 1),

            Arguments.of(
                """
                1 AND x -> a
                456 -> x
                """.trimIndent().lines(), 0),

            Arguments.of(
                """
                x OR y -> a
                123 -> x
                456 -> y
                """.trimIndent().lines(), 507),

            Arguments.of(
                """
                x LSHIFT 2 -> a
                123 -> x
                """.trimIndent().lines(), 492),

            Arguments.of(
                """
                y RSHIFT 2 -> a
                456 -> y
                """.trimIndent().lines(), 114),
        )
    }

    @Nested
    inner class Samples {
        @ParameterizedTest(name = "{0} -> {1}")
        @MethodSource("lacar.junilu.Day07Test#part1Examples")
        fun `Part 1`(input: List<String>, expectedDiff: Int) {
            assertEquals(expectedDiff, Day07(input).signalOnWireA())
        }
    }
}