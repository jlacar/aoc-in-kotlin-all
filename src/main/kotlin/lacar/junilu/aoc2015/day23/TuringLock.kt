package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock(private val initialState: LockState = initialLockState()) {
    fun run(program: Program) = generateSequence(initialState) { state ->
        if (program.hasNextCommand(state))
            program.executeNext(state)
        else null
    }.last()
}

fun initialLockState(a: Int = 0, b: Int = 0, pc: Int = 0) = mapOf("a" to a, "b" to b, "pc" to pc)

typealias Program = List<Command>
fun Program.hasNextCommand(state: LockState) = size > state.programCounter
fun Program.executeNext(state: LockState): LockState = this[state.programCounter].execute(state)

typealias Register = Pair<String, Int>
typealias LockState = Map<String, Int>
fun LockState.copy(vararg newValues: Register) = this + newValues.toSet()
fun LockState.valueOf(register: String) = this[register]!!
fun LockState.jump(offset: Int) = "pc" to (programCounter + offset)
val LockState.nextInstruction: Register get() = this.jump(1)
val LockState.a: Int get() = valueOf("a")
val LockState.b: Int get() = valueOf("b")
val LockState.programCounter: Int get() = valueOf("pc")

typealias Operation = (LockState, String, Int) -> LockState

class Command(
    private val operation: Operation,
    private val register: String = "",
    private val offset: Int = 0
) {
    fun execute(state: LockState): LockState = operation.invoke(state, register, offset)

    companion object {
        fun parse(line: String): Command {
            val opcode = line.substring(0, 3)
            return when (opcode) {
                "hlf" -> Command(operationFor(opcode), register = line.substring(4))
                "tpl" -> Command(operationFor(opcode), register = line.substring(4))
                "inc" -> Command(operationFor(opcode), register = line.substring(4))
                "jmp" -> Command(operationFor(opcode), offset = line.substring(4).toInt())
                "jie" -> Command(
                    operationFor(opcode),
                    register = line.substring(4, 5),
                    offset = line.substring(7).toInt()
                )
                "jio" -> Command(
                    operationFor(opcode),
                    register = line.substring(4, 5),
                    offset = line.substring(7).toInt()
                )
                else -> throw IllegalArgumentException("Unknown instruction: $line")
            }
        }
    }
}

private fun operationFor(opcode: String): Operation =
    when (opcode) {
        "hlf" -> { state, r, _ -> state.copy(r to state.valueOf(r) / 2, state.nextInstruction) }
        "tpl" -> { state, r, _ -> state.copy(r to state.valueOf(r) * 3, state.nextInstruction) }
        "inc" -> { state, r, _ -> state.copy(r to state.valueOf(r) + 1, state.nextInstruction) }
        "jmp" -> { state, _, offset -> state.copy(state.jump(offset)) }
        "jie" -> { state, r, offset ->
            if (state.valueOf(r) % 2 == 0)
                state.copy(state.jump(offset))
            else
                state.copy(state.nextInstruction)
        }
        "jio" -> { state, r, offset ->
            if (state.valueOf(r) == 1)
                state.copy(state.jump(offset))
            else
                state.copy(state.nextInstruction)
        }
        else -> throw IllegalArgumentException("Unknown operation: $opcode")
    }