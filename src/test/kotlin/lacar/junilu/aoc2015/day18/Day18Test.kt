package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInputDay18 = readPuzzleInput("aoc2015/day18-gm")
private val puzzleInputDay18Gh = readPuzzleInput("aoc2015/day18-gh")

class Day18Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(814, Day18.using(puzzleInputDay18, 100).lightsOnAfterAnimation())
            assertEquals(821, Day18.using(puzzleInputDay18Gh, 100).lightsOnAfterAnimation())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(924, Day18.using(puzzleInputDay18, 100).lightsOnAfterAnimationWithCornersAlwaysOn())
            assertEquals(886, Day18.using(puzzleInputDay18Gh, 100).lightsOnAfterAnimationWithCornersAlwaysOn())
        }
    }

    @Nested
    inner class Examples {
        @Test
        fun `Using standard GoL rules`() {
            val config =
                """
                .#.#.#
                ...##.
                #....#
                ..#...
                #.#..#
                ####..
                """.trimIndent().lines()
            assertEquals(4, Day18.using(config, 4).lightsOnAfterAnimation())
        }

        @Test
        fun `Using modified rules - corners always on`() {
            val config =
                """
                ##.#.#
                ...##.
                #....#
                ..#...
                #.#..#
                ####.#
                """.trimIndent().lines()
            assertEquals(17, Day18.using(config, 5).lightsOnAfterAnimationWithCornersAlwaysOn())
        }
    }
}