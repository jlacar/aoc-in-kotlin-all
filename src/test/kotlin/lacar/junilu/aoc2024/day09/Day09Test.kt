package lacar.junilu.aoc2024.day09

import lacar.junilu.aoc2024.day09.Day09.AmphiFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {
    @Test
    fun `using first example`() {

    }

    @Test
    fun `using 12345`() {
        assertEquals(listOf(
            AmphiFile(id = 0, blocks = 1, freeSpace = 2),
            AmphiFile(id = 1, blocks = 3, freeSpace = 4),
            AmphiFile(id = 2, blocks = 5, freeSpace = 0),
        ), Day09.using("12345").diskMap)
    }

    companion object {
        private val exampleInput = "2333133121414131402"
    }
}