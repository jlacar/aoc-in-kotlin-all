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
        val zero = Pair(0, 0)
        var emit = true
        return """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
            .findAll(input)
            .map {
                when (it.groupValues[0]) {
                    "do()" -> zero.also { emit = true }
                    "don't()" -> zero.also { emit = false }
                    else -> if (emit) it.groupValues[1].toPair() else zero
                }
            }.sumOf { (n1, n2) -> n1 * n2 }
    }
}