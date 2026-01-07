# AoC 2015 Day 1 - Not Quite Lisp

Puzzle page: https://adventofcode.com/2015/day/1

Code: [solution](./Day01.kt) | [tests](../../../../../../test/kotlin/lacar/junilu/aoc2015/day01/)

# Parsing the Input

Since the puzzle input is a series of parentheses with each character representing a direction, there's no need to parse the input into a data structure.
Interpreting the directions literally is all that's needed: `(` means go up one floor, `)` means go down one floor. Starting floor is always `0`

# Part 1

I used `fold()` to find the floor that Santa ends up on after following all directions. This is straightforward.

    override fun part1(): Int = directions.fold(0) { floor, direction ->
        floor + if (direction == '(') 1 else -1
    }

In 2026, I refactored the code to use a sequence. See the [Refactoring to a Sequence](#refactoring-to-a-sequence) section for more details.

# Part 2

I used `runningFold()` to find the first time Santa enters the basement.

    override fun part2(): Int = directions.runningFold(0) { floor, direction -> 
        floor + if (direction == '(') 1 else -1
    }.indexOfFirst { it == -1 }

# Refactoring

As short as the first cut of the program is, there were still opportunities to refactor for clarity. 

## Better Naming and Extracting Implementation Details

The names `part1()` and `part2()` were refactored to `lastFloor()` and `positionOfFirstTimeInBasement()`, respectively.

The shared logic for calculating the next floor visited was also extracted.

    private val nextFloor: (Int, Char) -> Int = { currentFloor, direction ->
        currentFloor + if (direction == '(') 1 else -1
    }

Finally, I refactored the calculation expressions to use `inc()` and `dec()` instead.

    private val nextFloor: (Int, Char) -> Int = { currentFloor, direction ->
        if (direction == '(') currentFloor.inc() else currentFloor.dec()
    }

## Over-engineering vs. practicing good habits 

For a program this small, it may seem a bit heavy-handed to do any refactoring at all. However, this is practice that will be applied in real-world programs. This kind of attention to detail paves the way for a gentler and more enjoyable experience for readers trying to understand the code.

## Using a sequence for Part 2 (first solution)

When I extracted the `runningFold()` operation in Part 2, I also added `.asSequence()` to the call chain to make sure I was operating on a sequence rather than a collection. This allowed the iteration of `runningFold()` to be terminated as soon as the first `-1` floor was found. 

Without the conversion to a sequence, the `runningFold()` would go through the entire set of directions, which would create a huge intermediate list for long inputs. With the sequence, the iterations are terminated as soon as `floor` becomes `-1` and the rest of the characters in the input will not be processed. The sequence essentially allows us to shortcircuit the iteration.  

# Refactoring to a Sequence

Revisiting this code in 2026, I realized I could use a sequence to represent the floors visited by Santa. This made both parts of the solution more symmetrical by using the same sequence as the start of their respective call chains.

I used `generateSequence()` to create the sequence of floors visited on demand, capturing an iterator for the directions in a closure. This is an example of a high-order function that returns a sequence generator function.

```kotlin
private fun floors(): Sequence<Int> {
   val iterator = directions.iterator()
   return generateSequence(0) {
      it.nextFloor(iterator)
   }
}
```

I refactored this further by using the `let()` scope function to capture the iterator in a closure and return the sequence generator function. I also replaced the `nextFloor()` function with a simple `go()` extension function that returns the next floor based on the direction.

```kotlin
private fun floors(): Sequence<Int> = directions.let { directions ->
    generateSequence(0) { floor ->
        if (directions.hasNext()) {
            floor.go(directions.next())
        } else null
    }
}
```

# Adding Kotest Tests

In December 2025 and January 2026, I started looking at other testing-related frameworks. I added Kotest framework and started adding tests that used different Kotest styles. The Kotest assertions are also nice, with the `shouldBe()` assertion being the most common way to verify the test results.