package lacar.junilu.aoc2015.day25

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day25Test {

    @Test
    fun `get fifty stars for AoC 2015`() {
        val puzzleInput = readPuzzleLines("aoc2015/day25-gh").first()
        val (r, c) = puzzleInput.drop(80).split(", ")
        val row = r.toInt()
        val col = c.split(" ")[1].dropLast(1).toInt()

        assertEquals(19980801, Day25.codeAt(row, col))
    }

    @Nested
    inner class `Smaller Chunks` {

        @ParameterizedTest
        @CsvSource(useHeadersInDisplayName = true, textBlock = """
            row, col, ord
            1,   1,   1
            2,   1,   2
            3,   1,   4
            4,   1,   7
            10,  1,   46
            2,   2,   5
            5,   6,   51
            2,   9,   54
            6,   4,   40
            4,   5,   33
            1,  10,   55""")
        fun ordinal(row: Int, col: Int, ord: Int) {
            assertEquals(ord, ordinalFor(row, col))
            assertEquals(ord, recursiveOrdFor(row, col))
        }

        @ParameterizedTest
        @CsvSource(useHeadersInDisplayName = true, textBlock = """
            row, col, code
            1,   1,   20151125
            1,   2,   18749137
            1,   3,   17289845
            1,   4,   30943339
            1,   5,   10071777
            1,   6,   33511524
            2,   1,   31916031
            3,   1,   16080970
            4,   1,   24592653
            5,   1,   77061
            6,   1,   33071741""")
        fun `codes sequence`(row: Int, col: Int, code: Long) {
            assertEquals(code, Day25.codeAt(row, col))
        }
    }
}