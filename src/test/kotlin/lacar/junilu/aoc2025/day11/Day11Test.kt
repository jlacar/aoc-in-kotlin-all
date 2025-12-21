package lacar.junilu.aoc2025.day11

import lacar.junilu.readPuzzleLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `Examples - Part 1 & 2`() {
        val example =
            """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out""".trimIndent().lines()

        val puzzle = using(example)

        assertEquals(5, puzzle.part1())
    }

    @Test
    fun `GitHub input solution`() {
        val puzzle = using(readPuzzleLines("aoc2025/day11-gh"))

        assertEquals(500, puzzle.part1())
    }

    private fun using(input: List<String>) = Day11(
        input.map {
            it.split(": ").let { (fromDevice, toDevices) ->
                fromDevice to toDevices.split(" ")
            }
        }.toMap()
    )
}