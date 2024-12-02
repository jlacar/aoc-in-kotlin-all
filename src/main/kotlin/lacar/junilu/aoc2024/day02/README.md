# AoC 2024 - Day 2: Red-Nosed Reports

Puzzle Page: https://adventofcode.com/2024/day/2

Code: [solution](Day02.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2024/day02/Day02Test.kt)

## Approach

This one ramped up the difficulty a couple of notches from Day 1, in my opinion. Quick-and-dirty again today. As these puzzles get more difficult, I'm sure I'll have to resort to a more test-first style to keep things straight in my head.

Both parts use `count()`, `all{}`. Part 2 uses `any{}`. The `windowed()` function was central to the solution since it's what I used to chunk the level changes in each report. Kotlin conveniently provides all these functions as part of its standard library.

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

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/23c254f935139ccddced9f022d7a54c3b0b99ebd) Note #1** - After analyzing how `canBeDampened()` worked, I realized that a call to `isSafe()` was redundant and the Part 2 call could be simplified. The `indices.map` operation in `canBeDampened()` will operate on the original list on its last iteration, with nothing removed from it. This favors input that has many unsafe reports because the `any` call will terminate when the first safe subset is found. I don't think the difference in time to run is that significant with this puzzle input.

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/73a52a51d13aade522098186968169ae185a76af) Note #2** - Renamed `pair` to `levels` to align better with the term used in the puzzle text. I think `levels[1] - levels[0]` connects better to the `windowed(2)`, `decreasing`, and `increasing` concepts. 

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/eed5fb34899a527fea672132051577808487cc5f) Note #3** - This one is a little more tricky. Not sure if it increases clarity since it results in more lines of code in `isSafe()`. It does, however, reduce the scope of the predicate expressions and keeps them in the only place they're used. I think this refactoring increases cohesion since everything that's related to each other are all in the `isSafe()` function scope. 

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/45314991a52f19f3d1d432778499033d0720f483) Note #4** - The diff shows moving the `inRange` lambda down a couple of lines but my intent was really to move `decreasing` and `increasing` up closer to `changeInLevels` so the connection to `levels[1] - levels[0]` was easier to see. Might seem like a small nuance but I think it's an improvement in cohesion.

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/900d476740de567b3d67c850b6480429aa19a23d) Note #5** - This one is courtesy of James Whiting from the FP Programming Community at work. He suggested using `zipWithNext()`, which works similarly to `windowed()`. I like how `zipWithNext()` explicitly works with two adjacent elements at a time and that the transformation function takes both elements. This way, I could give more meaningful names like `thisLevel` and `nextLevel`. Learned something new today. Love it!

Please comment if you have any thoughts about these refactorings/tidyings. Thanks!