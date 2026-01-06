package lacar.junilu.aoc2025.day11

import lacar.junilu.readPuzzleLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `Solution for GitHub input`() {
        val solution = using(readPuzzleLines("aoc2025/day11-gh"))

        assertEquals(
            expected = 500,
            actual = solution.pathCount(
                from = "you",
                to = "out"
            )
        )

        assertEquals(
            expected = 0,
            actual = solution.pathCount(
                from = "svr",
                to = "out",
                includes = listOf("dac", "fft")
            )
        )
    }

    @Test
    fun `Solution for Gmail input`() {
        val solution = using(readPuzzleLines("aoc2025/day11-gm"))

        assertEquals(
            expected = 643,
            actual = solution.pathCount(
                from = "you",
                to = "out"
            )
        )

        assertEquals(
            expected = 0,
            actual = solution.pathCount(
                from = "svr",
                to = "out",
                includes = listOf("dac", "fft")
            )
        )
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

        assertEquals(
            expected = 5,
            actual = example.pathCount(from = "you", to = "out")
        )
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

        assertEquals(
            expected = 2,
            actual = example.pathCount(
                from = "svr",
                to = "out",
                includes = listOf("dac", "fft")
            )
        )
    }

}

private fun using(input: List<String>) = Day11(
    input
        .map { it.split(": ") }
        .associate { (device, outputs) -> device to outputs.split(" ") }
)
