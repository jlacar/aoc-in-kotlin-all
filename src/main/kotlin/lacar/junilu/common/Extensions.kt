package lacar.junilu.common

fun Int.pow(exp: Int): Long = (1..exp).fold(1L) { acc, _ -> acc * this }
fun Int.squared(): Long = this.toLong() * this
