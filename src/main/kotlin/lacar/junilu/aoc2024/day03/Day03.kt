package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {

    fun part1(input: List<String>): Int = """mul\((\d+,\d+)\)""".toRegex()
        .findAll(input.joinToString(""))
        .map { it.groupValues[1].toPair() }
        .sumOf { (n1, n2) -> n1 * n2 }

    /**
     * Convert a string of the form "digits,digits" to a Pair<Int, Int>.
     */
    private fun String.toPair(): Pair<Int, Int> {
        val (n1, n2) = split(",").map(String::toInt)
        return Pair(n1, n2)
    }

    fun part2(input: List<String>): Int {
        val operands = mutableListOf<Pair<Int, Int>>()
        val endEnabled = """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
                .findAll(input.joinToString(""))
                .fold(true) { enabled, match -> addTo(operands, match, enabled) }
        return Pair(operands.sumOf { (n1, n2) -> n1 * n2 }, endEnabled).first
    }

    private fun addTo(
        operands: MutableList<Pair<Int, Int>>,
        match: MatchResult,
        enabled: Boolean
    ) = when (match.groupValues[0]) {
        "do()" -> true
        "don't()" -> false
        else -> {
            if (enabled) operands.add(match.groupValues[1].toPair())
            enabled
        }
    }
}