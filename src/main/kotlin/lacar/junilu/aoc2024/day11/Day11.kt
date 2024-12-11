package lacar.junilu.aoc2024.day11

class Day11(private val stones: List<Long>) {

    fun part1(blinks: Int): Long = stones.map { n -> howMany(n, blinks) }.sum()

//        (1..blinks).fold(stones.asSequence()) { acc: Sequence<Long>, _ ->
//            acc.flatMap { numberOnStone -> next(numberOnStone) }
//        }.sumOf { it }

    private fun Long.hasEvenDigits() = this.toString().length % 2 == 0

    private fun Long.splitInTwo(): Pair<Long, Long> {
        val thisAsString = this.toString()
        val half = thisAsString.length / 2
        return Pair(thisAsString.take(half).toLong(), thisAsString.takeLast(half).toLong())
    }

    private val memo = mutableMapOf<Pair<Long, Int>, Long>()

    private fun howMany(stone: Long, blinks: Int): Long = memo.getOrPut(Pair(stone, blinks)) {
        when (blinks) {
            0 -> 1L
            else -> when {
                stone == 0L -> howMany(1L, blinks - 1)
                stone.hasEvenDigits() -> {
                    val (left, right) = stone.splitInTwo()
                    howMany(left, blinks - 1) + howMany(right, blinks - 1)
                }
                else -> howMany(stone * 2024L, blinks - 1)
            }
        }
    }

    companion object {
        fun using(line: String) = Day11(
            line.split(" ").map { it.toLong() }
        )
    }
}