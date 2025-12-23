package lacar.junilu.aoc2025.day11

import lacar.junilu.readPuzzleLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `Solution for Part 1 using GitHub input`() {
        val solution = using(readPuzzleLines("aoc2025/day11-gh"))

        assertEquals(500, solution.pathCount("you", "out"))
    }

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

        assertEquals(5, example.pathCount("you", "out"))
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
    fun `Solution for Part 2 using GitHub input`() {
        val solution = using(readPuzzleLines("aoc2025/day11-gh"))

        assertEquals(0, solution.part2())
    }

    private fun using(input: List<String>) = Day11(
        input
            .map { it.split(": ") }
            .associate { (device, outputs) -> device to outputs.split(" ") }
    )
}