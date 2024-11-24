package lacar.junilu.aoc2015.day23

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LockStateTest {
    @Test
    fun `all registers are initialized to 0 by default`() {
        val state = initialLockState()
        assertAll(
            { assertEquals(0, state.a) },
            { assertEquals(0, state.b) },
            { assertEquals(0, state.programCounter) },
        )
    }

    @Test
    fun `can specify a different default for registers`() {
        val nonDefaultA: LockState = initialLockState(a = 1)
        val nonDefaultB: LockState = initialLockState(b = 2)
        val nonDefaultPc: LockState = initialLockState(pc = 3)

        assertAll (
            { assertEquals(1, nonDefaultA.a) },
            { assertEquals(2, nonDefaultB.b) },
            { assertEquals(3, nonDefaultPc.programCounter) },
        )
    }
}