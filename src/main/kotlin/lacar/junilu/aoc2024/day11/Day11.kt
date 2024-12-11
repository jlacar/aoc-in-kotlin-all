package lacar.junilu.aoc2024.day11

import lacar.junilu.println

class Day11(val stones: List<Long>) {

    fun part1(blinks: Int): List<Long> {
        var lineOfStones = buildList { addAll(stones) }

        repeat(blinks) { blink ->
//            "$blink: $lineOfStones".println()
            lineOfStones = lineOfStones.map {
                when {
                    it == 0L -> listOf(1L)
                    it.hasEvenDigits() -> it.splitInTwo()
                    else -> listOf(it * 2024)
                }
            }.flatten()
        }
//        "$blinks: $lineOfStones".println()
        return lineOfStones
    }

    private fun Long.hasEvenDigits() = this.toString().length % 2 == 0

    private fun Long.splitInTwo(): List<Long> {
        val asString = this.toString()
        val half = asString.length / 2
        return listOf(asString.take(half).toLong(), asString.takeLast(half).toLong())
    }

    companion object {
        fun using(line: String) = Day11(
            line.split(" ").map { it.toLong() }
        )
    }
}