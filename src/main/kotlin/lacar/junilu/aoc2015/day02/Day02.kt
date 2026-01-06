package lacar.junilu.aoc2015.day02

/**
 * Aoc 2015 Day 2 - I Was Told There Would Be No Math
 *
 * Puzzle page: https://adventofcode.com/2015/day/2
 */
class Day02(private val boxes: List<List<Int>>) {

    fun sqFeetOfWrapper() = boxes.sumOf { it.wrapperNeeded() }

    fun feetOfRibbon() = boxes.sumOf { it.ribbonNeeded() }

    private fun List<Int>.wrapperNeeded(): Int {
        val (l, w, h) = this
        val areas = listOf(l * w, w * h, h * l)
        return areas.sumOf { 2 * it } + areas.min()
    }

    private fun List<Int>.ribbonNeeded(): Int = smallestPerimeter() + volume()

    private fun List<Int>.volume() = reduce { acc, i -> acc * i }

    private fun List<Int>.smallestPerimeter() = sumOf { 2 * it } - 2 * max()

    companion object {
        fun using(input: List<String>) = Day02(
            input.map { line ->
                line.split("x").map(String::toInt)
            }
        )
    }
}