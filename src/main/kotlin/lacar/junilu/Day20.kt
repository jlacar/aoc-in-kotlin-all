package lacar.junilu

class Day20(private val leastNumberOfPresents: Int) : Solution<Int> {
    override fun part1(): Int {
        return generateSequence(1) { it.inc() }
            .takeWhile { presentsReceivedBy(it) < leastNumberOfPresents }
            .last() + 1
    }

    private fun presentsReceivedBy(house: Int) =
        factorsOf(house).fold(10 + house * 10) { acc, elf -> acc + elf * 10 }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun factorsOf(n: Int) : List<Int> =
    (2..n/2).filter { n % it == 0 }