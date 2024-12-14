package lacar.junilu.aoc2024.day13

import lacar.junilu.print
import lacar.junilu.println
import lacar.junilu.toPropsMap

class Day13(private val clawMachines: List<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>>) {

    fun part1(): Int {
        return clawMachines.count { it.canWinPrize() }
    }

    private fun Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>.canWinPrize(): Boolean {
        val (xa, ya) = first
        val (xb, yb) = second
        val (xp, yp) = third

        val determinant = xa * yb - xb * ya
        return when (determinant) {
            0 -> false
            else -> (yb * xp - xb * yp).let {
                (it % determinant == 0) && (it / determinant in 1..100)
            }
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

