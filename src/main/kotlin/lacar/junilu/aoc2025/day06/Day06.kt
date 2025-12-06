package lacar.junilu.aoc2025.day06

class Day06(val numbers: List<List<Long>>, val operations: List<String>) {
    fun solve(): Long = operations.mapIndexed { index, operation ->
        when (operation) {
            "+" -> numbers[index].sum()
            "*" -> numbers[index].reduce { product, n -> product * n }
            else -> throw IllegalArgumentException("Unknown operation $operation")
        }
    }.sum()
}