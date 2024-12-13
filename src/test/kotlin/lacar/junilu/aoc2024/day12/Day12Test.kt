package lacar.junilu.aoc2024.day12

import lacar.junilu.common.RegionTest
import lacar.junilu.common.RegionTest.Companion.fiveRegionTwoSymbolGrid
import lacar.junilu.common.RegionTest.Companion.large11RegionGrid
import lacar.junilu.common.RegionTest.Companion.smallFiveGrid
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun `Example - Part 1`() {
        assertEquals(140, Day12(smallFiveGrid).part1())
        assertEquals(772, Day12(fiveRegionTwoSymbolGrid).part1())
        assertEquals(1930, Day12(large11RegionGrid).part1())
    }

    @Test
    fun `Solution - Part 1`() {
        assertEquals(1370100, Day12.using(readPuzzleInput("aoc2024/day12-gh")).part1())
        assertEquals(1363484, Day12.using(readPuzzleInput("aoc2024/day12-gm")).part1())
    }
}