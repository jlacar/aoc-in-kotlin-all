package lacar.junilu

/**
 * AoC 2015 - Day 5: Doesn't He Have Intern-Elves For This?
 *
 * https://adventofcode.com/2015/day/5
 */
class Day05(private val strings: List<String>) {

    // --- PART 1 ---

    fun howManyNiceStringsPart1() = strings.count { it.isNice() }

    private fun String.isNice() =
        hasAtLeastThreeVowels() && hasDoubleLetters() && hasNoDisallowedSubstrings()

    private fun String.hasNoDisallowedSubstrings() =
        listOf("ab", "cd", "pq", "xy").none { contains(it) }

    private fun String.hasDoubleLetters() =
        windowed(2).any { it.first() == it.last() }

    private fun String.hasAtLeastThreeVowels(): Boolean =
        filter { "aeiou".contains(it) }.length >= 3

    // --- PART 2 ---

    fun howManyNiceStringsPart2() = strings.count { it.isNicer() }

    private fun String.isNicer() =
        hasNonOverlappingPair() && hasThreeLetterPalindrome()

    private fun String.hasThreeLetterPalindrome() =
        windowed(3).any { it.first() == it.last() }

    private fun String.hasNonOverlappingPair() =
        windowed(2).any { (lastIndexOf(it) - indexOf(it)) > 1 }
}