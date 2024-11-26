package lacar.junilu

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Returns the puzzle input read from the specified path
 * as a list of String, one for each line in the file.
 *
 * Assumes the file has a ".txt" extension and is
 * in `src/main/resources` or a subdirectory. If the
 * file is in a subdirectory, the specified path is
 * assumed to be relative to `src/main/resources`.
 *
 * @since AoC 2015
 */
fun readPuzzleInput(relativePath: String): List<String> {
    val inputFilePath = when (relativePath.first()) {
        '/' -> relativePath.drop(1)
        else -> relativePath
    }
    return Path("src/main/resources/$inputFilePath.txt").readLines()
}

/**
 * Converts a string representing a key-value pair into a Pair<String, R>.
 *
 * Use this as a convenient way to create map entries whose values need to be converted
 *
 * Example:
 * <code>
 *      keyValuePair("a: 2", ": ", String::toInt)   // Pair("a", 2)
 * </code>
 */
fun <R> keyValuePair(item: String, delimiters: String, transform: (String) -> R): Pair<String, R> {
    val (name, value) = item.split(delimiters)
    return name to transform(value)
}

/**
 * Converts a string to a map entry.
 *
 * Use this to
 */
fun <R> toPropsMap(
    list: String,
    itemDelimiter: String,
    keyValueDelimiter: String,
    transform: (String) -> R
): Map<String, R> =
    list.split(itemDelimiter)
        .associate { keyValuePair(it, keyValueDelimiter, transform) }
