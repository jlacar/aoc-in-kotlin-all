package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {

    fun part1(input: List<String>): Int = """mul\((\d+,\d+)\)""".toRegex()
        .findAll(input.joinToString(""))
        .sumOf { it.groupValues[1].mulValue }

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