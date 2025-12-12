package lacar.junilu.common

import kotlin.math.abs
import kotlin.math.sqrt

data class Point2D(val x: Int, val y: Int) {
    operator fun plus(other: Point2D) = Point2D(x + other.x, y + other.y)

    operator fun minus(other: Point2D) = Point2D(x - other.x, y - other.y)

    fun manhattan() = abs(x) + abs(y)

    fun distanceTo(other: Point2D) = sqrt(((x - other.x).squared() + (y - other.y).squared()).toDouble())
}

fun Pair<Point2D, Point2D>.distance() = first.distanceTo(second)

fun Pair<Point2D, Point2D>.area() = abs(first.x - second.x) * abs(first.y - second.y)

/**
 * Calculates the area of a thin rectangle defined by the two opposite corners.
 *
 * A thin rectangle has a thickness of at least 1 unit along any axis.
 * For example, a rectangle with corners (1, 1) and (1, 3) has an area of 4.
 */
fun Pair<Point2D, Point2D>.thinArea() =
    (abs(first.x - second.x) + 1).toLong() *
    (abs(first.y - second.y) + 1).toLong()