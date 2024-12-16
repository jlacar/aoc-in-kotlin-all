package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GridTest {
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
    }

    @Test
    fun `displayString gives lines joined with newline`() {
        val originalString = """
            12345..678
            9..123..45
            6.7...8.9.
            """.trimIndent()

        assertEquals(originalString, Grid.parse(originalString.lines()).displayString())
    }

    @Test
    fun `parse originInFirst is false by default and uses bottom line as origin`() {
        val originAtBottom = Grid.parse("""
            3333
            2222
            1111
            0000
            """.trimIndent().lines()
        )
        assertEquals("0000", originAtBottom.locations[0].map { it.symbol }.joinToString(""))
        assertEquals("3333", originAtBottom.locations.last().map { it.symbol }.joinToString(""))
    }

    @Test
    fun `parse originInFirst uses top line as origin when set to true`() {
        val originInFirst = Grid.parse("""
            0000
            1111
            2222
            3333
            """.trimIndent().lines(),
            originInFirst = true
        )
        assertEquals("0000", originInFirst.locations[0].map { it.symbol }.joinToString(""))
        assertEquals("3333", originInFirst.locations.last().map { it.symbol }.joinToString(""))
    }

    @Test
    fun `getDistinctSymbols returns distinct symbols`() {
        assertEquals(5, smallFiveGrid.getDistinctSymbols().size)
    }
}