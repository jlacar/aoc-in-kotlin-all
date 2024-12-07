package lacar.junilu.aoc2024.day07

class Day07(val input: Map<Int, List<Int>>) {

    // What is their total calibration result?
    fun part1(): Int {
        TODO()
    }

    // What is ...
    fun part2(): Int {
        TODO()
    }

    companion object {
        fun using(lines: List<String>) = Day07(
            lines.associate { equation ->
                val (testValue, operands) = equation.split(": ")
                testValue.toInt() to operands.split(" ").map { it.toInt() }
            }
        )
    }
}