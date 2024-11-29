# Aoc 2015: Day 25 - Let It Snow

Puzzle Page: https://adventofcode/2015/day/25

## Approach 

First thought: use a sequence and _take()_ which will be passed N which is calculated from the specified row and column.

The row determines how many numbers are in the diagonal. That is, row 1 has one number in the diagonal, row 2 has two numbers, row 3 has three, and so on.
The column determines the ordinal position on the diagonal

Formula for the first number in each row (to memoize):

ordinalOfFirstNumber(row N) = N + ordinalOfFirstNumber(N-1)

## Notes

## Design

## Discoveries