# AoC 2025: Day 1 - Secret Entrance

Puzzle Page: https://adventofcode.com/2025/day/1 

Code: [solution](Day01.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day01/Day01Test.kt)

## Overview

This problem involves a circular list of consecutive numbers and calculating wrapping indices, framed as a safe dial with a fixed number of positions (0 to 99), increasing clockwise.

## Parsing the input

Each line in the input is treated as a separate instruction that indicates the direction and number of positions to rotate the dial. A line starting with an "L" indicates the dial should be turned clockwise, going in the direction of descending position numbers. An "R" indicates that the dial should be turned counterclockwise, going in the direction of ascending position numbers. . The rest of the line is a number that indicates the number of positions or clicks to turn to get to the next number in the combination. The number can be greater than 99, in which case   the dial is to be turned at least one full revolution.

To facilitate calculation, a left rotation is converted to a negative number and a right rotation to a positive number.

## Part 1

For both parts, the dial will start from position 50. The goal for part 1 is to find how many instructions rotate the dial to 0. 

This is a good candidate for using the `count()` and `fold()` functions. 

## Part 2

As with many AoC puzzles, the second part can be solved by generalizing the solution for the first part. Part 2 requires that we count not just how many times an instruction ends at 0 but also the number of times the dial points to 0 as it is rotated to the next position. That is, if the dial starts at position 5 and the next instruction is "L30", it will point to 0 one time. If the instruction was "R30", at no time will the dial point to 0 as it is rotated to the next position. 

This is the trickiest part of the puzzle. Calculation will be different for left and right rotations.

## Reflection

Next time, review past problems and solutions before the start of Advent of Code because the context and algorithms are very similar. If I had done that, I could have saved a bit of time on this problem by not reinventing the wheel for calculating a wrapping index, which it turns out I had already solved for a previous year.