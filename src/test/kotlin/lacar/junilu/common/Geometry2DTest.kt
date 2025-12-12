package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Geometry2DTest {

    @ParameterizedTest
    @CsvSource(
        // area with same X coordinate has thickness of 1
        "7,3,  2,3,   6",
        "0,0,  0, 4,  5",
        "0,0,  0,-4,  5",
        // area with same Y coordinate has thickness of 1
        "0,0,  5,0,   6",
        "0,0, -5,0,   6",
        // proper rectangles
        "0,0,  4,4,  25",
        "0,0, -4,4,  25",
        "0,0,  4,-4, 25",
        "0,0, -4,-4, 25",
        "2,5,  9,7,  24",
        "2,5, 11,1,  50",
        )
    fun `thick Area defined by a Pair of Point2D`(x1: Int, y1: Int, x2: Int, y2: Int, expectedArea: Int) {
        val pairOfOppositeCorners = Pair(Point2D(x1, y1), Point2D(x2, y2))

        assertEquals(expectedArea, pairOfOppositeCorners.thinArea())
    }
}