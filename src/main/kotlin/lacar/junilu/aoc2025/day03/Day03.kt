package lacar.junilu.aoc2025.day03

class Day03(val banks: List<String>) {
    private fun joltage(bank: String, digits: Int) =
        (1..digits).fold(Pair(bank, "")) { acc, i ->
            val (remaining, result) = acc
            val max = remaining.dropLast(digits - i).maxOf { it }
            remaining.drop(remaining.indexOf(max) + 1) to result + max
        }.second.toLong()

    fun part1(): Long = banks.sumOf { joltage(it, 2) }
    fun part2(): Long = banks.sumOf { joltage(it, 12) }
}