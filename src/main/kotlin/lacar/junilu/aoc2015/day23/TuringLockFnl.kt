package lacar.junilu.aoc2015.day23

class TuringLockFnl(private val instructions: List<InstructionFnl>, val a: Int = 0) {
    private val initialState = when {
        a == 0 -> defaultInitialState
        else -> defaultInitialState + ("a" to a)
    }

    fun finalState() = generateSequence(initialState) { state ->
            if (state["pc"]!! >= instructions.size) null
            else instructions[state["pc"]!!].execute(state)
        }.last()

    companion object {
        private val defaultInitialState = mapOf("a" to 0, "b" to 0, "pc" to 0)

        fun using(source: List<String>, a: Int = 0) = TuringLockFnl(parse(source), a = a)

        private fun parse(source: List<String>) = source.map { InstructionFnl.parse(it) }
    }
}

val Map<String, Int>.b: Int get() = this["b"] ?: throw NoSuchElementException("No element for key 'b' found")

class InstructionFnl(private val mnemonic: String, val register: String = "", val offset: Int = 0) {
    fun execute(state: Map<String, Int>): Map<String, Int> {
        val value = state.getOrDefault(register, 0)
        val nextInstruction = "pc" to state.getValue("pc") + 1
        val jumpToOffset = "pc" to state.getValue("pc") + offset
        return when (mnemonic) {
            "hlf" -> state + setOf(register to value / 2, nextInstruction)
            "tpl" -> state + setOf(register to value * 3, nextInstruction)
            "inc" -> state + setOf(register to value + 1, nextInstruction)
            "jmp" -> state + jumpToOffset
            "jie" -> when (value % 2) {
                0 -> state + jumpToOffset
                else -> state + nextInstruction
            }
            "jio" -> when (value) {
                1 -> state + jumpToOffset
                else -> state + nextInstruction
            }
            else -> state
        }
    }

    companion object {
        fun parse(line: String): InstructionFnl {
            val mnemonic = line.substring(0, 3)
            val operands = line.substring(4)
            val r = operands.substring(0, 1)
            return when (mnemonic) {
                "hlf" -> InstructionFnl(mnemonic, register = r)
                "inc" -> InstructionFnl(mnemonic, register = r)
                "tpl" -> InstructionFnl(mnemonic, register = r)
                "jmp" -> InstructionFnl(mnemonic, offset = operands.toInt())
                "jie" -> InstructionFnl(mnemonic, register = r, offset = operands.substring(3).toInt())
                "jio" -> InstructionFnl(mnemonic, register = r, offset = operands.substring(3).toInt())
                else -> throw IllegalArgumentException("Unknown instruction: $line")
            }
        }
    }
}