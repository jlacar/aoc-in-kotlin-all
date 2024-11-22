package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */

class Instruction(val command: String, val register: String = "", val offset: Int = 0) {
    fun execute(state: Map<String, Int>): Map<String, Int> {
        val value = state.getOrDefault(register, 0)
        return when (command) {
            "hlf" -> state + (register to value / 2)
            "tpl" -> state + (register to value * 3)
            "inc" -> state + (register to value + 1)
            "jmp" -> state + ("pc" to state.getValue("pc") + offset)
            "jie" -> if (value % 2 == 0) state + ("pc" to state.getValue("pc") + offset) else state + ("pc" to state.getValue("pc") + 1)
            "jio" -> if (value == 1) state + ("pc" to state.getValue("pc") + offset) else state + ("pc" to state.getValue("pc") + 1)
            else -> state
        }
    }
}

fun main() {
    val instructions = listOf(
        Instruction("hlf", "a"),
        Instruction("tpl", "a"),
        Instruction("inc", "a"),
        Instruction("jmp", offset = 2),
        Instruction("jie", "a", 2),
        Instruction("jio", "a", 3)
    )

    val initialState = mapOf("a" to 0, "b" to 0, "pc" to 0)

    val finalState = generateSequence(initialState) { state ->
        if (state["pc"]!! >= instructions.size) null
        else instructions[state["pc"]!!].execute(state)
    }.last()

    println("Final state: $finalState")
}