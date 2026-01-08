package lacar.junilu.common

fun Int.pow(exp: Int): Long = (1..exp).fold(1L) { acc, _ -> acc * this }
fun Int.squared(): Long = this.toLong() * this

fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 != 0