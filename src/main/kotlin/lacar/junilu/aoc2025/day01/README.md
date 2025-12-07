# AoC 2025: Day 1 - Secret Entrance

Puzzle Page: https://adventofcode.com/2025/day/1 

Code: [solution](Day01.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day01/Day01Test.kt)

## Overview

This problem involves a circular list of consecutive numbers and calculating wrapping indices.

Most AoC problems start with the first part as a simple scenario that can later be generalized to solve the second part. This problem follows that pattern but it took me a while to find a way to generalize my initial solution.

## Parsing the input

Each line in the input starts with an "L" or an "R" that indicates the direction traversing the list of consecutive numbers. The rest of the line indicates the number of steps to take to the next number. Converting this to a negative number for the left direction and a positive number for the right direction allows us to easily calculate the wrapping index.

## Part 1

For the first part, we need to treat each line as a separate instruction and calculate the wrapping index for each one. From the initial position of 50, the goal is to find how many instructions end up on 0. This seems like a good candidate for using `count()` and `fold()`. 

## Part 2

Part 2 requires that we count not just how many times an instruction ends at 0 but also the number of times the index becomes 0 as it is moved to the next position.

## Notes

To prepare for AoC, it would help to reacquaint yourself with problems and solutions you've worked on before. Over the years, I have collected a number of utilities that can speed up solve times. If I had done that, I could have saved a bit of time on this problem by not reinventing the wheel for calculating a wrapping index.