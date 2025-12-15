package lacar.junilu.aoc2015.day24

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

private val puzzleInputForGitHub = readPuzzleLines("aoc2015/day24-gh").map { it.toInt() }

class Day24Test {

    @Nested
    inner class Assumptions {
        @ParameterizedTest(name = "{0} compartments")
        @ValueSource(ints = [3, 4])
        fun `sum of all weights is divisible by number of compartments`(compartments: Int) {
            assertEquals(0, puzzleInputForGitHub.sum() % compartments)
        }

        @ParameterizedTest(name = "{0} compartments")
        @ValueSource(ints = [3, 4])
        fun `list of weights does not include balanced weight`(compartments: Int) {
            val balancedWeight = puzzleInputForGitHub.sum() / compartments
            assertFalse(puzzleInputForGitHub.contains(balancedWeight))
        }
    }

    @Nested
    inner class Solutions {
        @Test
        fun `Part 1 for GitHub input`() {
            assertEquals(11266889531, Day24(puzzleInputForGitHub).smallestQuantumEntanglement(3))
        }

        @Test
        fun `Part 2 for GitHub input`() {
            assertEquals(77387711, Day24(puzzleInputForGitHub).smallestQuantumEntanglement(4))
        }

        // Exploratory code was in main (34ba86877bc8bc19430ec888597a542532d8ebed)
    }

    @ParameterizedTest(name = "{0} compartments")
    @CsvSource("3, 99", "4, 44")
    fun `Part 1 and 2 - Example weights `(numberOfCompartments: Int, expectedQE: Long) {
        val day24 = Day24((1..5).toList() + (7..11).toList())
        assertEquals(expectedQE, day24.smallestQuantumEntanglement(numberOfCompartments))
    }
}