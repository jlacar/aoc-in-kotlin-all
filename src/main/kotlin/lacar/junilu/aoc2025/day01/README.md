# AoC 2025: Day 1 - Secret Entrance

Puzzle Page: https://adventofcode.com/2025/day/1 

Code: [solution](Day01.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day01/Day01Test.kt)

## Overview

This problem involves a circular list of consecutive numbers and calculating wrapping indices. These are framed in the context of a safe with a circular dial and a fixed number of positions (0 to 99), with the numbers increasing as we go clockwise around the dial.

## Parsing the input

Each line in the input is treated as a separate instruction that tells us the direction and number of positions to rotate the dial. 

An instruction starting with an "L" means we need to turn the dial clockwise, moving through descending positions. An "R" means we need to turn the dial counterclockwise, moving through ascending positions.

The direction is followed by a number which specifies the number of positions or clicks of the dial to turn to get to the next number in the combination. This number can be greater than 99, in which case the dial will be turned at least one full revolution.

To facilitate calculation, a left rotation is converted to a negative number and a right rotation to a positive number and the list of instructions is passed to the solution class as a `List<Int>`.

For both parts, the dial will start at position 50.

## Part 1

The goal for part 1 is to find how many instructions rotate the dial to 0. This is a good candidate for using the `count()` and `fold()` functions. 

## Part 2

As with many AoC puzzles, the second part can be solved by generalizing the solution for the first part. Part 2 requires that we count not just how many times an instruction ends at 0 but also the number of times the dial points to 0 as it is turned to the next position. That is, if the dial starts at position 5 and the next instruction is "L30", it will point to 0 one time. If the instruction was "R30", it will not point to 0 at all as it is turned to position 35. 

Calculating the number of times position 0 is passed will be different for left and right rotations.

## Reflection

Next time, review past problems and solutions before the start of Advent of Code because the context and algorithms are very similar. If I had done that, I could have saved a bit of time on this problem by not reinventing the wheel for calculating a wrapping index, which it turns out I had already solved for a previous year.