# AoC 2025: Day 1 - Secret Entrance

Puzzle Page: https://adventofcode.com/2025/day/1 

Code: [solution](Day01.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day01/Day01Test.kt)

## Overview

This problem involves a circular list of consecutive numbers and calculating wrapping indices, framed as a safe dial with a fixed number of positions (0 to 99), increasing clockwise.

## Parsing the input

Each line in the input is treated as a separate instruction that indicates the direction and number of positions to rotate the dial. A line starting an "L" indicates the dial should be rotated clockwise to go in the descending direction of the position numbers. An "R" indicates that the dial should be turned counterclockwise, going in the ascending direction of position numbers. . The rest of the line indicates the number of positions or clicks to turn to get to the next number in the combination.

To facilitation calculation, a left rotation is converted to a negative number while a right rotation is a positive number.

## Part 1

For both parts, the dial will start from position 50. The goal for part 1 is to find how many instructions end up on 0. 

This is a good candidate for using the `count()` and `fold()` functions. 

## Part 2

As with many AoC puzzles, the second part can be solved by generalizing the solution for the first part. Part 2 requires that we count not just how many times an instruction ends at 0 but also the number of times the dial lands on 0 while rotating to the next position indicated by an instruction.

## Reflection

Next time, review past problems and solutions before the start of Advent of Code because the context and algorithms are very similar. If I had done that, I could have saved a bit of time on this problem by not reinventing the wheel for calculating a wrapping index, which it turns out I had already solved for a previous year.