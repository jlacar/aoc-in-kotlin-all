package lacar.junilu.aoc2024.day13

import lacar.junilu.toPropsMap

// region ===== Ubiquitous Language for this Puzzle

private typealias Button = Pair<Long, Long>
private typealias Prize = Pair<Long, Long>
private typealias ClawMachine = Triple<Button, Button, Prize>
private val ClawMachine.buttonA: Button get() = first
private val ClawMachine.buttonB: Button get() = second
private val ClawMachine.prize: Prize get() = third

// region ===== Puzzle solution

class Day13(private val clawMachines: List<ClawMachine>, limitPushes: Boolean = true) {

    private val inLimits: (Long) -> Boolean =
        when (limitPushes) {
            true -> { n: Long -> n in 0L..100L }
            false -> { _ -> true }
        }

    // For Parts 1 & 2, with and without a limit to # of button pushes, respectively.
    fun tokensToWinAllPrizes(): Long = clawMachines.sumOf { it.tokensToWin() }

    private fun ClawMachine.tokensToWin(): Long =
        when (val pushes = buttonPushesToWin()) {
            NO_SOLUTION -> 0L
            else -> {
                val (pushesA, pushesB) = pushes
                TOKENS_A * pushesA + TOKENS_B * pushesB
            }
        }

    private fun ClawMachine.buttonPushesToWin(): Pair<Long, Long> {
        val (aX, aY) = buttonA
        val (bX, bY) = buttonB
        val (pX, pY) = prize

        return when (val determinant = aX * bY - bX * aY) {
            0L -> NO_SOLUTION // no unique solution
            else -> {
                val a = (bY * pX - bX * pY)
                val b = (aX * pY - aY * pX)
                if (a % determinant != 0L || b % determinant != 0L) {
                    NO_SOLUTION // not whole # of pushes
                } else {
                    val pushesA = a / determinant
                    val pushesB = b / determinant
                    if (inLimits(pushesA) && inLimits(pushesB))
                        Pair(pushesA, pushesB)
                    else
                        NO_SOLUTION // not within limit of pushes
                }
            }
        }
    }

    companion object {
        private val NO_SOLUTION = Pair(0L, 0L)
        private const val TOKENS_A = 3
        private const val TOKENS_B = 1

        fun using(lines: List<String>, correction: Long = 0L) = Day13(
            clawMachinesFrom(lines, correction),
            correction == 0L
        )

        private fun clawMachinesFrom(lines: List<String>, correction: Long) = lines
            .chunked(4)
            .map { it.take(3) }
            .map { (buttonA, buttonB, prize) ->
                ClawMachine(
                    toButton(buttonA),
                    toButton(buttonB),
                    toPrize(prize, correction)
                )
            }

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
    }
}