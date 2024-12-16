package lacar.junilu

import kotlin.io.path.Path
import kotlin.io.path.readLines
import lacar.junilu.RelativePathNormalizer.normalized

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
    val inputFilePath = relativePath.normalized()
    return Path("src/main/resources/$inputFilePath").readLines()
}

object RelativePathNormalizer {
    fun String.normalized() = dropLeadingSlash().addExtension()

    private fun String.dropLeadingSlash() =
        when (startsWith("/")) {
            false -> this
            true -> this.drop(1)
        }

    private fun String.addExtension() =
        when (endsWith(".txt")) {
            false -> plus(".txt")
            true -> this
        }
}

/**
 * Converts a string representing a key-value pair into
 * a <code>Pair<String, R></code>.
 *
 * The transform function is applied to the value.
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
inline fun <R> keyValuePair(input: String, delimiters: String, transform: (String) -> R): Pair<String, R> {
    val (name, value) = input.split(delimiters)
    return name to transform(value)
}

/**
 * Converts a string with multiple delimited key-value pairs
 * into a <code>Map<String, R></code>, where R is the type
 * returned by the specified transform function.
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
inline fun <R> toPropsMap(
    input: String,
    itemDelimiter: String,
    keyValueDelimiter: String,
    transform: (String) -> R
): Map<String, R> =
    input.split(itemDelimiter)
        .associate { keyValuePair(it, keyValueDelimiter, transform) }
