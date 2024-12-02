# AoC 2024: Day 2 - Red-Nosed Reports

Puzzle Page: https://adventofcode.com/2024/day/2

Code: [solution](Day02.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2024/day02/Day02Test.kt)

## Approach

This one ramped up the difficulty a couple of notches from Day 1, in my opinion. Quick-and-dirty again today. As these puzzles get more difficult, I'm sure I'll have to resort to a more test-first style to keep things straight in my head.

Both parts use `count()` and `any{}` quite a bit. A key operation to the solution was using the `windowed()` function which Kotlin conveniently provides it as part of its standard library.

### Part 1

Once again, I found that Kotlin makes it really easy to express an idea directly in code.

The key ideas I wanted to express were that a report is safe if: 
- all changes between levels are in the same direction (changes are consistent) _and_
- all changes between levels are by 1, 2, or 3 (expressed as range in Kotlin: `1..3`)

The `windowed()` function makes it easy to compare successive chunks of a list. I used `windowed(2)` so I could compare each pair of numbers in a list.

IntelliJ IDEA has a very convenient intent of converting a parameter into a receiver so the code becomes very expressive:

    report.isSafe()
    changes.areConsistent()
    changes.areInSafeRange()

### Part 2

Initially, I created a separate function, `howManyAreSafeWithDampener()` for Part 2.

By adding a `useDampener` parameter to `howManyAreSafe()`, I could eliminate the extra function and keep the original Part 1 call while allowing for the additional requirement in Part 2 to use the Problem Dampener.
 
    report.howManyAreSafe()                    // Part 1 call
    report.howManyAreSafe(useDampener = true)  // Part 2 call 

For the dampening, I added a new function which allowed me to express the Part 2 requirement clearly

    report.isSafe() || report.canBeDampened()

## Reflection

I have to admit, the quick-and-dirty solution took me longer than I thought it would, getting the second star at around 1:06am ET.

However, the quick-and-dirty code also gave me a lot of opportunities to refactor to clear, expressive code. 

After analyzing how `canBeDampened()` worked, I realized that a call to `isSafe()` was redundant and the Part 2 call could be simplified.
