package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInputDay14 = readPuzzleInput("day14")
private val puzzleInputDay14Gh = readPuzzleInput("day14-gh")

class Day14Test {
    @Nested
    inner class Solution {
        @Test
        fun `Part 1 - SOLVED`() {
            assertEquals(2660, Day14.using(puzzleInputDay14, 2503).distanceTraveledByWinner())
            assertEquals(2696, Day14.using(puzzleInputDay14Gh, 2503).distanceTraveledByWinner())
        }

        @Test
        fun `Part 2 - SOLVED`() {
            assertEquals(1256, Day14.using(puzzleInputDay14, 2503).pointsEarnedByWinner())
            assertEquals(1084, Day14.using(puzzleInputDay14Gh, 2503).pointsEarnedByWinner())
        }
    }

    @Nested
    inner class Examples {
        @Test
        fun `Comet wins`() {
            val sample = Day14(listOf(
                Day14.Reindeer(speed = 14, flightTime = 10, restTime = 127),
                Day14.Reindeer(speed = 16, flightTime = 11, restTime = 162)
            ), 1000)
            assertEquals(1120, sample.distanceTraveledByWinner())
        }
        @Test
        fun `Dancer wins`() {
            val sample = Day14(listOf(
                Day14.Reindeer(speed = 14, flightTime = 10, restTime = 127),
                Day14.Reindeer(speed = 16, flightTime = 11, restTime = 162)
            ), 1000)
            assertEquals(689, sample.pointsEarnedByWinner())
        }
    }
}