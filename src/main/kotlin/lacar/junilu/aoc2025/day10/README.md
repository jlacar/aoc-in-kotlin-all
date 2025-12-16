# AoC 2025: Day 10 - Factory

Puzzle Page: https://adventofcode.com/2025/day/10 

Code: [solution](Day10.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day10/Day10Test.kt)

## Overview

This problem involves toggling between two states: OFF and ON.

## Parsing the input

Each line in the input is composed of three parts:
1. A light pattern
2. A list of buttons
3. A list of joltages 

Each line describes a machine that needs to be configured using the buttons provided.

I defined a data class to represent the machine with properties for each of the above parts.

Parsing of the second and third parts was fairly straightforward using the custom parsing utility function `findInts()` that I already had defined for the previous years.

Parsing the first part was a bit more involved but not too bad.

On all parts, I used a `dropEnds()` utility function to remove enclosing parentheses or brackets before parsing the values enclosed in them.

## Part 1

TBD 

## Part 2

TBD

## Reflection

It seems I have to keep relearning the lessong that whenever my solution gets big and unwieldy, it's a good sign that I should abandon the current approach and rethink the problem. This happened again with part 1.

I started thinking that this was a [Set Cover problem](https://en.wikipedia.org/wiki/Set_cover_problem) but eventually solved it using combinations, starting with the smallest combination of buttons and increasing the sample size until I found a combination that reset all the lights in the pattern.
