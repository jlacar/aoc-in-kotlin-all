package lacar.junilu.common

import kotlin.math.sqrt

/**
 * Three-dimensional geometry
 */

private fun Int.pow(exp: Int): Long = (1..exp).fold(1L) { acc, _ -> acc * this }
private fun Int.squared(): Long = this.toLong() * this

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int
) {
    fun distanceTo(other: Point3D) =
        sqrt(((x - other.x).squared() + (y - other.y).squared() + (z - other.z).squared()).toDouble())

}