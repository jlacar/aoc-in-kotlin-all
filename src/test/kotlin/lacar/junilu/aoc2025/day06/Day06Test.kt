package lacar.junilu.aoc2025.day06

import lacar.junilu.common.transpose
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun `Github input solutions - Parts 1 and 2`() {
        val input = readPuzzleInput("aoc2025/day06-gh")

        assertEquals(4693159084994, parseForPart1(input).solve())
        assertEquals(11643736116335, parseForPart2(input).solve())
    }

    @Test
    fun `Examples Part 1`() {
        val input = """
            123 328  51 64 
             45 64  387 23 
              6 98  215 314
            *   +   *   +""".trimIndent().lines()

        assertEquals(4277556, parseForPart1(input).solve())
        assertEquals(3263827, parseForPart2(input).solve())
    }

    private fun parseForPart1(input: List<String>): Day06 {
        val spaces = "\\s+".toRegex()

        fun parseNumbers(lines: List<String>) =
            lines.map { line ->
            line.trim().split(spaces)
                .map { it.toLong() }
            }.transpose()

        fun parseOperations(line: String) = line.trim().split(spaces)

        return Day06(
            numbers = parseNumbers(input.dropLast(1)),
            operations = parseOperations(input.last())
        )
    }

    private fun parseForPart2(input: List<String>): Day06 {

        fun parseNumbers(
            numbers: List<String>, ops: List<Pair<String, Int>>
        ): List<List<Long>> {
            return ops
                .map { (op, index) -> numbers.map { it.substring(index, index + op.length - 1) } }
                .map { ss ->
                    (0..ss.first().lastIndex)
                        .map { i -> ss.map { it[i] }.joinToString("").trim().toLong() }
                }
        }

        fun parseOperations(line: String): List<Pair<String, Int>> {
            val ops = "([*|+]\\s*)".toRegex()
                .findAll(line)
                .map { it.value }
                .toList()

            fun Pair<String, Int>.endIndex() = first.length + second

            return ops
                .runningFold(Pair("", 0)) { acc, op ->
                    Pair(op, acc.endIndex())
                }
                .drop(1)
        }

        val ops = parseOperations(input.last().padEnd(input.first().length + 1, ' '))

        return Day06(
            numbers = parseNumbers(input.dropLast(1), ops),
            operations = ops.map { it.first.trim() }
        )
    }
}