package lacar.junilu.aoc2015.day24

import lacar.junilu.combinations
import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
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

        @Test
        fun `explore solution`() {
            "balanced weight: ${puzzleInputForGitHub.balancedWeight()}".println()

            val groups = puzzleInputForGitHub.combinations(6)
                .filter { it.sum() == puzzleInputForGitHub.balancedWeight() }

            groups.minOf {
                "size = ${it.size}, $it".println()
                val longs = it.map(Int::toLong)
                longs.reduce { acc, i -> acc * i }
            }.also { "min = $it".println() }

//            for (k in 9..12) {
//                val groups = puzzleInput2015Day24.combinations(k)
//                    .filter { it.sum() == puzzleInput2015Day24.balancedWeight }
//                    .first()

//                println("k = $k, smallest group: ${groups.size}")

//                groups.map { it.map(Int::toLong).product() }
//                    .min()
//                    .println()
//            }
//                groups.minOf { it.product() }.let { println(it) }

//                    .minOf { it.reduce { acc, l -> acc * l } }
//                    .forEach { val rem = puzzleInput2015Day24 - it; "QE = ${it.product()} $it ${it.sum()} $rem ${rem.sum()}".println() }
//                    .minOf { it.product() }
        }
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