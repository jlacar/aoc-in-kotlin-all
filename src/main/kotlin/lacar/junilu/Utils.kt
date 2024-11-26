package lacar.junilu

import java.math.BigInteger
import java.security.MessageDigest

// region ================= General Purpose Output =====================

fun Any?.println() = println(this)

fun Any?.print() = print(this)

// region ================= List Extensions ============================

//**
val <T> List<T>.head: T
    get() = first()

val <T> List<T>.tail: List<T>
    get() = drop(1)

// region ================= Statistical Functions ======================

/**
 * Calculate permutations of the specified list.
 *
 * Adapted from Python: https://inventwithpython.com/recursion/chapter6.html and feedback
 * from https://www.reddit.com/r/Kotlin/comments/1edxill/seeing_some_strange_behavior_with_apply_scope/
 *
 * @return a List of all permutations of the elements of this list.
 */
fun <T> List<T>.permutations(): List<List<T>> {
    if (isEmpty()) return emptyList()
    if (size == 1) return listOf(this)

    return tail.permutations().fold(mutableListOf()) { acc, perm ->
        (0..perm.size).mapTo(acc) { i ->
            perm.subList(0, i) + this@permutations.head + perm.subList(i, perm.size)
        }
    }
}

/**
 * Generate the combinations of this iterable, taken k-elements at a time,
 * as a sequence.
 *
 * @param k the number of elements in each combination
 */
fun <T> Iterable<T>.combinations(k: Int): Sequence<List<T>> =
    sequence {
        val pool = this@combinations as? List<T> ?: toList()
        val n = pool.size
        if (k > n) return@sequence
        val indices = IntArray(k) { it }
        while (true) {
            yield(indices.map { pool[it] })
            var i = k
            do {
                if (i-- == 0) return@sequence
            } while (indices[i] == i + n - k)
            indices[i]++
            for (j in i + 1 until k) indices[j] = indices[j - 1] + 1
        }
    }

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
