package lacar.junilu.common

// region ===== Parsing input

private val ALL_NUMBERS = "[+-]?\\d+".toRegex()

fun String.allNumbers(): Sequence<MatchResult> = ALL_NUMBERS.findAll(this)

fun String.findInts(): List<Int> = allNumbers().map { it.value.toInt() }.toList()

fun String.findLongs(): List<Long> = allNumbers().map { it.value.toLong() }.toList()
