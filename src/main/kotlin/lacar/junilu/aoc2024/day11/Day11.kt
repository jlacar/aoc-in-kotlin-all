package lacar.junilu.aoc2024.day11

class Day11(val stones: List<Long>) {

    fun part1(blinks: Int): List<Long> =

//    buildList {
//        addAll("1 2024 1 0 9 9 2021976".split(" ").map { it.toInt() })
//    }.map { it.toLong() }

    companion object {
        fun using(line: String) = Day11(
            line.split(" ").map { it.toLong() }
        )
    }
}