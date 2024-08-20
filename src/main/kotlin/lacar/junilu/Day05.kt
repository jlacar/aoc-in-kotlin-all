package lacar.junilu

import kotlin.math.abs

/**
 * AoC 2015 - Day 5: Doesn't He Have Intern-Elves For This?
 *
 * https://adventofcode.com/2015/day/5
 */
class Day05(private val strings: List<String>) {

    // --- PART 1 ---

    fun part1() = strings.count { isNice(it) }

    private fun isNice(s: String) =
        hasEnoughVowels(s) && hasDoubleLetters(s) && hasNoDisallowedLetters(s)

    private fun hasNoDisallowedLetters(s: String) =
        s.windowed(2).none { listOf("ab", "cd", "pq", "xy").contains(it) }

    private fun hasDoubleLetters(s: String) =
        s.windowed(2).any { it.first() == it.last() }

    private fun hasEnoughVowels(s: String): Boolean =
        s.filter { "aeiou".contains(it) }.length >= 3

    // --- PART 2 ---

    fun part2() = strings.count { isNicer(it) }

    private fun isNicer(s: String) = hasNonOverlappingPair(s) && hasThreeLetterPalindrome(s)

    private fun hasThreeLetterPalindrome(s: String) =
        s.windowed(3).any { it.first() == it.last() }

    private fun hasNonOverlappingPair(s: String): Boolean {
        val pairs = s.windowed(2)
        return pairs.any { abs(pairs.indexOf(it) - pairs.lastIndexOf(it)) > 1 }
    }
}