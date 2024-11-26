package lacar.junilu.aoc2015.day23

class TuringLock(
    private val instructions: List<Instruction>,
    private val registers: MutableMap<String, Int> = mutableMapOf("a" to 0, "b" to 0, "pc" to 0)
) {
    val a by registers
    val b by registers
    private var pc by registers

    fun initialize(a: Int): TuringLock = this.also { registers["a"] = a }

    fun execute() {
        while (pc in instructions.indices) { pc += nextInstructionOffset() }
    }

    private fun nextInstructionOffset(): Int {
        val (mnemonic, register, offset) = instructions[pc]
        val value = valueOf(register)

        fun setRegisterTo(newValue: Int) { registers[register] = newValue }
        fun jumpToOffset(condition: Boolean = true) = if (condition) offset else 1

        return when (mnemonic) {
            "hlf" -> 1.also { setRegisterTo(value / 2) }
            "tpl" -> 1.also { setRegisterTo(value * 3) }
            "inc" -> 1.also { setRegisterTo(value + 1) }
            "jmp" -> jumpToOffset()
            "jie" -> jumpToOffset(value % 2 == 0)
            "jio" -> jumpToOffset(value == 1)
            else -> throw IllegalArgumentException("Unknown mnemonic: $mnemonic")
        }
    }

    private fun valueOf(r: String) = registers[r] ?: throw IllegalArgumentException("Unknown register $r")

    companion object {
        fun load(source: List<String>) = TuringLock(source.map { parse(it) })
    }
}

data class Instruction(val mnemonic: String, val register: String = "", val offset: Int = 0)

private fun parse(line: String): Instruction {
    val mnemonic = line.substring(0, 3)
    val operands = line.substring(4)
    val r = operands.substring(0, 1)
    return when (mnemonic) {
        "hlf" -> Instruction(mnemonic, register = r)
        "tpl" -> Instruction(mnemonic, register = r)
        "inc" -> Instruction(mnemonic, register = r)
        "jmp" -> Instruction(mnemonic, offset = operands.toInt())
        "jie" -> Instruction(mnemonic, register = r, offset = operands.substring(3).toInt())
        "jio" -> Instruction(mnemonic, register = r, offset = operands.substring(3).toInt())
        else -> throw IllegalArgumentException("Unknown instruction: $line")
    }
}