# AoC 2025 Day 9 - Movie Theater

Puzzle Page: https://adventofcode.com/2025/day/9

Code: [solution](Day09.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day01/)

# Parsing the input

Each line in the puzzle input is a comma-separated pair of integers representing the location of a red tile. Each line is parsed to a `Point2D` object which is part of the common library of data structures I have built up for AoC.

# Part 1

We need to find the area of the largest rectangle that can be formed by any pair of red tiles that form opposite corners of the rectangle. I paired up each red tile with all other red tiles listed after it, then took the maximum of the thin area. 

# Part 2

The coordinates given in the input define the perimeter of a closed polygon. In part 2, we need to find the area of the largest rectangle that lies entirely within the polygon.