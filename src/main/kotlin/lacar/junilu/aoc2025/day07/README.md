# AoC 2025: Day 7 - Laboratories

Puzzle Page: https://adventofcode.com/2025/day/7 

Code: [solution](Day07.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day07/Day07Test.kt)

## Overview

This problem involves a machine that is very similar to a [quincunx](https://en.wikipedia.org/wiki/Quincunx), also known as a [Galton Board](https://en.wikipedia.org/wiki/Galton_board). The machine in the puzzle is called a tachyon manifold. Unlike a Galton board, a so-called tachyon beam passes through the manifold and it is split by irregularly positioned splitters. 

## The input data

I didn't see any need to parse the input data into a more abstract structured format. I extracted the following two pieces of information from the input data:

1. The horizontal position of the entry point as indicated by `'S'`, which I chose to call `source`.
2. The manifold as a list of strings, chunked by two lines.

```kotlin 
   private val source = lines.first().indexOf('S')
   private val manifold = lines.drop(2).chunked(2)
```

The manifold data is chunked by two lines but only one of them contains splitters. I ignore the lines that don't have splitters.

## Part 1

The first part asks us to calculate how many times a beam that enters the manifold is split before it reaches the other end. To do this, we have to follow the path of the beam from the top of the manifold and track how many times it hits a splitter. Subsequently, we have to track the split beams and add them to the total. This continues until all the beams have reached the other end of the manifold.

We can't just count the number of splitters because there's a chance that a splitter will not get hit at all. We need to track where the beams are on each level of the manifold and add the number of splitters that they hit. This means tracking the horizontal position of the beam on each level. The tracking information needs to be updated whenever the beam hits a splitter.

I used a Boolean array to track the horizontal positions of the beams. The array is sized to the width of the manifold. Initially, only the `source` element set to true. I used the `also()` scope function to initialize the source beam position.

```kotlin
   val beams = BooleanArray(width).also { it[source] == true }
```
This array will be updated to reflect the new horizontal positions of the beam as it gets split. To make the code easier to read, I defined an extension function:

```kotlin
   private fun Char.isSplitter() = this == '^'
```

Updating the horizontal positions of the beams is as simple as checking if the current position is a splitter and updating the `beams` array accordingly:

```kotlin
    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
    }
```

Since we needed to count how many splits occurred, I made the `if` expression have a value of `1` if a splitter was hit and `0` otherwise.

```kotlin
    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
        1
    } else 0
```

A `sum()` operation around this gives the total number of splits on each level.

Processing the entire manifold was done with a `fold()` operation, with `0` as the initial value for the accumulator, `totalSplits`. The `fold()` operation processes two lines of input at a time. The line that doesn't have splitters is ignored by using parameter destructuring and an unnamed parameter, `_`:

```kotlin
    manifold.fold(0) { totalSplits, (splitters, _) ->
        splitters.mapIndexed { i, ch ->
            ...
        }.sum() + totalSplits
    }
```

The above expression gives the total number of times the beam is split when it goes through the manifold.

### Alternative Solution

Alternatively, I could have used `map()` instead `fold()`.

```kotlin
    fun beamSplits(): Int {
        val beams = BooleanArray(width).also { it[source] = true }
        return manifold.map { (splitters, _) ->
            splitters.mapIndexed { i, ch ->
                if (ch.isSplitter() && beams[i]) {
                    beams[i - 1] = true
                    beams[i + 1] = true
                    beams[i] = false
                    1
                } else 0
            }.sum()
        }.sum()
    }
```

## Part 2

We need to find how many distinct timelines the beams can take to exit the manifold. A timeline is essentially a path a beam can take through the manifold. # AoC 2025: Day 7 - Laboratories

Puzzle Page: https://adventofcode.com/2025/day/7

Code: [solution](Day07.kt) | [test](../../../../../../test/kotlin/lacar/junilu/aoc2025/day07/Day07Test.kt)

## Overview

This problem involves a machine that is very similar to a [quincunx](https://en.wikipedia.org/wiki/Quincunx), also known as a [Galton Board](https://en.wikipedia.org/wiki/Galton_board). The machine in the puzzle is called a tachyon manifold. Unlike a Galton board, a so-called tachyon beam passes through the manifold and it is split by irregularly positioned splitters.

## The input data

I didn't see any need to parse the input data into a more abstract structured format. I extracted the following two pieces of information from the input data:

1. The horizontal position of the entry point as indicated by `'S'`, which I chose to call `source`.
2. The manifold as a list of strings, chunked by two lines.

```kotlin 
   private val source = lines.first().indexOf('S')
   private val manifold = lines.drop(2).chunked(2)
```

The manifold data is chunked by two lines but only one of them contains splitters. I ignore the lines that don't have splitters.

## Part 1

The first part asks us to calculate how many times a beam that enters the manifold is split before it reaches the other end. To do this, we have to follow the path of the beam from the top of the manifold and track how many times it hits a splitter. Subsequently, we have to track the split beams and add them to the total. This continues until all the beams have reached the other end of the manifold.

We can't just count the number of splitters because there's a chance that a splitter will not get hit at all. We need to track where the beams are on each level of the manifold and add the number of splitters that they hit. This means tracking the horizontal position of the beam on each level. The tracking information needs to be updated whenever the beam hits a splitter.

I used a Boolean array to track the horizontal positions of the beams. The array is sized to the width of the manifold. Initially, only the `source` element set to true. I used the `also()` scope function to initialize the source beam position.

```kotlin
   val beams = BooleanArray(width).also { it[source] == true }
```
This array will be updated to reflect the new horizontal positions of the beam as it gets split. To make the code easier to read, I defined an extension function:

```kotlin
   private fun Char.isSplitter() = this == '^'
```

Updating the horizontal positions of the beams is as simple as checking if the current position is a splitter and updating the `beams` array accordingly:

```kotlin
    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
    }
```

Since we needed to count how many splits occurred, I made the `if` expression have a value of `1` if a splitter was hit and `0` otherwise.

```kotlin
    if (ch.isSplitter() && beams[i]) {
        beams[i - 1] = true  // split left
        beams[i + 1] = true  // split right
        beams[i] = false     // current beam is no longer active
        1
    } else 0
```

A `sum()` operation around this gives the total number of splits on each level.

Processing the entire manifold was done with a `fold()` operation, with `0` as the initial value for the accumulator, `totalSplits`. The `fold()` operation processes two lines of input at a time. The line that doesn't have splitters is ignored by using parameter destructuring and an unnamed parameter, `_`:

```kotlin
    manifold.fold(0) { totalSplits, (splitters, _) ->
        splitters.mapIndexed { i, ch ->
            ...
        }.sum() + totalSplits
    }
```

The above expression gives the total number of times the beam is split when it goes through the manifold.

### Alternative Solution

Alternatively, I could have used `map()` instead `fold()`.

```kotlin
    fun beamSplits(): Int {
        val beams = BooleanArray(width).also { it[source] = true }
        return manifold.map { (splitters, _) ->
            splitters.mapIndexed { i, ch ->
                if (ch.isSplitter() && beams[i]) {
                    beams[i - 1] = true
                    beams[i + 1] = true
                    beams[i] = false
                    1
                } else 0
            }.sum()
        }.sum()
    }
```

## Part 2

We need to find how many distinct timelines the beams can take to exit the manifold. A timeline is essentially a path a beam can take through the manifold. The code is very similar to Part 1, except we use an array of Long to track the number of beams at each horizontal position. The array is initialized with a single beam at the source position. The total number of beams at the end of the manifold is the number of distinct timelines.

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

### Improving semantics with extension functions

I added a couple of extension functions to make the code more readable:

```kotlin
private fun Char.isSplitter() = this == '^'
private fun Boolean.hits(ch: Char) = this && ch.isSplitter()
```

I made these private to the Day 7 solution to keep the scope limited to this puzzle.

This allowed me to make the code more readable and conversational:

```kotlin
    splitters.mapIndexed { i, splitter ->
    if (beams[i].hits(splitter)) {
        ...
        1
    } else 0
```