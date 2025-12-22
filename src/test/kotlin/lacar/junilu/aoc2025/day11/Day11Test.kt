package lacar.junilu.aoc2025.day11

import lacar.junilu.readPuzzleLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `Example - Part 1`() {
        val example = using(
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
            iii: out""".trimIndent().lines())

        assertEquals(5, example.part1())
    }

    @Test
    fun `Example - Part 2`() {
        val example = using(
            """
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out""".trimIndent().lines())

        assertEquals(2, example.part2())
    }

    @Test
    fun `GitHub input solution`() {
        val puzzle = using(readPuzzleLines("aoc2025/day11-gh"))

        assertEquals(500, puzzle.part1())
        assertEquals(0, puzzle.part2())
    }

    private fun using(input: List<String>) = Day11(
        input
            .map { it.split(": ") }
            .associate { (device, outputs) -> device to outputs.split(" ") }
    )
}