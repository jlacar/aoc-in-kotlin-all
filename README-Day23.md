# Day 23: Opening the Turing Lock

## AI Assistant Notes

The AI Assistant surprised me when it was able to suggest all the enum values for the instructions when all I typed in was `enum class Instruction {}`. I'm speculating that it used the link to the puzzle page that I always put in the header comment for every solution to get some context. Another possibility is that it used the puzzle input file that is in the `main/resources` folder of my project.

It was also able to generate code that got the correct answer, with a few minor tweaks.

# Design Notes

Representation the registers with a new class got complicated. At first, I tried using a data class.

    data class Registers(val a: Int, val b: Int, val pc: Int)

Using this, however, got pretty unwieldy and produced a lot of code like this:

    return when (r) {
        "a" -> state.copy(a = newValue)
        "b" -> state.copy(b = newValue)
        else -> throw IllegalArgumentException("Unknown register: $r")
    }

The AI Assistant generated code that used a map with "a", "b", and "pc" to represent the two registers and the program counter, that is, the offset of the current instruction being executed. Using a map made it easier to make a copy. 

First, I added a couple of type aliases to give context and improve semantics

    typealias Register = Pair<String, Int>
    typealias LockState = Map<String, Int>

Then I created extension functions for specific map operations. First, there was the `copy()` operation. I wanted to be able to 

    fun LockState.copy(vararg newValues: 