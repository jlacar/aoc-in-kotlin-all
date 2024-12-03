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
        return 0
    }
}