package lacar.junilu.aoc2025.day06

class Day06(val numbers: List<List<Long>>, val operations: List<String>) {
    fun solve(): Long = operations.mapIndexed { index, operation ->
        when (operation) {
            "+" -> numbers[index].sum()
            "*" -> numbers[index].reduce { acc, i -> acc * i }
            else -> throw IllegalArgumentException("Unknown operation $operation")
        }
    }.sum()
}