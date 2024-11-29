package lacar.junilu

/**
 * AoC 2015 - Day 11: Corporate Policy
 *
 * https://adventofcode.com/2015/day/11
 */
class Day11() {
    fun nextPassword(currentPassword: String) =
        generateSequence(currentPassword.increment()) { it.increment() }.first { it.isValid() }

    private fun String.isValid() =
        hasNoIllegalCharacters() && hasValidTrio() && hasPairOfDoubleLetters()

    private fun String.increment() =
        foldRight(StringBuilder()) { ch, sb ->
            sb.append(ch.nextIncrement(emptyOrWrappedToA(sb)))
        }.reverse().toString().lowercase()

    private fun emptyOrWrappedToA(sb: StringBuilder) = sb.isEmpty() || sb.last() == 'A'

    private fun Char.nextIncrement(wrapOrIncrement: Boolean): Char =
        if (wrapOrIncrement) { if (this == 'z') 'A' else inc() } else { this }

    private fun String.hasNoIllegalCharacters() = none { "ilo".contains(it) }

    private val validTrios = "abcdefghijklmnopqrstuvwxyz".windowed(3).filter { it.hasNoIllegalCharacters() }
    private fun String.hasValidTrio() = validTrios.any { trio -> contains(trio) }

    private fun String.hasPairOfDoubleLetters() = Regex("([a-z])\\1")
        .findAll(this, 0)
        .map { matches -> matches.groupValues.first() }
        .distinct().count() >= 2

    // Internal, for testing
    internal fun isValid(password: String) = password.isValid()
    internal fun incr(s: String) = s.increment()
}