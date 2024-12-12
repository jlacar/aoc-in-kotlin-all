package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegionTest {

    companion object {
        @JvmStatic
        private val smallFiveGrid = Grid.parse(
            """
            AAAA
            BBCD
            BBCC
            EEEC        
            """.trimIndent().lines()
        )
    }

    @Test
    fun `countRegions gives correct number of regions`() {
        val regions = smallFiveGrid.getRegions()
//        assertEquals(5, Region.countRegions(smallFiveRegions))
    }
}
