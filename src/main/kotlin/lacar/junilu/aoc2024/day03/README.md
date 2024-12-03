# AoC 2024 - Day 3: (title)

Puzzle Page: https://adventofcode.com/2024/day/3

Code: [solution](Day03.kt) - [test](../../../../../../test/kotlin/lacar/junilu/aoc2024/day03/Day03Test.kt)

## Here Be (RegEx) Dragons!

> "When faced with a problem, some people say 'Let's use RegEx.' Now, they have two problems." --a wise person on the Internet

See https://regex.info/blog/2006-09-15/247 and decide who to attribute that quote to.

## Approach

Let me just start by saying this: I don't like solving problems that involve RegEx. That being said, it seems a regular expression is the best tool for this one. 

It took me over three hours of wrangling with the regex incantations and matches to get it just right. At one point during this struggle, I seriously considered giving up and taking a brute force approach. Sunk Cost prevented me from doing that though so I muddled through.

### Part 1

Once I had a regex that worked, it was fairly straightforward to use `sumOf{}` and deconstructing parameters to get the answer.

### Part 2

This was a bit of challenge for me to keep with a functional style. I didn't fully achieve immutability though but at least I got to where the mutating was contained.

## Reflections


