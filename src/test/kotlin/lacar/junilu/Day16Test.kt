package lacar.junilu

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private val puzzleInputDay16 = readPuzzleInput("day16")
private val puzzleInputDay16Gh = readPuzzleInput("day16-gh")

class Day16Test {
    @Test
    fun `Part 1 - SOLVED`() {
        assertEquals(40, Day16.using(puzzleInputDay16).probableAuntSue())
        assertEquals(103, Day16.using(puzzleInputDay16Gh).probableAuntSue())
    }

    @Test
    fun `Part 2 - SOLVED `() {
        assertEquals(241, Day16.using(puzzleInputDay16).realAuntSue())
        assertEquals(405, Day16.using(puzzleInputDay16Gh).realAuntSue())
    }
}