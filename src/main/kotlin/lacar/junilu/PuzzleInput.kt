package lacar.junilu

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
 * Converts a string representing a key-value pair into
 * a <code>Pair<String, R></code>.
 *
 * Use this as a convenient way to create map entries whose
 * values need to be converted to something other than a String.
 *
 * Example:
 * <code>
 *      keyValuePair("a: 2", ": ", String::toInt)   // Pair("a", 2)
 * </code>
 *
 * @param input       a String containing a key and its value
 * @param delimiters  what separates the key from its value
 * @param transform   a function applied to the value
 *
 * @since AoC 2015
 */
fun <R> keyValuePair(input: String, delimiters: String, transform: (String) -> R): Pair<String, R> {
    val (name, value) = input.split(delimiters)
    return name to transform(value)
}

/**
 * Converts a string to a <code>Map<String, Pair<String, R>></code>,
 * where R is the type returned by the specified transform function.
 *
 * Use this to convert input that has a key and delimited values.
 *
 * Example:
 * <code>
 *    val propsMap = toPropsMap(
 *            input = "capacity 2, durability 0, flavor -2",
 *            itemDelimiter = ", ",
 *            keyValueDelimiter = " ") { it.toInt() }
 *
 *    propsMap == mapOf("capacity" to 2, "durability" to 0, "flavor" to -1)  // true
 * </code>
 *
 * @param input               a string that contains a list of key-value pairs
 * @param itemDelimiter       separator of each key-value pair
 * @param keyValueDelimiter   separator of key and value
 * @param transform           a function applied to each value
 *
 * @see 2015 Day 15
 */
fun <R> toPropsMap(
    input: String,
    itemDelimiter: String,
    keyValueDelimiter: String,
    transform: (String) -> R
): Map<String, R> =
    input.split(itemDelimiter)
        .associate { keyValuePair(it, keyValueDelimiter, transform) }
