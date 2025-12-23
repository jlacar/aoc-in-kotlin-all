package lacar.junilu.aoc2025.day07

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day07Test {
    @Test
    fun `Solutions for GitHub input`() {
        val solution = Day07(readPuzzleLines("aoc2025/day07-gh"))

        assertEquals(1566, solution.beamSplits())
        assertEquals(5921061943075, solution.beamTimeLines())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val solution = Day07(readPuzzleLines("aoc2025/day07-gm"))

        assertEquals(1717, solution.beamSplits())
        assertEquals(231507396180012, solution.beamTimeLines())
    }

    @Test
    fun `Examples - Parts 1 & 2`() {
        val example = Day07(
            """
            .......S.......
            ...............
            .......^.......
            ...............
            ......^.^......
            ...............
            .....^.^.^.....
            ...............
            ....^.^...^....
            ...............
            ...^.^...^.^...
            ...............
            ..^...^.....^..
            ...............
            .^.^.^.^.^...^.
            ...............""".trimIndent().lines())

        assertEquals(21, example.beamSplits())
        assertEquals(40, example.beamTimeLines())
    }
}

