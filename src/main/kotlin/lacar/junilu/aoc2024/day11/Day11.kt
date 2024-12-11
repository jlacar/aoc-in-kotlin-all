package lacar.junilu.aoc2024.day11

class Day11(private val stones: List<Long>) {

    fun part1(blinks: Int): Long {
        var count = 0L
        generateSequence(stones.asSequence()) { acc ->
            val next = acc.map { numberOnStone ->
                when {
                    numberOnStone == 0L -> listOf(1L).asSequence()
                    numberOnStone.hasEvenDigits() -> numberOnStone.splitInTwo().asSequence()
                    else -> listOf(numberOnStone * 2024).asSequence()
                }
            }.flatten()
            next
        }.drop(blinks).first().forEach { count++ }
        return count
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