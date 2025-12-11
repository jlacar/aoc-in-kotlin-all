# AoC 2025: Day 7 - Laboratories

Puzzle Page: https://adventofcode.com/2025/day/7 

Code: [solution](Day07.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day07/Day07Test.kt)

## Overview

This problem involves a tachyon manifold, a machine similar to a [quincunx](https://en.wikipedia.org/wiki/Quincunx), also known as a Galton Board. Instead of marbles, we are dealing with tachyon beams that pass through the tachyon manifold. Instead of pegs, we have splitters that split any beam that hits it in two, one going to the left and the other to the right of the splitter.

## The input data

The input is a character representation of the board drawn as a grid. A space is indicated by a "." while a "^" denotes a splitter. Beams are directed vertically downwards through the manifold. The single source beam is denoted by an "S" centered in the first row at the top of the board. The end of the manifold is the bottom row of the grid.

## Part 1

We need to find how many times beams have been split by the time they exit the manifold.

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
