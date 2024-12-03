package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {

    fun part1(input: List<String>): Int = input.sumOf { line -> part1Sum(line) }

    private fun part1Sum(line: String): Int =
        """mul\((\d+,\d+)\)""".toRegex()
            .findAll(line)
            .map { it.groupValues[1].toPair() }
            .sumOf { (n1, n2) -> n1 * n2 }

    /**
     * Convert a string of the form "digits,digits" to a Pair<Int, Int>.
     */
    private fun String.toPair(): Pair<Int, Int> {
        val (n1, n2) = split(",").map(String::toInt)
        return Pair(n1, n2)
    }

    fun part2(input: List<String>): Int =
        input.fold(Pair(0, true)) { acc, line ->
            val (runningSum, enabled) = acc
            val (currentSum, nextEnabled) = part2Sum(line, enabled)
            Pair(runningSum + currentSum, nextEnabled)
        }.first

    private fun part2Sum(input: String, startEnabled: Boolean = true): Pair<Int, Boolean> {
        val operands = mutableListOf<Pair<Int, Int>>()
        val endEnabled = """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
            .findAll(input)
            .fold(startEnabled) { enabled, match -> addTo(operands, match, enabled) }
        return Pair(operands.sumOf { (n1, n2) -> n1 * n2 }, endEnabled)
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