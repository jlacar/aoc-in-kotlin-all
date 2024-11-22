package lacar.junilu.aoc2015.day23

/**
 * AoC 2015 - Day 23: Opening the Turing Lock
 *
 * https://adventofcode.com/2015/day/23
 */
class TuringLock {
    fun execute(instructions: List<Instruction>) {
        val registers = Registers
        var pointer = 0

        while (pointer in instructions.indices) {
            val instruction = instructions[pointer]
            when (instruction.command) {
                "hlf" -> {
                    registers.set(instruction.register, registers.get(instruction.register) / 2)
                    pointer++
                }
                "tpl" -> {
                    registers.set(instruction.register, registers.get(instruction.register) * 3)
                    pointer++
                }
                "inc" -> {
                    registers.set(instruction.register, registers.get(instruction.register) + 1)
                    pointer++
                }
                "jmp" -> {
                    pointer += instruction.offset
                }
                "jie" -> {
                    if (registers.get(instruction.register) % 2 == 0)
                        pointer += instruction.offset
                    else
                        pointer++
                }
                "jio" -> {
                    if (registers.get(instruction.register) == 1)
                        pointer += instruction.offset
                    else
                        pointer++
                }
                else -> pointer++
            }
        }
    }
}

object Registers {
    private var a: Int = 0
    private var b: Int = 0

    fun get(register: String): Int {
        return when (register) {
            "a" -> a
            "b" -> b
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }

    fun set(register: String, value: Int) {
        when (register) {
            "a" -> a = value
            "b" -> b = value
            else -> throw IllegalArgumentException("Unknown register: $register")
        }
    }
}

class Instruction(val command: String, val register: String = "", val offset: Int = 0)