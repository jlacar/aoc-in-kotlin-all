package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {
    fun part1(input: String): Int =
        """mul\((\d+,\d+)\)""".toRegex()
        .findAll(input)
        .map { it.groupValues[1] }      // (ds,ds)
        .map { it.toPair() }            // Pair<Int, Int>
        .sumOf { (n1, n2) -> n1 * n2 }

    private fun String.toPair(): Pair<Int, Int> {
        val (n1, n2) = split(",").map(String::toInt)
        return Pair(n1, n2)
    }

    fun part2(input: String): Int {
        val operands = mutableListOf<Pair<Int, Int>>()
        """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
            .findAll(input)
            .fold(true) { emit, match -> addTo(operands, match, emit) }
        return operands.sumOf { (n1, n2) -> n1 * n2 }
    }

    private fun addTo(
        operands: MutableList<Pair<Int, Int>>,
        match: MatchResult,
        emit: Boolean
    ) = when (match.groupValues[0]) {
            "do()" -> true
            "don't()" -> false
            else -> {
                if (emit) operands.add(match.groupValues[1].toPair())
                emit
            }
    }
}