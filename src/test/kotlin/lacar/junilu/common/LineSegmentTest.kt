package lacar.junilu.common

import lacar.junilu.common.Point.Companion.ORIGIN
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LineSegmentTest {
    @Test
    fun `extend moves ends by current length`() {
        val newEndPoints = LineSegment(ORIGIN, 2, 4).extend().endPoints.toList()

        assertAll (
            { assertTrue( Point(4, 8) in newEndPoints, "(4, 8) should be an end point" ) },
            { assertTrue( Point( -2, -4) in newEndPoints, "(-2, -4) should be an end point" ) },
        )
    }
}