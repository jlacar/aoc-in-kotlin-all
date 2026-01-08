# AoC 2015 Day 1 - Not Quite Lisp

Puzzle page: https://adventofcode.com/2015/day/1

Code: [solution](./Day01.kt) | [tests](../../../../../../test/kotlin/lacar/junilu/aoc2015/day01)

# Parsing the Input

There's no need for an abstract data type for the puzzle input. It is a series of parentheses, each one representing a direction:  `(` means go up one floor, and `)` means go down one floor. Santa always starts at floor `0`.

To make the code more readable, however, I created an `isUp()` extension function on `Char`.

    private fun Char.isUp() = this == '('

# Part 1

To find the floor that Santa ends up on after following all directions, I used a `fold()` operation.

    override fun part1(): Int = directions.fold(0) { floor, direction ->
        floor + if (direction.isUp()) 1 else -1
    }

In 2026, I refactored the code to use a sequence. See the [Refactoring to a Sequence](#refactoring-to-a-sequence) section for more details.

# Part 2

To find the position of the first direction that puts Santa in the basement (floor == -1) I used `runningFold()` and `indexOfFirst()`.

    override fun part2(): Int = directions.runningFold(0) { floor, direction -> 
        floor + if (direction.isUp()) 1 else -1
    }.indexOfFirst { it == -1 }

# Refactoring

As short as this program is, I still found opportunities to refactor for clarity. 

To make the code use the language from the puzzle description, I renamed `part1()` and `part2()` to `lastFloor()` and `firstPositionInBasement()`, respectively.

I also extracted the shared logic for calculating the next floor visited to a private function.

    private val nextFloor: (Int, Char) -> Int = { currentFloor, direction ->
        currentFloor + if (direction.isUp()) 1 else -1
    }

Finally, I refactored the calculation expressions to use `inc()` and `dec()` instead.

    private val nextFloor: (Int, Char) -> Int = { currentFloor, direction ->
        if (direction == '(') currentFloor.inc() else currentFloor.dec()
    }

## Practicing good habits v. over-engineering 

For a program this small, it may seem a bit heavy-handed to do any refactoring at all. However, I'm not solving these problems to compete based on time or brevity. I do this to practice habits that I can apply in real-world development. By being mindful of code design and readability, I aim to improve my skill in writing code that is more readable and maintainable.

## Using a sequence for Part 2 (first solution)

When I extracted the `runningFold()` operation in Part 2, I also added `.asSequence()` to the call chain to make sure I was operating on a sequence rather than a collection. This allowed `runningFold()` to terminate as soon as the answer was found. 

# Refactoring to a Sequence

Revisiting this code in 2026, I realized I could use a sequence to represent the floors visited by Santa. This made parts 1 and 2 more symmetrical.

I used `generateSequence()` to create the sequence of floors visited on demand, capturing an iterator for the directions in a closure. This is an example of a high-order function that returns a sequence generator function.

```kotlin
private fun floors(): Sequence<Int> {
   val iterator = directions.iterator()
   return generateSequence(0) {
      it.nextFloor(iterator)
   }
}
```

I refactored this further to use the `let()` scope function to capture the iterator and return the sequence generator function. 

I also replaced the `nextFloor()` function with the `go()` extension function, changing the semantics.

```kotlin
    private fun floors(): Sequence<Int> = directions.iterator()
    .let { directions ->
        generateSequence(0) { floor ->
            if (directions.hasNext()) {
                floor.go(directions.next())
            } else null
        }
    }
```

# Adding Kotest Tests

In December 2025 and January 2026, I started looking at other testing-related frameworks. I added Kotest framework and started adding tests that used different Kotest styles. The Kotest assertions are also nice, with the `shouldBe()` assertion being the most common way to verify the test results.