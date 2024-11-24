package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock(private val initialState: LockState = initialLockState()) {
    fun run(program: Program) = generateSequence(initialState) { state ->
        if (program.hasNext(state))
            program.executeNext(state)
        else null
    }.last()
}

/*
   DEVELOPER NOTE: The following extensions are used primarily to introduce domain semantics
   to the high level code. It also limits the scope of the knowledge of the specific map keys
   used for the two registers, "a" and "b", and the program counter, "pc".
 */

typealias Program = List<Instruction>
fun Program.hasNext(state: LockState) = size > state.programCounter
fun Program.executeNext(state: LockState): LockState = this[state.programCounter].execute(state)

// convenience extensions
typealias Register = Pair<String, Int>
typealias LockState = Map<String, Int>
fun LockState.copy(vararg newValues: Register) = this + newValues.toSet()
fun LockState.jump(offset: Int) = "pc" to (programCounter + offset)
fun LockState.valueOf(register: String) = this[register]!!
val LockState.next: Register get() = this.jump(1)
val LockState.a: Int get() = valueOf("a")
val LockState.b: Int get() = valueOf("b")
val LockState.programCounter: Int get() = valueOf("pc")

typealias Operation = (LockState, String, Int) -> LockState

fun initialLockState(a: Int = 0, b: Int = 0, pc: Int = 0) = mapOf("a" to a, "b" to b, "pc" to pc)

class Instruction(
    private val operation: Operation,
    private val register: String = "",
    private val offset: Int = 0
) {
    fun execute(state: LockState): LockState = operation(state, register, offset)

    companion object {
        private val operations: Map<String, Operation> = mapOf(
            "hlf" to { state, r, _ -> state.copy(r to state.valueOf(r) / 2, state.next) },
            "tpl" to { state, r, _ -> state.copy(r to state.valueOf(r) * 3, state.next) },
            "inc" to { state, r, _ -> state.copy(r to state.valueOf(r) + 1, state.next) },
            "jmp" to { state, _, offset -> state.copy(state.jump(offset)) },
            "jie" to { state, r, offset ->
                when {
                    state.valueOf(r) % 2 == 0 -> state.copy(state.jump(offset))
                    else -> state.copy(state.next)
                }
            },
            "jio" to { state, r, offset ->
                when {
                    state.valueOf(r) == 1 -> state.copy(state.jump(offset))
                    else -> state.copy(state.next)
                }
            }
        )

        private fun operationFor(opcode: String) = operations[opcode]!!

        fun parse(line: String): Instruction {
            val opcode = line.substring(0, 3)
            val args = line.substring(4)
            val r = args.substring(0, 1)
            return when (opcode) {
                "hlf" -> Instruction(operationFor(opcode), register = r)
                "tpl" -> Instruction(operationFor(opcode), register = r)
                "inc" -> Instruction(operationFor(opcode), register = r)
                "jmp" -> Instruction(operationFor(opcode), offset = args.toInt())
                "jie" -> Instruction(
                            operation = operationFor(opcode),
                            register = r,
                            offset = args.substring(3).toInt()
                         )
                "jio" -> Instruction(
                            operation = operationFor(opcode),
                            register = r,
                            offset = args.substring(3).toInt()
                         )
                else -> throw IllegalArgumentException("Unknown operation: $line")
            }
        }
    }
}
