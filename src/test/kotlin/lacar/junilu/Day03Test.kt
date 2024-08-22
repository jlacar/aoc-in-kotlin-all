package lacar.junilu

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val day03 = Day03.using(readResource("day03"))

class
Day03Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            assertEquals(2565, day03.housesSantaVisited())
        }

        @Test
        fun part2() {
            assertEquals(2639, day03.housesSantaOrRoboSantaVisited())
        }
    }

    @Nested
    inner class Examples {

        @ParameterizedTest(name = "{0} should visit {1} houses")
        @CsvSource(
            ">, 2",
            "^>v<, 4",
            "^v^v^v^v^v, 2",
        )
        fun part1(directions: String, expectedCount: Int) {
            assertEquals(expectedCount, Day03(directions).housesSantaVisited())
        }

        @ParameterizedTest(name = "{0} should visit {1} houses")
        @CsvSource(
            "^v, 3",
            "^>v<, 3",
            "^v^v^v^v^v, 11",
        )
        fun part2(directions: String, expectedCount: Int) {
            assertEquals(expectedCount, Day03(directions).housesSantaOrRoboSantaVisited())
        }
    }
}