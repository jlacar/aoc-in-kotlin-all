package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock(private val initialState: LockState = mapOf("a" to 0, "b" to 0, "pc" to 0)) {
    fun run(program: List<Instruction>): LockState {
        return generateSequence(initialState) { state ->
            if (state.isProgramCounterLessThan(program.size))
                program[state.currentInstruction].execute(state)
            else null
        }.last()
    }
}

class Instruction(
    private val command: String,
    private val register: String = "",
    private val offset: Int = 0
) {
    fun execute(state: LockState): LockState {
        return when (command) {
            "hlf" -> state.halve(register)
            "tpl" -> state.triple(register)
            "inc" -> state.inc(register)
            "jmp" -> state.jump(offset)
            "jie" -> state.jumpIfEven(register, offset)
            "jio" -> state.jumpIfOne(register, offset)
            else -> throw IllegalArgumentException("Unknown command: $command")
        }
    }
}

typealias LockState = Map<String, Int>
val LockState.currentInstruction get() = this["pc"]!!
fun LockState.isProgramCounterLessThan(offset: Int) = this["pc"]!! < offset
fun LockState.next() = "pc" to (this.getValue("pc") + 1)
fun LockState.getValue(register: String) = this.getOrDefault(register, 0)
fun LockState.halve(register: String) = this + (register to this.getValue(register) / 2) + this.next()
fun LockState.triple(register: String) = this + (register to this.getValue(register) * 3) + this.next()
fun LockState.inc(register: String) = this + (register to this.getValue(register) + 1) + this.next()
fun LockState.jump(offset: Int) = this + ("pc" to this.getValue("pc") + offset)
fun LockState.jumpIfEven(register: String, offset: Int) = if (this.getValue(register) % 2 == 0) this.jump(offset)
else this + this.next()
fun LockState.jumpIfOne(register: String, offset: Int) = if (this.getValue(register) == 1) this.jump(offset)
else this + this.next()
fun LockState.getRegister(register: String) = this.getOrDefault(register, 0)