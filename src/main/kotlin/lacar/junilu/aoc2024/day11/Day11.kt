package lacar.junilu.aoc2024.day11

class Day11(val stones: List<Long>) {

    fun part1(blinks: Int): List<Long> =
        (1..blinks).fold(stones) { acc: List<Long>, _ ->
            acc.map { numberOnStone ->
                when {
                    numberOnStone == 0L -> listOf(1L)
                    numberOnStone.hasEvenDigits() -> numberOnStone.splitInTwo()
                    else -> listOf(numberOnStone * 2024)
                }
            }.flatten()
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