package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private val puzzleInputDay12 = readPuzzleLines("aoc2015/day12").first()

class Day12Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(111754, Day12(puzzleInputDay12).sumOfAllNumbersInDocument())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(65402, Day12(puzzleInputDay12).sumOfAllNumbersExcludingObjectsWithRed())
        }
    }

    @Nested
    inner class Examples {
        @ParameterizedTest(name = "{0} -> expect {1}")
        @MethodSource("lacar.junilu.Day12Test#part1Arguments")
        fun `Sum of all numbers in document`(input: String, expectedSum: Int) =
            assertEquals(expectedSum, Day12(input).sumOfAllNumbersInDocument())

        @ParameterizedTest(name = "{0} -> expect {1}")
        @MethodSource("lacar.junilu.Day12Test#part2Arguments")
        fun `Sum of all numbers excluding objects with 'red'`(jsonString: String, expectedSum: Int) =
            assertEquals(expectedSum, Day12(jsonString).sumOfAllNumbersExcludingObjectsWithRed())
    }

    companion object {
        @JvmStatic
        fun part1Arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("""{"a": [1,2,3]}""", 6),
                Arguments.of("""{"a":2,"b":4}""", 6),
                Arguments.of("[[[3]]]", 3),
                Arguments.of("""{"a":{"b":4},"c":-1}""", 3),
                Arguments.of("""{"a":[-1,1]}""", 0),
                Arguments.of("""[-1,{"a":1}]""", 0),
                Arguments.of("{}", 0),
                Arguments.of("[]", 0),

                Arguments.of(
                    """
                    { "a": [1, 2, 3], 
                      "b": 4, 
                      "c": "red" }""".trimIndent(), 10
                ),

                Arguments.of(
                    """
                    { "a": [1, 2, 3], 
                      "b": 4, 
                      "c": "yellow", 
                      "d": { 
                        "e": "red", 
                        "f": 5, 
                        "g": 6 
                      }, 
                      "h": { 
                        "b": 7, 
                        "c": "yellow", 
                        "d": [0, 1, 3] 
                      } 
                    }""".trimIndent(), 32
                )
            )
        }

        @JvmStatic
        fun part2Arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    """
                    { "description": "has 'red' object value", 
                      "a": [1, 2, 3], 
                      "b": 4, 
                      "c": "red"
                    } """.trimIndent(), 0
                ),

                Arguments.of(
                    """
                    { "description": "no 'red' object value",
                      "a": [1, 2, 3], 
                      "b": 4, 
                      "c": "yellow"
                    } """.trimIndent(), 10
                ),

                Arguments.of(
                    """
                    { "description": "has 'red' in array, and in objects",
                      "a": ["these should be summed", 1, 2, 3, "red"], 
                      "b": 4, 
                      "c": "yellow",
                      "d": {
                        "description": "these should not be summed",
                        "e": "red",
                        "f": 5,
                        "g": 6
                      },
                      "h": {
                        "b": 7, 
                        "c": "yellow",
                        "d": ["description: these should be summed", 0, 1, 3, "red"],
                        "e": {
                          "description": "these should not be summed",
                          "i": 1,
                          "j": 55,
                          "k": "red"
                        }
                      }
                    } """.trimIndent(), 21
                ),
            )
        }
    }
}
