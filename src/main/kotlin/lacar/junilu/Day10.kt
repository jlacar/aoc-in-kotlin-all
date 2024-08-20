package lacar.junilu

/**
 * AoC 2015 - Day 10: Elves Look, Elves Say
 *
 * https://adventofcode.com/2015/day/10
 */
class Day10() {
    fun lookSay(input: String, times: Int = 1) =
        (1..times).fold(input) { number, _ -> say(number) }

    private val sameConsecutiveDigit = """(.)\1*""".toRegex()

    fun say(digits: String) = sameConsecutiveDigit
        .findAll(digits, 0)
        .map { matches -> matches.groupValues.first() }
        .map { sameDigits -> "${sameDigits.length}${sameDigits.first()}" }
        .joinToString("")
}