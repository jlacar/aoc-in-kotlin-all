package lacar.junilu

/**
 * AoC 2015 - Day 10: Elves Look, Elves Say
 *
 * https://adventofcode.com/2015/day/10
 */
class Day10(val input: String = "3113322113") : Solution<Int> {
    override fun part1(): Int = lookSay(40)
    override fun part2(): Int = lookSay(50)

    private fun lookSay(n: Int) = (1..n).fold(input) { look, _ -> say(look) }.length

    private val sameConsecutiveDigit = """(.)\1*""".toRegex()

    fun say(digits: String) = sameConsecutiveDigit
        .findAll(digits, 0)
        .map { matches -> matches.groupValues.first() }
        .map { sameDigits -> "${sameDigits.length}${sameDigits.first()}" }
        .joinToString("")
}