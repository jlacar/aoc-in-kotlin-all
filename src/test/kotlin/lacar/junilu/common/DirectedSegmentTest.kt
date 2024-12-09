package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import lacar.junilu.common.Point.Companion.ORIGIN as ORIGIN

class DirectedSegmentTest {
    @Test
    fun `extend positive slope new end`() {
        assertEquals(Point(2, 2), DirectedSegment(Point(0, 0), Point(1, 1)).extend().end)
        assertEquals(Point(-2, -2), DirectedSegment(Point(0, 0), Point(-1, -1)).extend().end)
    }

    @Test
    fun `extend negative slope new end`() {
        assertEquals(Point(2, -2), DirectedSegment(Point(0, 0), Point(1, -1)).extend().end)
        assertEquals(Point(-2, 2), DirectedSegment(Point(0, 0), Point(-1, 1)).extend().end)
    }

    @Test
    fun `extends by current segment length`() {
        assertEquals(Point(4, 8), DirectedSegment(ORIGIN, Point(2, 4)).extend().end)
        assertEquals(Point(-4, -8), DirectedSegment(ORIGIN, Point(-2, -4)).extend().end)
    }
}