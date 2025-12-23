package lacar.junilu.aoc2025.day05

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day05Test {
    @Test
    fun `Solutions for Github input`() {
        val solution = using(readPuzzleLines("aoc2025/day05-gh"))

        assertEquals(840, solution.part1())
        assertEquals(359913027576322L, solution.part2())
    }

    @Test
    fun `Solutions for Gmail input`() {
        val solution = using(readPuzzleLines("aoc2025/day05-gm"))

        assertEquals(509, solution.part1())
        assertEquals(336790092076620L, solution.part2())
    }

    @Test
    fun `Example - Part 1 & 2`() {
        val input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32""".trimIndent()

        val example = using(input.lines())

        assertEquals(3, example.part1())
        assertEquals(14, example.part2())
    }

}

// region Parsing input

private fun using(lines: List<String>): Day05 {
    val blank = lines.indexOfFirst { it.isBlank() }
    return Day05(idRangesFrom(lines.take(blank)), idsFrom(lines.drop(blank + 1)))
}

private fun idsFrom(lines: List<String>): List<Long> =
    lines.map { it.toLong() }

private fun idRangesFrom(lines: List<String>): List<LongRange> =
    lines.map { it.split("-").map { it.toLong() }.let { (start, end) -> start..end } }

// endregion