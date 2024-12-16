package lacar.junilu.aoc2024.day13

import lacar.junilu.toPropsMap

// Ubiquitous Language of this Puzzle
private typealias Button = Pair<Long, Long>
private typealias Prize = Pair<Long, Long>
private typealias ClawMachine = Triple<Button, Button, Prize>

class Day13(private val clawMachines: List<ClawMachine>, limitPushes: Boolean = true) {

    private val inLimits: (Long) -> Boolean =
        when (limitPushes) {
            true -> { n: Long -> n in 0L..100L }
            false -> { _ -> true }
        }

    fun tokensToWinAllPrizes(): Long = clawMachines.sumOf { it.tokensToWin() }

    private val noSolution = Pair(0L, 0L)

    private fun ClawMachine.tokensToWin(): Long =
        when (val pushes = buttonPushesToWin()) {
            noSolution -> 0L
            else -> {
                val (pushesA, pushesB) = pushes
                3 * pushesA + pushesB
            }
        }

    // aliases for Triple properties
    private val ClawMachine.buttonA: Button get() = first
    private val ClawMachine.buttonB: Button get() = second
    private val ClawMachine.prize: Prize get() = third

    private fun ClawMachine.buttonPushesToWin(): Pair<Long, Long> {
        val (aX, aY) = buttonA
        val (bX, bY) = buttonB
        val (pX, pY) = prize

        return when (val determinant = aX * bY - bX * aY) {
            0L -> noSolution // no unique solution
            else -> {
                val a = (bY * pX - bX * pY)
                val b = (aX * pY - aY * pX)
                if (a % determinant != 0L || b % determinant != 0L) {
                    noSolution // not whole # of pushes
                } else {
                    val pushesA = a / determinant
                    val pushesB = b / determinant
                    if (inLimits(pushesA) && inLimits(pushesB))
                        Pair(pushesA, pushesB)
                    else
                        noSolution // not within limit of pushes
                }
            }
        }
    }

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
                correction == 0L
        )
    }
}