package lacar.junilu.aoc2025.day10

import lacar.junilu.aoc2025.day10.Day10.Companion.using
import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day10Test {

    @Test
    fun `Examples - Part 1 & 2`() {
        val example = using(
            """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}""".trimIndent().lines())

        assertEquals(7, example.part1())
    }

    @Test
    fun `GitHub input solution - Part 1`() {
        val puzzle = using(readPuzzleLines("aoc2025/day10-gh"))

        assertEquals(432, puzzle.part1())
    }

}