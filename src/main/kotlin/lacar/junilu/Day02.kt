package lacar.junilu

/**
 * Aoc 2015 - Day 2: I Was Told There Would Be No Math
 *
 * https://adventofcode.com/2015/day/2
 */
class Day02(private val boxDimensions: List<IntArray>) : Solution<Int> {

    override fun part1() = boxDimensions.sumOf { wrapperNeeded(it) }

    override fun part2() = boxDimensions.sumOf { ribbonNeeded(it) }

    private fun wrapperNeeded(dims: IntArray): Int {
        val (w, l, h) = dims
        val areas = listOf(w * l, w * h, l * h)
        return areas.sumOf { 2 * it } + areas.min()
    }

    private fun ribbonNeeded(dims: IntArray): Int =
        dims.sumOf { 2 * it } - 2 * dims.max() +
        dims.fold(1) { acc, i -> acc * i }

    companion object {
        fun using(input: List<String>) = Day02(
            input.map { line ->
                line.split("x").map { it.toInt() }.toIntArray()
            }
        )
    }
}