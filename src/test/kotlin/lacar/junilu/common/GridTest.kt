package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GridTest {
    companion object {
        @JvmStatic
        val smallFiveSymbolGridText = """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent()

        @JvmStatic
        val originAtTopText = """
            0000
            1111
            2222
            3333
            """.trimIndent()

        @JvmStatic
        val originAtBottomText = """
            3333
            2222
            1111
            0000
            """.trimIndent()
    }

    // region ===== tests for Grid.parse()

    @Test
    fun `toGrid extension function parses String receiver as a Grid`() {
        val text = """
            AAA
            BBB
            CCC
            """.trimIndent()

        // expected and actual calls below are equivalent
        assertEquals(
            Grid.parse(text.lines()),  // this is equivalent
            text.toGrid()              // to this
        )
        assertEquals(
            Grid.parse(text.lines(), originInFirst = true),
            text.toGrid(originInFirst = true)
        )
    }

    @Test
    fun `parse uses bottom line as origin with originInFirst set to false by default`() {
        val originAtBottom = originAtBottomText.toGrid( /* use default for originInFirst */ )

        assertEquals("0000", originAtBottom.locations[0].map { it.symbol }.joinToString(""))
        assertEquals("3333", originAtBottom.locations.last().map { it.symbol }.joinToString(""))
    }

    @Test
    fun `parse uses top line as origin when originInFirst set to true`() {
        val grid = originAtTopText.toGrid(originInFirst = true)

        assertEquals("0000", grid.locations[0].map { it.symbol }.joinToString(""))
        assertEquals("3333", grid.locations.last().map { it.symbol }.joinToString(""))
    }

    // region ===== tests for Grid.displayString()

    @Test
    fun `displayString shows origin line at top when originInFirst is true`() {
        val grid = Grid.parse(originAtTopText.lines(), originInFirst = true)

        assertEquals(originAtTopText, grid.displayString())
    }

    @Test
    fun `displayString shows origin line at bottom when originInFirst is false`() {
        val gridDefault = Grid.parse(originAtBottomText.lines()) // default is false
        val gridExplicit = Grid.parse(originAtBottomText.lines(), originInFirst = false)

        assertEquals(originAtBottomText, gridDefault.displayString())
        assertEquals(gridDefault.displayString(), gridExplicit.displayString())
    }

    // region ===== tests for other Grid methods: allDistinctSymbols()

    @Test
    fun `allDistinctSymbols returns all distinct symbols in grid`() {
        val grid = Grid.parse(smallFiveSymbolGridText.lines())
        assertEquals(5, grid.allDistinctSymbols().size)
    }
}