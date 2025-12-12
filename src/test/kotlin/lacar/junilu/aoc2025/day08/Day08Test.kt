package lacar.junilu.aoc2025.day08

import lacar.junilu.common.Point3D
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun `GitHub input solutions - Parts 1 & 2`() {
        val puzzle = using(readPuzzleInput("aoc2025/day08-gh"))

        assertEquals(103488, puzzle.part1(1000))
        assertEquals(8759985540L, puzzle.part2())
    }

    @Test
    fun `Examples - Parts 1 & 2`() {
        val example = using(
            """
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
            542,29,236
            431,825,988
            739,650,466
            52,470,668
            216,146,977
            819,987,18
            117,168,530
            805,96,715
            346,949,466
            970,615,88
            941,993,340
            862,61,35
            984,92,344
            425,690,689""".trimIndent().lines())

        assertEquals(40, example.part1(10))
        assertEquals(25272L, example.part2())
    }
}

private fun using(lines: List<String>) = Day08(
    lines.map {
        val (x, y, z) = it.split(",").map { it.toInt() }
        Point3D(x = x, y = y, z = z)
    }
)
