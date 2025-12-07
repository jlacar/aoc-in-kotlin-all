package lacar.junilu.aoc2025.day07

import java.util.BitSet

private fun BitSet.split(index: Int) {
    set(index - 1)
    set(index + 1)
    clear(index)
}

class Day07(val lines: List<String>) {

    private val width = lines.first().length

    private fun newBitSet(): BitSet = BitSet(width)

    fun timesBeamSplit(): Int {
        fun String.findAllSplitters(): BitSet = newBitSet()
            .also { splitters ->
                indices.forEach { i -> if (this[i] == '^') splitters.set(i) }
            }

        val initialBeam = newBitSet().also { it.set(lines.first().indexOf('S')) }

        return lines.drop(2).chunked(2).fold(Pair(initialBeam, 0)) { acc, nextPair ->
            val (beamsIn, totalSplits) = acc
            val splitters = nextPair.first().findAllSplitters()
            val beamsOut = newBitSet().apply { or(beamsIn) }
            val splits = (0 until width).fold(totalSplits) { acc, i ->
                if (beamsIn.get(i) && splitters.get(i)) {
                    beamsOut.split(i)
                    acc + 1
                } else {
                    acc
                }
            }
            Pair (beamsOut, splits)
        }.second
    }
}