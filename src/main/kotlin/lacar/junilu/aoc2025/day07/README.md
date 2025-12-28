# AoC 2025: Day 7 - Laboratories

Puzzle Page: https://adventofcode.com/2025/day/7 

Code: [solution](Day07.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day07/Day07Test.kt)

## Overview

This problem involves a machine that is very similar to a [quincunx](https://en.wikipedia.org/wiki/Quincunx), also known as a Galton Board. The machine in the puzzle is called a tachyon manifold. Unlike Galton board, however, tachyon beam pass through the manifold and instead of pegs, we have splitters that split any beam that hits it in two, one going to the left and the other to the right of the splitter. Essentially, the manifold is like a quincunx with missing pegs.

## The input data

The input is a character representation of manifold and the splitters in it. A `'.'` represents space while a `'^'` represents a splitter. Beams enter the manifold from a point at the top of manifold indicated with an `'S'` and travel downwards through the manifold toward the bottom, changing direction only when it hits a splitter.

I didn't see any need to parse the input data into a more structured format so in this problem, I just used the strings read from the input. However, I did choose to ignore every other line since the line we really need to work with are the ones with the splitters in them. So, I started with two pieces of information:

1. The horizontal position of the entry point as indicated by `'S'`, which I chose to call `source`.

    private val source = lines.first().indexOf('S')

2. The manifold as a list of strings, chunked by two lines.

    private val manifold = lines.drop(2).chunked(2)

## Part 1

The first part asks us to calculate how many times a beam that enters the manifold is split before it reaches the other end. To do this, we have to follow the path of the beam from the top of the manifold and track how many times it hits a splitter. Subsequently, we have to track the split beams and add them to the total. This continues until all the beams have reached the other end of the manifold.

Of course, it's not as simple as counting the number of splitters because there's a chance that a splitter may not get hit by the beam at all. We need to track where the beams are on each level of the manifold and add the number of splitters that are hit. This means tracking the horizontal position of the beam on each level. The horizontal positions need to be updated whenever the beam hits a splitter.

I chose to use a Boolean array to track the horizontal positions of the beams on each level. The array is sized to the width of the manifold with the `source` element set to true to represent the beam at the entry point to the manifold. I used the `also()` scope function to initialize the source beam position.

    val beams = BooleanArray(width).also { it[source] == true }

As the beam travels down the manifold and gets split, this array will be updated to reflect the new horizontal positions of the beams. To make the code easier to read, I defined an extension function:

    private fun Char.isSplitter() = this == '^'

Updating the horizontal positions of the beams is as simple as checking if the current position is a splitter and updating the `beams` array accordingly:

    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
    }

Since we needed to count how many splits occurred, I made the `if` expression have a value of `1` if a splitter was hit and `0` otherwise.

    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
        1
    } else 0

A `sum()` operation around this gives the total number of splits on each level.

Processing the entire manifold was done with a `fold()` operation, with `0` as the initial value for `totalSplits` and the number of splits on each level added to it in each iteration. Since the lines from the input are chunked by two, the `fold()` operation will process each chunk of the manifold separately. The lines with the splitters are isolated by using parameter destructuring:

    manifold.fold(0) { totalSplits, (splitters, _) ->
        splitters.mapIndexed { i, ch ->
            ...
        }.sum() + totalSplits
    }

The value of the above expression is the total number of times the beam is split in the manifold.

## Part 2

We need to find how many distinct timelines the beams can take to exit the manifold. A timeline is essentially a path a beam can take through the manifold. This is where the manifold differs from the quincunx in that the beams might fall straight through several levels instead of changing direction at every level. It all depends on the positions of the splitters. Essentially, the manifold is like a quincunx with missing pegs.

## Reflections

It took me a while to figure out Part 2. At first, I tried using a directed acyclic graph (DAG) to represent the manifold and then finding the shortest paths between the source beam and the end of the manifold. I used a depth-first search (DFS) and memoization, which worked for the example input. With the real puzzle input, however, the program quickly ran out of memory. 

My biggest mistake was jumping to conclusions too quickly. When I read "paths" I immediately thought of graphs, nodes, edges, and graph traversal algorithms. At that point, I had already fallen into the rabbit hole and it took a couple of days for me to realize this line of thinking was a dead end.

In retrospect, I should have looked at the example input more closely and gotten a clear understanding of how the number of paths increased when a beam hit a splitter. When I finally sat down to do that on Day 10, I was already three days behind in the competition.

Here's how I drew it out:

```text
The Manifold     Beams            Levels and # of beams

.......S.......  .......S.......  L0 = 1
...............  .......1.......
.......^.......  ......1^1......  L1 = 1,1 = 2
...............  ......|.|......
......^.^......  .....1^2^1.....  L2 = 1,2,1 = 4
...............  .....|.|.|.....
.....^.^.^.....  ....1^3^3^1....  L3 = 1,3,3,1 = 8
...............  ....|.|.|.|....
....^.^...^....  ...1^4^3|1^1...  L4 = 1,4,3,3,1,1 = 13
...............  ...|.|.|||.|...
...^.^...^.^...  ..1^5^4|4^2^1..  L5 = 1,5,4,3,4,2,1 = 20
...............  ..|.|.|||.|.|..
..^...^.....^..  .1^1|4^7|.|1^1.  L6 = 1,1,5,4,7,4,2,1,1 = 26
...............  .|.|||.||.||.|.
.^.^.^.^.^...^.  1^2^A^B^B^||1^1  L7 = 1,2,10,11,11,2,1,1,1 = 40
...............  |.|.|.|.|.|||.|
                 1 2 A B B 211 1
```
Levels 2 and 3 are where I had my key insight, with the other levels providing confirmation of what I discovered. 

On level 2, the two beams coming in are split, resulting in four beams. With each of the splits, one beam moves to the outside while the other moves inside the manifold. 

The beams on the outside are still part of a single path. However, the beams going inside pass through the same space and essentially combine into a single beam. We can think of this as multiple paths through the same space.

The same logic applies to level 3: The inside paths are the combination of beams from upper levels that are moving through the same space. The outside paths are still part of only one possible path. 

It occurred to me that this looked a lot like Pascal's triangle:
```text
     1 
    1 1
   1 2 1
  1 3 3 1
 1 4 6 4 1
```

The difference being that a beam could fall straight through several levels before it is combined with other beams.

The key insight was that a beam combines with other beams falling through the same space in the manifold. The splitting and combining of beams is what dictates how many additional paths there are through a particular horizontal offset at any given level in the manifold.

This is why Level 4 is different from Pascal's triangle because there is a space where a third splitter should be if we were to get the same numbers as Pascal's triangle. 
