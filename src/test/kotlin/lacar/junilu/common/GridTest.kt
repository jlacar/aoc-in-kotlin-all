package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GridTest {
    @Test
    fun `displayString gives lines joined with newline`() {
        val originalString = """
            12345..678
            9..123..45
            6.7...8.9.
            """.trimIndent()

        assertEquals(originalString, Grid.parse(originalString.lines()).displayString())
    }
}