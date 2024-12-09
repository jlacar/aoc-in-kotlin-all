package lacar.junilu.aoc2024.day09

import lacar.junilu.aoc2024.day09.Day09.AmphiFile
import lacar.junilu.println
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {
    @Test
    fun `parsing first example`() {
        val diskMap = listOf(
                AmphiFile(id = 0, blocks = 2, freeSpace = 3),
                AmphiFile(id = 1, blocks = 3, freeSpace = 3),
                AmphiFile(id = 2, blocks = 1, freeSpace = 3),
                AmphiFile(id = 3, blocks = 3, freeSpace = 1),
                AmphiFile(id = 4, blocks = 2, freeSpace = 1),
                AmphiFile(id = 5, blocks = 4, freeSpace = 1),
                AmphiFile(id = 6, blocks = 4, freeSpace = 1),
                AmphiFile(id = 7, blocks = 3, freeSpace = 1),
                AmphiFile(id = 8, blocks = 4, freeSpace = 0),
                AmphiFile(id = 9, blocks = 2, freeSpace = 0),
            )

        assertEquals(diskMap, Day09.using(exampleInput).diskMap)
    }

    @Test
    fun `checkSum calculation`() {
        val compacted = "0099811188827773336446555566..............".dropLastWhile { it == '.' }.map { c ->
            AmphiFile(id = c - '0', blocks = 1).also { it.println()}
        }

        assertEquals(1928, compacted.checkSum())
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