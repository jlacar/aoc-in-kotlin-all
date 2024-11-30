# Aoc 2015: Day 25 - Let It Snow

Puzzle Page: https://adventofcode/2015/day/25

## Approach 

First thought: use a sequence to generate codes. Use `take()` to get to the code identified by the row and column inputs.

The row determines how many numbers are in the diagonal. That is, row 1 has one number in the diagonal, row 2 has two numbers, row 3 has three, and so on.
The column determines the ordinal position on the diagonal. This will allow us to calculate N, which will be passed to `take()`.

Formula for the first number in each row (to memoize):

    ordinalOfFirstNumber(row N) = N + ordinalOfFirstNumber(N-1)


## Notes

## Design

## Discoveries