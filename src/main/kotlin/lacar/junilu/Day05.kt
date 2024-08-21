package lacar.junilu

import kotlin.math.abs

/**
 * AoC 2015 - Day 5: Doesn't He Have Intern-Elves For This?
 *
 * https://adventofcode.com/2015/day/5
 */
class Day05(private val strings: List<String>) {

    // --- PART 1 ---

    fun countOfNiceStrings() = strings.count { isNice(it) }

    private fun isNice(s: String) =
        s.hasEnoughVowels() && s.hasDoubleLetters() && s.hasNoDisallowedSubstrings()

    private fun String.hasNoDisallowedSubstrings() =
        windowed(2).none { listOf("ab", "cd", "pq", "xy").contains(it) }

    private fun String.hasDoubleLetters() =
        windowed(2).any { it.first() == it.last() }

    private fun String.hasEnoughVowels(): Boolean =
        filter { "aeiou".contains(it) }.length >= 3

    // --- PART 2 ---

    fun countOfNicerStrings() = strings.count { isNicer(it) }

    private fun isNicer(s: String) = s.hasNonOverlappingPair() && s.hasThreeLetterPalindrome()

    private fun String.hasThreeLetterPalindrome() =
        windowed(3).any { it.first() == it.last() }

    private fun String.hasNonOverlappingPair(): Boolean {
        val pairs = windowed(2)
        return pairs.any { abs(pairs.indexOf(it) - pairs.lastIndexOf(it)) > 1 }
    }
}