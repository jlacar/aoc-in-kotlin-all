package lacar.junilu.common

// region Parse as numbers

private val ALL_NUMBERS = "[+-]?\\d+".toRegex()

fun String.allNumbers(): Sequence<MatchResult> = ALL_NUMBERS.findAll(this)

fun String.findInts(): List<Int> = allNumbers().map { it.value.toInt() }.toList()

fun String.findLongs(): List<Long> = allNumbers().map { it.value.toLong() }.toList()

// endregion

// region Parse Iterables and their elements

fun <T> Iterable<T>.dropEnds() = drop(1).dropLast(1)

fun String.dropEnds() = drop(1).dropLast(1)

// endregion