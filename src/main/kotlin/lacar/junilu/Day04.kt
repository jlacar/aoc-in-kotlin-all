package lacar.junilu

import java.security.MessageDigest

/**
 * AoC 2015 - Day 4: The Ideal Stocking Stuffer
 *
 * https://adventofcode.com/2015/day/4
 */
class Day04(private val secretKey: String) {
    fun part1() = mineFor("00000")

    fun part2() = mineFor("000000")

    private fun mineFor(prefix: String): Int =
        (1..Int.MAX_VALUE).first { secretKey.md5(it).startsWith(prefix) }
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(salt: Int): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest((this + salt).toByteArray())
    return digest.toHexString()
}