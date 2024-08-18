package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

private const val puzzleInput = 29_000_000

class Day20Test {
    @Nested
    inner class Solution {
        @Test
        fun `Solution for unlimited houses visited by each elf`() {
            assertEquals(665280, Day20(puzzleInput, 10).firstToGetAsManyPresents())
        }

        @Test
        fun `Solution for up to 50 houses visited by each elf`() {
            assertEquals(705600, Day20(puzzleInput, 11, 50).firstToGetAsManyPresents())
        }
    }

    @Nested
    inner class Examples {
        @ParameterizedTest(name = "House {1} is first to get at least {0} presents")
        @CsvSource(
            "120, 6",
            "150, 8",
        )
        fun `Examples when unlimited houses visited by each elf`(numberOfPresents: Int, expectedHouse: Int) {
            assertEquals(expectedHouse, Day20(numberOfPresents, 10).firstToGetAsManyPresents())
        }

        /*
         * Limit 2 visits per elf
         * House Presents
         *   1   (11)
         *   2   (33) 11 + 22
         *   3   (33)
         *   4   (66) 22 + 44
         *   5   (55)
         *   6   (99) 33 + 66
         *   7   (77)
         *   8   (132) 44 + 88
         *   9   (99)
         */
        @ParameterizedTest(name = "House {1} is first to get at least {0} presents")
        @CsvSource(
            "33, 2",
            "99, 6"
        )
        fun `Examples when limited to 2 houses visited by each elf`(numberOfPresents: Int, expectedHouse: Int) {
            assertEquals(expectedHouse, Day20(numberOfPresents, 11, 2).firstToGetAsManyPresents())
        }
    }
}