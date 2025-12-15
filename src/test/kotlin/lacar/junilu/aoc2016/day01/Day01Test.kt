package lacar.junilu.aoc2016.day01

import lacar.junilu.common.Direction
import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day01Test {
    @Test
    fun `Solution - Part 1`() {
        val instructions = readPuzzleLines("aoc2016/day01-gh").first()
        assertEquals(241, Day01.distanceTraveled(instructions))
    }

    @Test
    fun `Solution - Part 2`() {
        val instructions = readPuzzleLines("aoc2016/day01-gh").first()
        assertEquals(116, Day01.distanceToBunnyHQ(instructions))
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
        start,  afterLeft
        NORTH,  WEST
        SOUTH,  EAST
        WEST,   SOUTH
        EAST,   NORTH""")
    fun `direction facing after turning left`(start: Direction, afterLeft: Direction) {
        assertEquals(afterLeft, start.left)
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
        start,  afterRight
        NORTH,  EAST
        SOUTH,  WEST
        WEST,   NORTH
        EAST,   SOUTH""")
    fun `direction facing after turning right`(start: Direction, afterRight: Direction) {
        assertEquals(afterRight, start.right)
    }

    @Test
    fun `Part 1 Examples`() {
        assertAll(
            { assertEquals(5, Day01.distanceTraveled("R2, L3")) },
            { assertEquals(2, Day01.distanceTraveled("R2, R2, R2")) },
            { assertEquals(12, Day01.distanceTraveled("R5, L5, R5, R3")) },
        )
    }

    @Test
    fun `Part 2 Example`() {
        assertEquals(4, Day01.distanceToBunnyHQ("R8, R4, R4, R8"))
    }
}