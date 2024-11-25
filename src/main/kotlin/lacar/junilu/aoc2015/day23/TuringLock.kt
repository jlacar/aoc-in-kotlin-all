package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock(
    private val program: List<Instruction>,
    private val registerSet: MutableMap<String, Int> = mutableMapOf(
        "a" to 0,
        "b" to 0,
        "pc" to 0
    )
) {
    val a by registerSet
    val b by registerSet
    private var pc by registerSet

    fun run() { while (hasNext()) executeNext() }

    fun initialize(a: Int): TuringLock = this.also { registerSet["a"] = a }

    private fun hasNext() = pc < program.size
    private fun valueOf(r: String) = registerSet[r]!!
    private fun jump(offset: Int): Int = (pc + offset).also { pc = it }
    private fun next() = jump(1)

    val hlf = { r: String -> registerSet.put(r, valueOf(r) / 2); next() }
    val tpl = { r: String -> registerSet.put(r, valueOf(r) * 3); next() }
    val inc = { r: String -> registerSet.put(r, valueOf(r) + 1); next() }
    val jmp = { offset: Int -> jump(offset) }
    val jie = { r: String, offset: Int -> if (valueOf(r) % 2 == 0) jump(offset) else next() }
    val jio = { r: String, offset: Int -> if (valueOf(r) == 1) jump(offset) else next() }

    private fun executeNext(): Int {
        val instruction = program[pc]
        return when (instruction.mnemonic) {
            "hlf" -> { hlf(instruction.register) }
            "tpl" -> { tpl(instruction.register) }
            "inc" -> { inc(instruction.register) }
            "jmp" -> { jmp(instruction.offset) }
            "jie" -> { jie(instruction.register, instruction.offset) }
            "jio" -> { jio(instruction.register, instruction.offset) }
            else -> throw IllegalArgumentException("Unknown mnemonic: ${instruction.mnemonic}")
        }
    }

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