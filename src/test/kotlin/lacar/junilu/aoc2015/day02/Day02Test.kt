package lacar.junilu.aoc2015.day02

import io.kotest.core.annotation.DisplayName
import io.kotest.matchers.shouldBe
import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class Day02Test {

    @Nested
    @DisplayName("Solutions for GitHub input")
    inner class SolutionGH {
        private val solution = Day02.using(readPuzzleLines("aoc2015/day02-gh"))

        @Test
        fun `Part 1 - Wrapping paper needed`() {
            solution.sqFeetOfWrapper() shouldBe 1586300
        }

        @Test
        fun `Part 2 - Ribbon needed`() {
            solution.feetOfRibbon() shouldBe 3737498
        }
    }

    @Nested
    @DisplayName("Solutions for Gmail input")
    inner class SolutionGM {
        private val solution = Day02.using(readPuzzleLines("aoc2015/day02"))

        @Test
        fun `Part 1 - Wrapping paper needed`() {
            solution.sqFeetOfWrapper() shouldBe 1588178
        }

        @Test
        fun `Part 2 - Ribbon needed`() {
            solution.feetOfRibbon() shouldBe 3783758
        }
    }

    @Nested
    inner class Examples {

        @ParameterizedTest(name = "given a {0}x{1}x{2} box, it should need {3} sq feet of wrapping paper")
        @CsvSource(
            "2, 3, 4, 58",
            "1, 1, 10, 43"
        )
        fun `Part 1 - Wrapping paper needed `(w: Int, l: Int, h: Int, expectedArea: Int) {
            Day02(boxDimensions(w, l, h)).sqFeetOfWrapper() shouldBe expectedArea
        }

        @ParameterizedTest(name = "given a {0}x{1}x{2} box, it should need {3} feet of ribbon")
        @CsvSource(
            "2, 3, 4, 34",
            "1, 1, 10, 14",
        )
        fun `Part 2 - Ribbon needed `(w: Int, l: Int, h: Int, expectedLength: Int) {
            Day02(boxDimensions(w, l, h)).feetOfRibbon() shouldBe expectedLength
        }

        private fun boxDimensions(w: Int, l: Int, h: Int) = listOf(listOf(w, l, h))
    }
}