# AoC 2015 Day 2 - I Was Told There Would Be No Math

Puzzle page: https://adventofcode.com/2015/day/2

Code: [solution](./Day02.kt) | [tests](../../../../../../test/kotlin/lacar/junilu/aoc2015/day02/)

# Parsing the Input

Each line in the input represents the dimensions of a box: length x width x height. This is parsed by splitting the line on `x` and converting the resulting list of strings to integers.

# Part 1

To get the area of the wrapping paper needed, we need the surface area of the box plus the product of its two smallest sides.

# Part 2

To get the length of ribbon needed, we need the smallest perimeter of the box plus a length equal to the volume of the box for the bow.
