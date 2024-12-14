package lacar.junilu.aoc2024.day13

import lacar.junilu.print
import lacar.junilu.println
import lacar.junilu.toPropsMap

class Day13(private val clawMachines: List<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>>) {

    fun part1(): Int {
        return clawMachines.count { it.canWinPrize() }
    }

    private fun Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>.canWinPrize(): Boolean {
        val (buttonA, buttonB, prize) = this
        val slopeA = buttonA.let { (runA, riseA) -> riseA.toDouble() / runA.toDouble() }
        val slopeB = buttonB.let { (runB, riseB) -> riseB.toDouble() / runB.toDouble() }
        val slopeP = prize.let{ (runP, riseP) -> riseP.toDouble() / runP.toDouble() }

        val sorted = listOf(slopeA, slopeB, slopeP).sorted()
        return (sorted[1] == slopeP)
                .also {
                    (if (it) "might win - " else "CAN'T win - ").print()
                    sorted.forEach { when (it) {
                        slopeA -> " A: $it".print()
                        slopeB -> " B: $it".print()
                        slopeP -> " P: $it".print()
                        else -> {}
                    }}
                    " - [A: $first, B: $second P: $third ]".println()
                }
    }

    private fun Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>.tokensToWin(): Int {
        return 1
    }

    companion object {
        private fun toDisplacement(buttonStr: String): Pair<Int, Int> {
            val props = toPropsMap(
                    input = buttonStr.drop(10).trim(),
                    itemDelimiter = ", ",
                    keyValueDelimiter = "+"
                ) { it.toInt() }

            return Pair(props["X"]!!, props["Y"]!!)
        }

        private fun toPoint(prizeStr: String): Pair<Int, Int> {
            val props = toPropsMap(
                    input = prizeStr.drop(7).trim(),
                    itemDelimiter = ", ",
                    keyValueDelimiter = "="
                ) { it.toInt() }

            return Pair(props["X"]!!, props["Y"]!!)
        }

        fun using(lines: List<String>) = Day13(
            lines
            .chunked(4)
            .map { it.take(3) }
            .map { (buttonA, buttonB, prize) ->
                Triple(
                    toDisplacement(buttonA),
                    toDisplacement(buttonB),
                    toPoint(prize)
                )
            }
        )
    }
}

