# Aoc 2015: Day 25 - Let It Snow

Puzzle Page: https://adventofcode.com/2015/day/25

Code: [solution](Day25.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2015/day25/Day25Test.kt)

## Approach 

Calculate the ordinal, N, for the code at row and column. Then use `take()` and `last()` to get the Nth code of the sequence.

I used `generateSequence()` to generate the code sequence.

### Geometry

Had to use graphing paper to visualize the calculation of the ordinal for a code at a given coordinate.

Key concepts:
- The value of the row or column is equal to the number of codes in the diagonal that intersects the X or Y axis. That is, the diagonal through (5, 1) will have 5 codes. Likewise, the diagonal through (1, 10) will have 10 codes in it.
- The diagonal for (row, col) starts at (row + col - 1, 1) 
- The sum of numbers from 1..N is equal to N * (N + 1) / 2

### Reflection

Breaking down the problem into smaller chunks was once again key to getting the final solution. Luckily, this puzzle only had two chunks: finding the ordinal position of the code, then generating the Nth number in the sequence to get the answer. 

I used a parameterized test to make it easier to incrementally add different cases.

First approach I took was a recursive solution. I walked in the different cases starting with the degenerate case of (1, 1). Then I handled the case where row > 1 and column is 1. Finally, I worked out the case where both row and column are greater than 1. The `when` expression reflects this progression.

Later on, after looking at Andrew Pickett's solution, I realized that calculating the ordinal was similar to the problem of getting the sum of the numbers from 1..N. This is a straightforward calculation with no recursion necessary. I used the formulas listed in the Geometry section; not sure if they're the same as what Andrew used though.