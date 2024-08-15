package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class Day20Test {
    @Nested
    inner class Part1 {
        @ParameterizedTest(name = "At least {0} at house {1}")
        @CsvSource(
            "120, 6",
            "150, 8",
        )
        fun example(minPresents: Int, house: Int) {
            assertEquals(house, Day20(minPresents).part1())
        }

        @Test
        fun solution() {
            assertEquals(665280, Day20(29_000_000).part1())
        }
    }
}