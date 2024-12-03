# AoC 2024 - Day 2: Red-Nosed Reports

Puzzle Page: https://adventofcode.com/2024/day/2

Code: [solution](Day02.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2024/day02/Day02Test.kt)

## Approach

This one ramped up the difficulty a couple of notches from Day 1, in my opinion. Quick-and-dirty again today. As these puzzles get more difficult, I'm sure I'll have to resort to a more test-first style to keep things straight in my head. The upside to doing a quick-and-dirty solution is that it gives me many opportunities to tidy up and refactor the code to a better, cleaner, and clearer state.

Both parts use `count()`, `all{}`. Part 2 uses `any{}`. The `windowed()` function, and later on `zipWithNext()`, was central to the solution since it's what I used to chunk the level changes in each report. Kotlin conveniently provides all these functions as part of its standard library.

### Part 1

Once again, Kotlin made it easy to express abstract ideas clearly and succinctly.

The key ideas taken from the puzzle description revolve around what makes a report "safe":
- all changes between levels are either **all increasing** or **all decreasing** _and_
- all changes between levels are by 1, 2, or 3 (expressed as a range in Kotlin: `1..3`)

The `windowed()` function is the workhorse for chunking the report into pairs of adjacent levels. I initially used `windowed(2)` for this but in a later refactoring switched to `zipWithNext()` which chunks the list into pairs of adjacent elements. See Refactoring Note #5 below.

IntelliJ IDEA has a very convenient intent (`⌥ Option` + `↩ Enter` on Mac) for converting a parameter into a receiver. I've used this often to make the code more expressive:

* `isSafe(report)` becomes `report.isSafe()`
* `areConsistent(changes)` becomes `changes.areConsistent()`
* `inSafeRange(changes)` becomes `changes.inSafeRange()`

This is achieved with [extension functions](https://kotlinlang.org/docs/extensions.html).

### Part 2

Initially, I created a separate function, `howManyAreSafeWithDampener()`, for Part 2. I eliminated it later by adding 
a `useDampener` parameter to `howManyAreSafe()` from Part 1. A default value of `false` kept the Part 1 calls unchanged.
 
    report.howManyAreSafe()                    // Part 1 call
    report.howManyAreSafe(useDampener = true)  // Part 2 call 

To handle "dampening" of an otherwise unsafe report, I created a new function, `canBeDampened()`, which let me express the Part 2 intent clearly

    report.isSafe() || report.canBeDampened()

This was further simplified in a later refactoring. See Refactofing Note #1 below.

## Reflection

I have to admit, the quick-and-dirty solution took me longer than I thought it would, getting the second star at around 1:06am ET.

However, the quick-and-dirty code also gave me a lot of opportunities to refactor to clear, expressive code. 

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/23c254f935139ccddced9f022d7a54c3b0b99ebd) Note #1** - After analyzing how `canBeDampened()` worked, I realized that calling `isSafe()` on the full report in Part 2 is redundant because if a full report is safe, removing its first level gives a subset report that is also safe. Same thing is true if you remove the last level. Therefore, `report.isSafe() || report.canBeDampened` can be simplified to just `report.canBeDampened`.

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/73a52a51d13aade522098186968169ae185a76af) Note #2** - Renamed `pair` to `levels` to align better with the term used in the puzzle text. I think `levels[1] - levels[0]` connects better to the `windowed(2)`, `decreasing`, and `increasing` concepts. This was refactored further later. See Refactoring Note #5 below.

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/eed5fb34899a527fea672132051577808487cc5f) Note #3** - This one was a little tricky. Not sure if it increased clarity since it ended up with more lines of code in `isSafe()`. However, it reduced the scope of the predicate expressions and kept them in the only place they're used. I think this refactoring increased cohesion since everything that's related to each other are all inside the `isSafe()` function's scope. 

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/45314991a52f19f3d1d432778499033d0720f483) Note #4** - The diff shows moving the `inRange` lambda down a couple of lines but my intent was really to move `decreasing` and `increasing` up closer to `changeInLevels` so the connection to `levels[1] - levels[0]`, and later `nextLevel - thisLevel` was easier to see. It might seem like a trivial change but I think there's enough nuance to improve code cohesion and lessen cognitive load.

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/900d476740de567b3d67c850b6480429aa19a23d) Note #5** - This one is courtesy of James Whiting from the FP Programming Community at work. He suggested using `zipWithNext()`, which works similarly to `windowed()`. I like how `zipWithNext()` works with two adjacent elements at a time and that the transformation function takes both elements of each pair as its parameters to which I gave more meaningful names, `thisLevel` and `nextLevel`. I learned something new today. Love it! :)

**[Refactoring](https://github.com/jlacar/aoc-in-kotlin-all/commit/368e38a2d27c7a8632ffd29e4845e5eccccac7d0) Note #6** - Reduced (I think) the noise from the explicit function type declarations in `isSafe()` and inlined the transform function for `zipWithNext()`. I feel like this improves readability.

Please comment if you have any thoughts about these refactorings/tidyings. Thanks!