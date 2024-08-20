package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private val puzzleInput = readResource("day08")

class Day08Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(1342, Day08(puzzleInput).decodedLengthDiff())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(2074, Day08(puzzleInput).encodedLengthDiff())
        }
    }

    @Nested
    inner class Samples {

        @ParameterizedTest(name = "{0} -> {1}")
        @MethodSource("lacar.junilu.Day08Test#decodeExamples")
        fun `Part 1 examples`(input: String, expectedDiff: Int) {
            assertEquals(expectedDiff, Day08(input.lines()).decodedLengthDiff())
        }

        @ParameterizedTest(name = "{0} -> {1}")
        @MethodSource("lacar.junilu.Day08Test#encodeExamples")
        fun `Part 2 examples`(input: String, expectedDiff: Int) {
            assertEquals(expectedDiff, Day08(input.lines()).encodedLengthDiff())
        }
    }

    companion object {
        @JvmStatic
        fun decodeExamples() = Stream.of(
            Arguments.of(
                """
                ""
                """.trimIndent(), 2 - 0),

            Arguments.of(
                """
                "v\xfb\"lgs\"kvjfywmut\x9cr"
                """.trimIndent(), 28 - 18),

            Arguments.of(
                """
                "h\\"
                """.trimIndent(),5 - 2),

            Arguments.of(
                """
                "abc"
                """.trimIndent(), 5 - 3),

            Arguments.of(
                """
                "aaa\"aaa"
                "aaa\"aa\"aa"
                """.trimIndent(), 10 - 7 + 13 - 9),

            Arguments.of(
                """
                "\x27"
                """.trimIndent(), 6 - 1),

            Arguments.of(
                """
                ""
                "abc"
                "aaa\"aaa"
                "\x27"
                """.trimIndent(), 12)
        )

        @JvmStatic
        fun encodeExamples() = Stream.of(
            Arguments.of(
                """
                ""
                """.trimIndent(), 6 - 2),

            Arguments.of(
                """
                "abc"
                """.trimIndent(), 9 - 5),

            Arguments.of(
                """
                "aaa\"aaa"
                """.trimIndent(), 16 - 10),

            Arguments.of(
                """
                "\x27"
                """.trimIndent(), 11 - 6),
        )
    }
}