package lacar.junilu

import kotlin.math.sqrt

class Day20(private val numberOfPresents: Int) : Solution<Int> {
    override fun part1(): Int = firstHouseToGetAtLeastAsManyPresents()

    override fun part2(): Int = firstOfFiftyHousesToGetAsManyPresents()

    private fun firstHouseToGetAtLeastAsManyPresents() = generateSequence(1) { it.inc() }
        .takeWhile { house -> presentsReceivedBy(house, 10) < numberOfPresents }
        .last() + 1

    private fun presentsReceivedBy(house: Int, presentsPerElf: Int) =
        house.factors().fold(presentsPerElf + house * presentsPerElf) { acc, elf -> acc + elf * presentsPerElf }

    private fun firstOfFiftyHousesToGetAsManyPresents(): Int {
        return 0
    }
}

private fun Int.factors(): Sequence<Int> = sequence {
    val limit = floorDiv(sqrt(toDouble()).toInt())
    for (divisor in 2..limit) {
        if (rem(divisor) == 0) {
            yieldAll(listOf(divisor, div(divisor)))
        }
    }
}.distinct()

fun main() {
    50.factors().forEach(::println)
}
