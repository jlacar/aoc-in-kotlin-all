# AoC 2025: Day 10 - Factory

Puzzle Page: https://adventofcode.com/2025/day/10 

Code: [solution](Day10.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day10/Day10Test.kt)

## Overview

This problem involves toggling between two states: OFF and ON.

## Parsing the input

Each line in the input is composed of three parts:
1. An indicator light pattern
2. A list of buttons to press to configure the indicator lights
3. A list of joltages 

Each line describes a machine that needs to be configured using the buttons available for it.

I defined a data class to represent the machine with properties for each of the above parts.

Parsing of the second and third parts was fairly straightforward using the custom parsing utility function `findInts()` that I already had defined for the previous years.

Parsing the first part was a bit more involved but not too bad: Map each `#` to its corresponding 0-based position in the pattern, so a pattern would be represented as a set of integers instead of a string. Each integer in the set represented the position of an indicator light that should be ON.

Where appropriate, I used a `dropEnds()` utility function to remove enclosing parentheses or brackets before parsing the values enclosed in them.

## Part 1

Had to think backwards on this. Instead of figuring out how to turn the appropriate lights on to match the given pattern, I reversed the goal and looked for the combination that would turn all the lights in the pattern OFF.

## Part 2

TBD

## Reflection

It seems I have to keep relearning the lesson that whenever my solution gets big and unwieldy, it's a good sign that the approach isn't working and I need to understand the problem better. This happened again with part 1.

I started out thinking that this was a [Set Cover problem](https://en.wikipedia.org/wiki/Set_cover_problem) but eventually realized that I could use combinations, starting with the smallest combination of buttons and increasing the size until I found a combination of buttons that would reset all the lights in the machine's indicator lights.
