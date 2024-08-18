package lacar.junilu

import kotlin.math.sqrt

/**
 * AoC 2015 - Day 20: Infinite Elves and Infinite Houses
 *
 * https://adventofcode.com/2015/day/20
 */
class Day20(
    private val numberOfPresents: Int,
    private val presentsPerElf: Int,
    private val visitLimit: Int = 0
) {
    fun firstToGetAsManyPresents() =
        generateSequence(1) { it.inc() }
        .takeWhile { house -> presentsDeliveredTo(house) < numberOfPresents }
        .last() + 1

    private fun presentsDeliveredTo(house: Int) =
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