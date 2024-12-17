package lacar.junilu.common

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WrapTest {
    @ParameterizedTest(name = "wrap(max = 10, {0}, {1}) -> {2}")
    @CsvSource(
        useHeadersInDisplayName = true, textBlock = """
        pos,      diff,     newPos
        8,           1,          9
        9,           1,          0
        9,          10,          9
        9,          11,          0
        9,         100,          9
        9,         101,          0
        9,         102,          1
        1,          -1,          0
        0,          -1,          9
        0,         -11,          9
        0,        -111,          9
        1,         -20,          1
        1,         -21,          0
        1,         -22,          9
        1,         -23,          8"""
    )
    fun `wrapping math`(pos: Int, diff: Int, newPos: Int) {
        assertEquals(newPos, wrap(10, pos, diff))
    }
}