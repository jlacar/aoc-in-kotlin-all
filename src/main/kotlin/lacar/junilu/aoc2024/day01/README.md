# AoC 2024 - Day 1: Historian Hysteria

Puzzle Page: https://adventofcode.com/2024/day/1

Code: [solution](Day01.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2024/day01/Day01Test.kt)

## Here we go! 
:)

This year is the first time I've done a quick-and-dirty solution just to get my stars. I must say, there are some pretty hard-core competitors out there waiting for the stroke of midnight for the next puzzle. By the time I got my two stars for Day 1 at a quarter past midnight, I was already at #54 in the private leaderboard at work and tied for 8th place in my local location leaderboard.

Keeping up and catching up with these guys isn't going to be easy but I'm looking forward to all the fun.

## Approach

My first cut was a quick-and-dirty implementation, with no tests. The bulk of the work was in parsing the input.

### Part 1

Goal: find the total distance between the two lists

Split the input into two sorted columns, then sum up the absolute values of the differences between corresponding elements in each list.

I used `sorted()`, `map()`, `abs()`, and `sum()`.

### Part 2

Goal: find the total _similarity score_

This one didn't need the two columns to be sorted but that's what I used anyway. I used `count { it == n }` to get the multiplier for each element in the first column. 

## Experiments and Discoveries

I'm experimenting with putting puzzle parsing logic in a companion object of the test class. We'll see how I like this approach after a few days worth of puzzles. Right now, it helps keep the solution code focused on the problem-solving.

## Reflections

[Liutauras Vilda pointed out on CodeRanch](https://coderanch.com/t/785796/Advent-Code#3581662) that the sorting of the lists in Part 1 were part of the problem statement. He's right so I moved the sorting out of the parsing logic and back into the solution logic.

Still haven't built into muscle memory the refactoring of simplifying call chains like `map{ ... }.sum()` to `sumOf{ }`. Needed IDE to suggest it before I saw it. More practice is needed. :)