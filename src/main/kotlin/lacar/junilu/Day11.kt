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
        hasNoIllegalCharacters(this) &&
        hasValidTrio(this) &&
        hasPairOfDoubleLetters(this)

    private fun String.increment() =
        foldRight(StringBuilder()) { ch, sb ->
            sb.append(ch.nextIncrement(sb.isEmpty() || sb.last() == 'A'))
        }.reverse().toString().lowercase()

    private fun Char.nextIncrement(nextOrWrap: Boolean): Char =
        if (nextOrWrap) { if (this != 'z') inc() else 'A' } else { this }

    internal fun isValid(password: String) = password.isValid()
    internal fun incr(s: String) = s.increment()

    companion object {
        fun hasNoIllegalCharacters(str: String) = str.none { "ilo".contains(it) }

        private val validTrios = "abcdefghijklmnopqrstuvwxyz".windowed(3).filter { hasNoIllegalCharacters(it) }
        fun hasValidTrio(password: String) = validTrios.any { trio -> password.contains(trio) }

        private val doubleLetters = Regex("([a-z])\\1")
        fun hasPairOfDoubleLetters(password: String) = doubleLetters
            .findAll(password, 0)
            .map { matches -> matches.groupValues.first() }
            .distinct().count() >= 2

        // For testing
    }
}