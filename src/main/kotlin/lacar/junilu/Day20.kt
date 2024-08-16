package lacar.junilu

import kotlin.math.sqrt

class Day20(private val numberOfPresents: Int) : Solution<Int> {
    override fun part1(): Int = firstToGetAsManyPresentsWith(10, 0)

    override fun part2(): Int = firstToGetAsManyPresentsWith(11, 50)

    fun testPart2(visitLimit: Int) = firstToGetAsManyPresentsWith(11, visitLimit)

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
    yieldAll(listOf(1, this@factors))
    val limit = floorDiv(sqrt(toDouble()).toInt())
    for (divisor in 2..limit) {
        if (rem(divisor) == 0) {
            yieldAll(listOf(divisor, div(divisor)))
        }
    }
}.distinct()