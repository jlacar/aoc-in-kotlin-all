# Day 3: Perfectly Spherical Houses in a Vacuum

This is mostly a parsing and interpreting problem. It also involves finding distinct instances of things in a collection.

Details can be found on the [puzzle page](https://adventofcode.com/2015/day/3).

# Part 1

In this part, you have to find how many houses received at least one present.

The main solution was this:

    fun part1() = housesVisited(directions).distinct().count()

There wasn't much I could do to make the high-level code tell a clearer story than this, or so I thought. 

After a (long) while, I realized that the `Solution` interface was not used or even needed so I started removing it from all the solution classes, renaming `part1()` and `part2()` functions with more intention-revealing names. This gave me

    fun housesSantaVisited() = housesVisited(directions).distinct().count()

I think this is the end of the refactoring journey for this code.

# Part 2

In this part, the directions are divided between Santa and RoboSanta. So each one will follow every other direction. That is, Santa will follow even-number indexed directions (including 0) and RoboSanta will use odd-number indexed directions.

The initial solution was this:

     fun part2() =
        (housesVisited(directions.filterIndexed { index, _ -> index % 2 == 0 }) +
         housesVisited(directions.filterIndexed { index, _ -> index % 2 == 1 }))
        .distinct()
        .count()

Giving it a more intention-revealing name, I got this:

     fun housesSantaOrRobotSantaVisited() =
        (housesVisited(directions.filterIndexed { index, _ -> index % 2 == 0 }) +
         housesVisited(directions.filterIndexed { index, _ -> index % 2 == 1 }))
        .distinct()
        .count()

# Refactoring notes

"Make the code tell a story" has become my main guideline for refactoring. When I came back to the code after a couple of weeks and renamed `part1()` to `housesSantaOrRobotSantaVisited()`, it became clear that there was dissonance in the story the code was telling. The lambda to `filterIndexed` was noisier than the rest of the code because it was at a lower level of abstraction.

To make it clearer, I had to think about how to tell the story of the `{ index, _ -> index % 2 == 0 }` lambda expression. What does this represent? I came up with this:

    private val hasEvenIndex = { index: Int, _ -> index % 2 == 0 }
    private val hasOddIndex = { index: Int, _ -> index % 2 == 1 }

    private val bySanta = directions.filterIndexed(hasEvenIndex)
    private val byRobotSanta = directions.filterIndexed(hasOddIndex)

Reading the code again, I renamed a few more times until I settled on `evenIndicesOnly` and `oddIndicesOnly`.

# Solution Core

The core of the solution is in the processing of the direction. I used a `runningFold()` operation initialized with the origin location. The `when` expression will translate the direction to the corresponding line that makes a copy of the current location with either `first`, representing the row, or `second`, representing the column, either incremented or decremented.


