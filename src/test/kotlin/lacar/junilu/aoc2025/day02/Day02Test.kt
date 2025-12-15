package lacar.junilu.aoc2025.day02

import lacar.junilu.readPuzzleLines
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun `Github input solution - Parts 1 & 2`() {
        val puzzle = using(readPuzzleLines("aoc2025/day02-gh").first())
        assertEquals(23560874270, puzzle.part1())
        assertEquals(44143124633, puzzle.part2())
    }

    @Test
    fun `GMail input solution - Parts 1 & 2`() {
        val puzzle = using(readPuzzleLines("aoc2025/day02-gm").first())
        assertEquals(18595663903, puzzle.part1())
        assertEquals(19058204438, puzzle.part2())
    }

    @Test
    fun `Example input`() {
        val puzzle = using(example)
        assertEquals(1227775554L, puzzle.part1())
        assertEquals(4174379265L, puzzle.part2())
    }

    private val example =
        """11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"""

    companion object {
        fun using(input: String): Day02 {
            val rangeStrings = input.split(",")
            val idRanges = rangeStrings.map { rangeString ->
                val (start, end) = rangeString.split("-").map(String::toLong)
                start..end
            }
            return Day02(idRanges)
        }
    }
}