package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock(
    private val instructions: List<Instruction>,
    private val registerSet: MutableMap<String, Int> = mutableMapOf("a" to 0, "b" to 0, "pc" to 0)
) {
    val a by registerSet
    val b by registerSet
    private var pc by registerSet

    fun initialize(a: Int): TuringLock = this.also { registerSet["a"] = a }

    fun execute() {
        while (pc in instructions.indices) {
            val (mnemonic, register, offset) = instructions[pc]
            pc += when (mnemonic) {
                "hlf" -> { registerSet[register] = valueOf(register) / 2; 1 }
                "tpl" -> { registerSet[register] = valueOf(register) * 3; 1 }
                "inc" -> { registerSet[register] = valueOf(register) + 1; 1 }
                "jmp" -> offset
                "jie" -> if (valueOf(register) % 2 == 0) offset else 1
                "jio" -> if (valueOf(register) == 1) offset else 1
                else -> throw IllegalArgumentException("Unknown mnemonic: $mnemonic")
            }
        }
    }

    private fun valueOf(r: String) = registerSet[r] ?: throw IllegalArgumentException("Unknown register $r")

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