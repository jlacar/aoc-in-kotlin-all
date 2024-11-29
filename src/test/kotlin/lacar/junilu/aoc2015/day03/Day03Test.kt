package lacar.junilu.aoc2015.day03

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private val puzzleInputDay03forGitHub = readPuzzleInput("aoc2015/day03-gh").first()

class
Day03Test {

    @Nested
    inner class Solution {
        @Test
        fun part1() {
            Assertions.assertEquals(2565, Day03(puzzleInputDay03forGitHub).housesSantaVisited())
        }

        @Test
        fun part2() {
            Assertions.assertEquals(2639, Day03(puzzleInputDay03forGitHub).housesSantaOrRobotSantaVisited())
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
            Assertions.assertEquals(expectedCount, Day03(directions).housesSantaVisited())
        }

        @ParameterizedTest(name = "{0} should visit {1} houses")
        @CsvSource(
            "^v, 3",
            "^>v<, 3",
            "^v^v^v^v^v, 11",
        )
        fun part2(directions: String, expectedCount: Int) {
            Assertions.assertEquals(expectedCount, Day03(directions).housesSantaOrRobotSantaVisited())
        }
    }
}