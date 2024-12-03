package lacar.junilu.aoc2024.day03

/**
 * AoC 2024 - Day 3: Mull It Over
 */
object Day03 {
    fun part1(input: String): Int {
        val regex = """mul\((\d+,\d+)\)""".toRegex()
        val matches = regex.findAll(input)

        val ops = matches.map { it.groupValues[1] }
        val pairs = ops.map {
            val (n1, n2) = it.split(",").map(String::toInt)
            Pair(n1, n2)
        }

        return pairs.sumOf { (n1, n2) -> n1 * n2 }
    }

    fun part2(input: String): Int {
        val regex = """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
        val matches = regex.findAll(input)

        var emit = true
        val pairs = mutableListOf<Pair<Int, Int>>()
        matches.forEach { match ->
            when (match.groupValues[0]) {
                "do()" -> emit = true
                "don't()" -> emit = false
                else -> if (emit) {
                    val (n1, n2) = match.groupValues[0].drop(4).dropLast(1).split(",").map(String::toInt)
                    pairs.add(Pair(n1, n2))
                }
            }
        }

        return pairs.toList().sumOf { (n1, n2) -> n1 * n2 }
    }
}