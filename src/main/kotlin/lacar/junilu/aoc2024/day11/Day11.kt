package lacar.junilu.aoc2024.day11

class Day11(private val stones: List<Long>) {
    fun part1(blinks: Int): Long = stones.sumOf { number -> Memoized.stoneCount(number, blinks) }

    companion object {
        fun using(line: String) = Day11(
            line.split(" ").map { it.toLong() }
        )
    }
}

private object Memoized {
    private val memo = mutableMapOf<Pair<Long, Int>, Long>()
    fun stoneCount(number: Long, blinks: Int): Long = memo.getOrPut(Pair(number, blinks)) {
        when (blinks) {
            0 -> 1L
            else -> when {
                number == 0L -> stoneCount(1L, blinks - 1)
                number.hasEvenDigits() -> {
                    val (left, right) = number.splitInTwo()
                    stoneCount(left, blinks - 1) + stoneCount(right, blinks - 1)
                }
                else -> stoneCount(number * 2024L, blinks - 1)
            }
        }
    }

    private fun Long.hasEvenDigits() = this.toString().length % 2 == 0

    private fun Long.splitInTwo(): Pair<Long, Long> {
        val thisAsString = this.toString()
        val half = thisAsString.length / 2
        return Pair(thisAsString.take(half).toLong(), thisAsString.takeLast(half).toLong())
    }
}
