package lacar.junilu.aoc2024.day13

import lacar.junilu.toPropsMap

// Ubiquitous Language of this Puzzle
private typealias Button = Pair<Long, Long>
private typealias Prize = Pair<Long, Long>
private typealias ClawMachine = Triple<Button, Button, Prize>

class Day13(private val clawMachines: List<ClawMachine>, private val inLimits: (Long) -> Boolean) {

    fun part1(): Long {
        return clawMachines.sumOf { it.tokensToWin() }
    }

    fun part2(): Long {
        return part1()
    }

    private val ClawMachine.buttonA: Button get() = first
    private val ClawMachine.buttonB: Button get() = second
    private val ClawMachine.prize: Prize get() = third

    private fun ClawMachine.tokensToWin(): Long {
        val (aX, aY) = buttonA
        val (bX, bY) = buttonB
        val (pX, pY) = prize

        return when (val determinant = aX * bY - bX * aY) {
            0L -> 0L
            else -> {
                val a = (bY * pX - bX * pY)
                val b = (aX * pY - aY * pX)
                if (a % determinant == 0L && b % determinant == 0L) {
                    val pushes = listOf(a / determinant, b / determinant)
                    if (pushes.all { inLimits(it) })
                        3L * pushes[0] + pushes[1]
                    else
                        0L
                } else
                    0L
            }
        }
    }

//    private fun ClawMachine.tokensToWin(correction: Long = 0): Long {
//        val (aX, aY) = buttonA
//        val (bX, bY) = buttonB
//        val (pX, pY) = prize
//
//        val determinant = aX * bY - bX * aY
//
//        return 3L * (bY * pX - bX * pY) / determinant + (bY * pX - bX * pY) / determinant
//    }

    companion object {
        private fun toButton(buttonStr: String): Button {
            val props = toPropsMap(
                    input = buttonStr.drop(10).trim(),
                    itemDelimiter = ", ",
                    keyValueDelimiter = "+"
                ) { it.toLong() }

            return Pair(props["X"]!!, props["Y"]!!)
        }

        private fun toPrize(prizeStr: String, correction: Long): Prize {
            val props = toPropsMap(
                    input = prizeStr.drop(7).trim(),
                    itemDelimiter = ", ",
                    keyValueDelimiter = "="
                ) { it.toLong() }

            return Pair(props["X"]!! + correction, props["Y"]!! + correction)
        }

        fun using(lines: List<String>, correction: Long = 0L) = Day13(
            lines
            .chunked(4)
            .map { it.take(3) }
            .map { (buttonA, buttonB, prize) ->
                Triple(
                    toButton(buttonA),
                    toButton(buttonB),
                    toPrize(prize, correction)
                )
            },
            when (correction) {
                0L -> { n: Long -> n in 1L..100L }
                else -> { _ -> true }
            }
        )
    }
}

//a(17,86)
//b(84,37)
//p(10000000007870,10000000006450)