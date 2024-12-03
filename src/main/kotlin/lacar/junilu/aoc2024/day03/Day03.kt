package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {

    fun part1(input: String): Int = mulRegex.findAll(input).sumOf { mulResult(it) }

    private val mulRegex = """mul\((?<op1>\d+),(?<op2>\d+)\)""".toRegex()

    private fun mulResult(match: MatchResult) =
        listOf("op1", "op2")
            .map { name -> match.groups[name]!!.value.toInt() }
            .reduce { a, b -> a * b }

    private val String.mulValue: Int get() = split(",")
        .map(String::toInt)
        .reduce { acc, n -> acc * n }

    fun part2(input: List<String>): Int {
        val mulValues = mutableListOf(0)
        """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
            .findAll(input.joinToString(""))
            .fold(true) { enabled, match ->
                addTo(mulValues, match, enabled)
            }
        return mulValues.sum()
    }

    private fun addTo(
        mulValues: MutableList<Int>,
        match: MatchResult,
        enabled: Boolean
    ) = when (match.groupValues[0]) {
        "do()" -> true
        "don't()" -> false
        else -> {
            if (enabled) { mulValues.add(match.groupValues[1].mulValue) }
            enabled
        }
    }
}