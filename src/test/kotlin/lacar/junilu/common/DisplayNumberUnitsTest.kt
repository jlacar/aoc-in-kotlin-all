package lacar.junilu.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("The units() extension functions")
class DisplayNumberUnitsTest {

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock =
        """
        singular,    plural,    display1,   display2
        inch,        inches,    1 inch,     2 inches
        elk,         elk,       1 elk,      2 elk
        mouse,       mice,      1 mouse,    2 mice
        moose,       moose,     1 moose,    2 moose"""
    )
    fun `display correct singular and plural forms`(singular: String, plural: String, display1: String, display2: String) {
        assertEquals(display1, 1.units(singular, plural))
        assertEquals(display2, 2.units(singular, plural))
    }

    @Test
    fun `by default adds s to singular for plural form`() {
        assertEquals("1 sec", 1.units("sec"))
        assertEquals("2 secs", 2.units("sec"))
    }

    @Test
    fun `recognize plus as shorthand to append suffix for plural form`() {
        assertEquals("1 inch", 1.units("inch", "+es"))
        assertEquals("2 inches", 2.units("inch", "+es"))
    }

    @Test
    fun `recognize minus as shorthand to append suffix after dropping last letter of singular form`() {
        assertEquals("1 berry", 1.units("berry", "-ies"))
        assertEquals("2 berries", 2.units("berry", "-ies"))
    }

    @Test
    fun `recognize asterisk as shorthand to use same unit for singular and plural forms`() {
        assertEquals("1 elk", 1.units("elk", "*"))
        assertEquals("2 elk", 2.units("elk", "*"))
    }
}