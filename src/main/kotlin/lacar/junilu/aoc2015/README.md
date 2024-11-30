# Advent of Code 2015 - In Kotlin

I'm solving these in 2024 to get ready for this year's event. I had the most difficulty with day 22.

## Solutions

Here are my notes on each of the puzzles and their solutions:

* &#11088;&#11088; [Day 1](day01/.)
* &#11088;&#11088; [Day 2](day02/.)
* &#11088;&#11088; [Day 3](day03/.)
* &#11088;&#11088; [Day 4](day04/.)
* &#11088;&#11088; [Day 5](day05/.)
* &#11088;&#11088; [Day 6](day06/.)
* &#11088;&#11088; [Day 7](day07/.)
* &#11088;&#11088; [Day 8](day08/.)
* &#11088;&#11088; [Day 9](day09/.)
* &#11088;&#11088; [Day 10](day10/.)
* &#11088;&#11088; [Day 11](day11/.)
* &#11088;&#11088; [Day 12](day12/.)
* &#11088;&#11088; [Day 13](day13/.)
* &#11088;&#11088; [Day 14](day14/.)
* &#11088;&#11088; [Day 15](day15/.)
* &#11088;&#11088; [Day 16](day16/.)
* &#11088;&#11088; [Day 17](day17/.)
* &#11088;&#11088; [Day 18](day18/.)
* &#11088;&#11088; [Day 19](day19/.)
* &#11088;&#11088; [Day 20](day20/.)
* &#11088;&#11088; [Day 21](day21/.)
* &#11088;&#11088; [Day 22](day22/.)
* &#11088;&#11088; [Day 23](day23/.)
* &#11088;&#11088; [Day 24](day24/.)
* &#11088;&#11088; [Day 25](day25/.)

## Utilities 

A few of the problems required calculating permutations and combinations. I found [implementation of permutations and combinations that were workable](https://inventwithpython.com/recursion/chapter6.html) written in Python. Converting to Kotlin was fairly straightforward. 

This is the stripped-down Python code for permutations, without the original print statements:

    def getPerms(chars, indent=0):
        if len(chars) == 1:
        return [chars]
    
        # RECURSIVE CASE
        permutations = []
        head = chars[0]
        tail = chars[1:]
        tailPermutations = getPerms(tail, indent + 1)
        for tailPerm in tailPermutations:
            for i in range(len(tailPerm) + 1):
                newPerm = tailPerm[0:i] + head + tailPerm[i:]
                permutations.append(newPerm)
        return permutations

I made a few tweaks to this in the Kotlin version. I kept with the general approach but added a guard clause for an empty list, which I'd expect to return an empty list. The Python implementation was written in an imperative style so I translated to a more functional style for Kotlin.

I added a couple of List extension properties, `head` and `tail`. I got these from StackOverflow.

    val <T> List<T>.head: T
        get() = first()
    
    val <T> List<T>.tail: List<T>
        get() = drop(1)

I also made `permutations()` an extionsion function of `List<T>`:

    fun <T> List<T>.permutations(): List<List<T>> { ... }

I added a check for the "zero" case where the list was empty. I'd expect `permutations()` to return an empty list in this case. I kept the "one" base case, where a list with only one element returns the list itself as its only permutation.

For the recursive part of the logic, the `head` and `tail` extension properties really helped simplify and clarify the Kotlin logic. 

Using `fold()` operations instead of for-loops allowed me streamline the Kotlin code. Specifically, I eliminated or inlined a few temporary variables. The `permutations` variable from the Python code was now the outer `fold()` operation's accumulator variable, initialized in call to `fold()` itself. The `tailPermutations` variable became an inline call `tail.permutations()` which because the receive of the outer `fold()` operation.

The inner for-loop from Python also became a `fold()` operation. With the `+` operator overloaded for `List<T>`, adding the elements of each permutation together was easy. The only thing that looks a little weird is the 

    fun <T> List<T>.permutations(): List<List<T>> {
        if (isEmpty()) return emptyList()
        if (size == 1) return listOf(this)

        return tail.permutations()
            .fold(mutableListOf()) { allPerms, perm ->
                (0..perm.size).fold(allPerms) { acc, i ->
                    acc.add(perm.subList(0, i) + head + perm.subList(i, perm.size))
                    acc
                }
            }
    }