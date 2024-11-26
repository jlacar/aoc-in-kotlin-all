package lacar.junilu.aoc2015.day23

private const val PROGRAM_COUNTER = "pc"

class TuringLockFnl(private val instructions: List<InstructionFnl>, val a: Int = 0) {
    private val initialState = when {
        a == 0 -> defaultInitialState
        else -> defaultInitialState + ("a" to a)
    }

    fun finalState() = generateSequence(initialState) { state ->
            if (state[PROGRAM_COUNTER]!! >= instructions.size) null
            else instructions[state[PROGRAM_COUNTER]!!].execute(state)
        }.last()

    companion object {
        private val defaultInitialState = mapOf("a" to 0, "b" to 0, PROGRAM_COUNTER to 0)

        fun using(source: List<String>, a: Int = 0) = TuringLockFnl(parse(source), a = a)

        private fun parse(source: List<String>) = source.map { InstructionFnl.parse(it) }
    }
}

val Map<String, Int>.b: Int get() = this["b"] ?: throw NoSuchElementException("No element for key 'b' found")

class InstructionFnl(private val mnemonic: String, val register: String = "", val offset: Int = 0) {
    fun execute(state: Map<String, Int>): Map<String, Int> {
        val pc = state.getValue(PROGRAM_COUNTER)
        val value = state.getOrDefault(register, 0)
        val nextInstruction = PROGRAM_COUNTER to (pc + 1)

        fun nextState(vararg registers: Pair<String, Int>) = state + setOf(*registers)
        fun setRegisterTo(newValue: Int) = register to newValue
        fun jumpToOffset(condition: Boolean = true) = PROGRAM_COUNTER to (pc + if (condition) offset else 1)

        return when (mnemonic) {
            "hlf" -> nextState(setRegisterTo(value / 2), nextInstruction)
            "tpl" -> nextState(setRegisterTo(value * 3), nextInstruction)
            "inc" -> nextState(setRegisterTo(value + 1), nextInstruction)
            "jmp" -> nextState(jumpToOffset())
            "jie" -> nextState(jumpToOffset(value % 2 == 0 ))
            "jio" -> nextState(jumpToOffset(value == 1 ))
            else -> throw IllegalArgumentException("Unknown mnemonic: $mnemonic")
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