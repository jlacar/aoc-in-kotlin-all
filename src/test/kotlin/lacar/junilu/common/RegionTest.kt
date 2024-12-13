package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegionTest {

    companion object {
        @JvmStatic
        val smallFiveGrid = Grid.parse(
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent().lines()
        )

        @JvmStatic
        val fiveRegionTwoSymbolGrid = Grid.parse(
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent().lines())

        @JvmStatic
        val large11RegionGrid = Grid.parse(
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent().lines())
    }

    @Test
    fun `getRegions gives correct number of regions`() {
        assertEquals(5, smallFiveGrid.getRegions().size)
        assertEquals(5, fiveRegionTwoSymbolGrid.getRegions().size)
        assertEquals(11, large11RegionGrid.getRegions().size)
    }

//    @Test
//    fun `regions are contiguous groups with same symbol`() {
//
//    }
}
