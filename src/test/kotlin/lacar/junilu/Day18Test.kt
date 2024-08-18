package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInput = readResource("day18")

class Day18Test {

    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(814, Day18.using(puzzleInput, 100).part1())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(924, Day18.using(puzzleInput, 100).part2())
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
            assertEquals(4, Day18.using(config, 4).part1())
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
            assertEquals(17, Day18.using(config, 5).part2())
        }
    }
}