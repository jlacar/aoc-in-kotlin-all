package lacar.junilu.aoc2025.day09

import lacar.junilu.common.Point2D
import lacar.junilu.common.findInts
import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day09Test {
    @Test
    fun `Solutions for GitHub input`() {
        val puzzle = using(readPuzzleLines("aoc2025/day09-gh"))

        assertEquals(4777409595, puzzle.maxRectangularArea())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val puzzle = using(readPuzzleLines("aoc2025/day09-gm"))

//        assertEquals(4667642882, puzzle.maxRectangularArea())  // too low!
        assertEquals(4733727792, puzzle.maxRectangularArea())  // too low!
    }

    @Test
    fun `Examples - Parts 1 & 2`() {
        val example = using(
            """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3""".trimIndent().lines()
        )

        assertEquals(50, example.maxRectangularArea())
    }
}

private fun using(lines: List<String>) = Day09(
    lines.map { s ->
        s.findInts().let { Point2D(x = it.first(), y = it.last()) }
    }
)
