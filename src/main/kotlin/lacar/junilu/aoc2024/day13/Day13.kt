package lacar.junilu.aoc2024.day13

import lacar.junilu.toPropsMap

class Day13(private val clawMachines: List<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>>) {

    fun part1() = clawMachines.size.also {
        println("Total Claw Machines: $it")
        println(clawMachines.joinToString("\n"))
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