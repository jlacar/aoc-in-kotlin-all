# Advent of Code 2015 - In Kotlin

I'm solving these in 2024 to get ready for this year's event. I had the most difficulty with day 22.

## Solutions

Here are my notes on each of the puzzles and their solutions:

* &#11088;&#11088; [Day 1](https://adventofcode.com/2015/day/1) - [(code)](day01/Day01.kt) - [(notes)](day01/README.md)
* &#11088;&#11088; [Day 2](https://adventofcode.com/2015/day/2) - (code) - (notes)
* &#11088;&#11088; [Day 3](https://adventofcode.com/2015/day/3) - (code) - (notes)
* &#11088;&#11088; [Day 4](https://adventofcode.com/2015/day/4) - (code) - (notes)
* &#11088;&#11088; [Day 5](https://adventofcode.com/2015/day/5) - (code) - (notes)
* &#11088;&#11088; [Day 6](https://adventofcode.com/2015/day/6) - (code) - (notes)
* &#11088;&#11088; [Day 7](https://adventofcode.com/2015/day/7) - (code) - (notes)
* &#11088;&#11088; [Day 8](https://adventofcode.com/2015/day/8) - (code) - (notes)
* &#11088;&#11088; [Day 9](https://adventofcode.com/2015/day/9) - (code) - (notes)
* &#11088;&#11088; [Day 10](https://adventofcode.com/2015/day/10) - (code) - (notes)
* &#11088;&#11088; [Day 11](https://adventofcode.com/2015/day/11) - (code) - (notes)
* &#11088;&#11088; [Day 12](https://adventofcode.com/2015/day/12) - (code) - (notes)
* &#11088;&#11088; [Day 13](https://adventofcode.com/2015/day/13) - (code) - (notes)
* &#11088;&#11088; [Day 14](https://adventofcode.com/2015/day/14) - (code) - (notes)
* &#11088;&#11088; [Day 15](https://adventofcode.com/2015/day/15) - (code) - (notes)
* &#11088;&#11088; [Day 16](https://adventofcode.com/2015/day/16) - (code) - (notes)
* &#11088;&#11088; [Day 17](https://adventofcode.com/2015/day/17) - (code) - (notes)
* &#11088;&#11088; [Day 18](https://adventofcode.com/2015/day/18) - (code) - (notes)
* &#11088;&#11088; [Day 19](https://adventofcode.com/2015/day/19) - (code) - (notes)
* &#11088;&#11088; [Day 20](https://adventofcode.com/2015/day/20) - (code) - (notes)
* &#11088;&#11088; [Day 21](https://adventofcode.com/2015/day/21) - (code) - (notes)
* &#11088;&#11088; [Day 22](https://adventofcode.com/2015/day/22) - (code) - (notes)
* &#11088;&#11088; [Day 23](https://adventofcode.com/2015/day/23) - (code) - (notes)
* &#11035;&#11035; [Day 24](https://adventofcode.com/2015/day/24) - (code) - (notes)
* &#11035;&#11035; [Day 25](https://adventofcode.com/2015/day/25) - (code) - (notes)

## Utilities 

There are some basic operations that come in handy in solving these problems. One such operation is calculating permutations. I looked around for some implementations and I found [one that was workable](https://inventwithpython.com/recursion/chapter6.html), although it was written in Python. No biggie, converting Python code to Kotlin is fairly straightforward. 

This is the Python code, without the informational print statements:

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

This was easily translated to Kotlin but there were a few changes I made in the process. I kept with the general approach but added one guard clause for an empty list, which I'd expect to return an empty list. The Python implementation is done in an imperative style. I translated this to a more functional style.

The first thing I did was to add a couple of extension properties, `head` and `tail` to `List<T>`. I got these off of a StackOverflow thread I happened upon while looking for permutations solutions in Kotlin, of which there were surprisingly few.

    val <T> List<T>.head: T
        get() = first()
    
    val <T> List<T>.tail: List<T>
        get() = drop(1)

I also made `permutations()` an function of `List<T>`:

    fun <T> List<T>.permutations(): List<List<T>> { ... }

Then I added a check for the "zero" case where the list was empty. I'd expect `permutations()` to return an empty list here. I kept the "one" base case, of course, where a list with only one element returns the list itself as its only permutation.

For the recursive part of the logic, the `head` and `tail` extension properties really helped simplify and clarify the Kotlin logic. 

Using `fold()` operations instead of for-loops allowed me streamline the Kotlin code. Specifically, I eliminated or inlined the temporary variables. The `permutations` variable from the Python code was now the outer `fold()` operation's accumulator variable, initialized in call to `fold()` itself. The `tailPermutations` variable became an inline call `tail.permutations()` which because the receive of the outer `fold()` operation.

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