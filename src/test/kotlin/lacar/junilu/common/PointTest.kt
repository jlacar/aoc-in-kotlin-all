package lacar.junilu.common

import lacar.junilu.common.Direction.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `slide moves Point in direction indicated`() {
        val start = Point(row = 1, col = 1)
        val ends = listOf(
            NORTH to start.copy(row = start.row + 1),
            SOUTH to start.copy(row = start.row - 1),
            EAST to start.copy(col = start.col + 1),
            WEST to start.copy(col = start.col - 1),
        )
        
        ends.forEach { (dir, expectedEnd) ->
            assertEquals(expectedEnd, start.shift(dir))
        }
    }
}