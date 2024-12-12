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

        @JvmStatic
        private val fiveRegionTwoSymbolGrid = Grid.parse(
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent().lines()
        )
    }

    @Test
    fun `getRegions gives correct number of regions`() {
        val smallFiveRegions = smallFiveGrid.getRegions()
        assertEquals(5, smallFiveRegions.size)

        val fiveRegionTwoSymbolRegions = fiveRegionTwoSymbolGrid.getRegions()
        assertEquals(5, fiveRegionTwoSymbolRegions.size)
    }

//    @Test
//    fun `regions are contiguous groups with same symbol`() {
//
//    }
}
