package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private val puzzleInput = readResource("day19")

class Day19Test {

    @Nested
    inner class Part1 {
        @Test
        fun examples() {
            assertEquals(7, Day19.using(
                """
                    H => HO
                    H => OH
                    O => HH
                    
                    HOHOHO
                """.trimIndent().lines()
            ).part1())
        }

        @Test
        fun solution() {
            assertEquals(535, Day19.using(puzzleInput).part1())
        }
    }

    @Nested
    inner class Part2 {

        @Test
        fun example() {
            assertEquals(6, Day19.using(
                """
                    e => H
                    e => O
                    H => HO
                    H => OH
                    O => HH
                    
                    HOHOHO
                """.trimIndent().lines()
            ).part2())
        }

        @Test
        fun solution() {
            assertEquals(212, Day19.using(puzzleInput).part2())
        }
    }
}