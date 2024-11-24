# Day 23: Opening the Turing Lock

## AI Assistant Notes

I thought of using an enumeration to represent the instructions for the Turing lock and the AI Assistant surprised me by suggesting all the correct enum values.

    enum class Instruction { // I just typed this header
        HLF, TPL, INC, JMP, JIE, JIO;  // and the AI immediately gave this
    }

I think what surprised me the most was the amount of context the AI had used in giving that suggestion. I don't know if it used the link to the puzzle page that I always put in the header comment for every solution but that was the first thought that came to mind. 

The JetBrains documentation I found said that AI Assistant uses files from the current project and that tabs you currently have open. I had the input data in a file in the `main/resources` folder of my project and I'm not sure if was open in one of the tabs at that point.

When prompted, the AI Assistant generated some fairly decent code that, with a few minor tweaks, arrived at the correct answer to part 1 of the puzzle.

### Explaining errors

I made a mistake in the parsing code and got a `NumberFormatException` when trying to convert the offset to an `Int`.

When asked to explain, part of the AI Assistant's explanation had this:

> The problem likely arises from these lines:
> 
>      (some lines of code around the bug)
>      ... line.substring(4, 5).toInt() ... // the bug!
>      (some lines of code around the bug)
> Here, the assumption that `line.substring(4, 5)` will always produce a valid integer, but if the input contains a "+" sign, it fails.
> 
> Instead, we need to handle cases where the substring might include a sign or the number might have multiple characters. We can use the `trim` method and the `toIntOrNull` method to safely parse integers.

#### The Good

AI Assistant was able to narrow in on the code that had the bug. This isn't surprising the context the AI used to generate an explanation includes the stack trace, which reference the line of code that produced the exception. It at least saved me the tedium of having to pore over the stack trace and find the exact line of code that threw the exception.

#### The Not so Good

The explanation the AI Assistant gave for the problem, unfortunately, was incorrect. The `toInt()` function has no problem with a "+" sign and will correctly interpret it. 

One of its suggestions to use `trim` could have been a solution. The suggestion to use `toIntOrNull` wasn't really an option in the case though so I ignored it.

The AI Assistant missed the real problem, which was an off-by-one error: I had used the wrong start index in the `substring()` call, which gave `" +12"` instead `"+12"`. The problem was the leading space, not the `"+"` sign, which the `toInt()` can handle just fine.


In this case, the AI Assistant was still helpful in that it helped me to quickly identify where the bug was. However, it still needed me, the human programmer, to vet the validity of its explanation and make the correct decision on how to fix the bug.

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
