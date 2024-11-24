package lacar.junilu.aoc2015.day23

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegistersTest {
    @Test
    fun `all registers are initialized to 0`() {
        val state = initialLockState()
        assertAll(
            { assertEquals(0, state.a) },
            { assertEquals(0, state.b) },
            { assertEquals(0, state.programCounter) },
        )
    }
}