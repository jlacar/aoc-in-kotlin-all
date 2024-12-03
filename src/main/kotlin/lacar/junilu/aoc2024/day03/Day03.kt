package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {
    fun part1(input: String): Int {
        val regex = """mul\((\d+,\d+)\)""".toRegex()
        val matches = regex.findAll(input)

        val ops = matches.map { it.groupValues[1] }
        val pairs = ops.map { it.toPair() }

        return pairs.sumOf { (n1, n2) -> n1 * n2 }
    }

    private fun String.toPair(): Pair<Int, Int> {
        val (n1, n2) = split(",").map(String::toInt)
        return Pair(n1, n2)
    }

    fun part2(input: String): Int {
        val regex = """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
        val matches = regex.findAll(input)

        var emit = true
        return matches.map { match ->
            val zero = Pair(0, 0)
            when (match.groupValues[0]) {
                "do()" -> zero.also { emit = true }
                "don't()" -> zero.also { emit = false }
                else -> if (emit) {
                    match.groupValues[1].toPair()
                } else {
                    zero
                }
            }
        }.sumOf { (n1, n2) -> n1 * n2 }
    }
}