package lacar.junilu

import kotlin.math.sqrt

class Day20(
    private val numberOfPresents: Int,
    private val visitLimit: Int = 0
) : Solution<Int> {
    override fun part1(): Int = firstToGetAsManyPresentsWith(10, visitLimit)

    override fun part2(): Int = firstToGetAsManyPresentsWith(11, visitLimit)

    private fun firstToGetAsManyPresentsWith(presentsPerElf: Int, visitLimit: Int) =
        generateSequence(1) { it.inc() }
        .takeWhile { house -> presentsDeliveredTo(house, presentsPerElf, visitLimit) < numberOfPresents }
        .last() + 1

    private fun presentsDeliveredTo(house: Int, presentsPerElf: Int, visitLimit: Int) =
        house.factors().fold(0) { acc, elf ->
            if (visitLimit == 0 || elf.willVisit(house, visitLimit)) acc + elf * presentsPerElf else acc
        }

    private fun Int.willVisit(house: Int, limit: Int) = house <= this * limit
}

private fun Int.factors(): Sequence<Int> = sequence {
    val thisNumber = this@factors
    yieldAll(listOf(1, thisNumber))
    
    val limit = thisNumber.floorDiv(sqrt(thisNumber.toDouble()).toInt())
    for (divisor in 2..limit) {
        if (thisNumber % divisor == 0) {
            yieldAll(listOf(divisor, thisNumber/divisor))
        }
    }
}.distinct()