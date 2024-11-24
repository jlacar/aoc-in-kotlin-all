# Day 23: Opening the Turing Lock

## AI Assistant Notes

The AI Assistant surprised me when it was able to suggest all the enum values for the instructions when all I typed in was `enum class Instruction {}`. I'm speculating that it used the link to the puzzle page that I always put in the header comment for every solution to get some context. Another possibility is that it used the puzzle input file that is in the `main/resources` folder of my project.

It was also able to generate code that got the correct answer, with a few minor tweaks.

## Design Notes

### Using a Map to model the lock registers 

I first considered creating a class to encapsulate the logic related to the registers, a and b, along with the program counter, (pc). 

    data class Registers(val a: Int, val b: Int, val pc: Int)

Using this, however, got pretty complicated and unwieldy, with lots of code like this:

    return when (r) {
        "a" -> state.copy(a = newValue)
        "b" -> state.copy(b = newValue)
        else -> throw IllegalArgumentException("Unknown register: $r")
    }

The AI Assistant generated code that used a map with "a", "b", and "pc" to represent the two registers and the program counter. Using a map made it easier to manage the values of the registers and program counter. 

First, I added a couple of type aliases to give context and improve semantics

    typealias Register = Pair<String, Int>
    typealias LockState = Map<String, Int>

Then I created extension functions for specific map operations. First, there was the `copy()` operation. I used a vararg parameter so I could change multiple registers at one go. For example, if `r` was "a" and `newValue` was 5, I would write.
 
    state.copy(r to newValue, state.next)

This essentially created a new map with new values for the specified keys. In this example, `r to newValue` would replace the value of the "a" register, and increment the `pc` by `1`. Bare bones, the above line is equivalent to

    state + listOf(r to newValue, "pc" to (state["pc"]!! + 1)).toSet()

These are all the extensions I defined to make the high-level code easier to read.

    fun LockState.copy(vararg newValues: Register) = this + newValues.toSet()
    fun LockState.jump(offset: Int) = "pc" to (programCounter + offset)
    fun LockState.valueOf(register: String) = this[register]!!
    val LockState.next: Register get() = this.jump(1)
    val LockState.programCounter: Int get() = valueOf("pc")

I also added convenience extensions for getting the value of each register

    val LockState.a: Int get() = valueOf("a")
    val LockState.b: Int get() = valueOf("b")

The reason behind all these was to limit the scope of having to know the literal values of the map keys. That is, the `LockState.programCounter` property extension encapsulates the knowledge that `"pc"` is the key for the program counter. Likewise for the `LockState.a` and `LockState.b` property extensions. 

I also added the `initialLockState()` function as another convenience and limit the scope of the literal map key values.

Overall, I think I achieved my goal of encapsulating and limiting scope because there are no uses of the literal key values in the high level code at all.

### Using `invoke()`

The `invoke()` function allows you to execute a function object. 
