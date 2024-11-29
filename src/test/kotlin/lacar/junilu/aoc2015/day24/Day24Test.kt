package lacar.junilu.aoc2015.day24

import lacar.junilu.combinations
import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

private val puzzleInputForGitHub = readPuzzleInput("aoc2015/day24-gh").map { it.toInt() }

class Day24Test {

    private fun List<Int>.balancedWeight(compartments: Int = 3) = sum() / compartments
    private val List<Int>.isValidInput get() = sum() % 3 == 0

    private fun List<Int>.isBalancedWeightGroup(): Boolean {
        val rest = this - this.toSet()
        return this.sum() == balancedWeight() && rest.sum() == balancedWeight() * 2
    }

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
    inner class `Solution - Part 1` {
        @Test
        fun `for GitHub input`() {
            assertEquals(11266889531, Day24(puzzleInputForGitHub).smallestQuantumEntanglement())
        }

        // Exploratory code was in main (34ba86877bc8bc19430ec888597a542532d8ebed)
    }

    @Nested
    inner class `Part 1 Example` {
        private val numbers = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11).reversed()

        @Test
        fun `example list is valid`() {
            assertTrue(numbers.isValidInput)
        }

        @Test
        fun `combinations of 2 should not be empty`() {
            numbers.combinations(2)
                .filter { it.isBalancedWeightGroup() }
                .forEach { println("$it ${it.sum()} ${numbers - it} ${(numbers - it).sum()}") }
        }
    }
}