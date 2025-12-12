package lacar.junilu.common

import kotlin.math.sqrt

/**
 * Three-dimensional geometry
 */

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int
) {
    fun distanceTo(other: Point3D) =
        sqrt(((x - other.x).squared() + (y - other.y).squared() + (z - other.z).squared()).toDouble())
}