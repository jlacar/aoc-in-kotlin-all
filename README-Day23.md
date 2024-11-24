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

Then I created extension functions for specific map operations. First, there was the `copy()` operation. I used a vararg parameter so I could change multiple registers at one go. For example, if `r` was "a" and `newValue` was 5 
 
    state.copy(r to newValue, state.next)

Bare bones, this would be

    state + listOf(r to newValue, "pc" to (state["pc"]!! + 1)).toSet()

The extensions help make it possible to write it in a clearer way.

    fun LockState.copy(vararg newValues: Register) = this + newValues.toSet()
    fun LockState.jump(offset: Int) = "pc" to (programCounter + offset)
    fun LockState.valueOf(register: String) = this[register]!!
    val LockState.next: Register get() = this.jump(1)
    val LockState.programCounter: Int get() = valueOf("pc")

I also added convenience extensions for each register

    val LockState.a: Int get() = valueOf("a")
    val LockState.b: Int get() = valueOf("b")

