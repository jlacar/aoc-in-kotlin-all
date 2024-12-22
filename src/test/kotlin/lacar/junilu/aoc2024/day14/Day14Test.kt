package lacar.junilu.aoc2024.day14

import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun `Solution - Part 1`() {
        assertEquals(229868730, usingGmailInput.part1())
        assertEquals(211773366, usingGitHubInput.part1())
    }

    @Test
    fun `Solution - Part 2`() {
        assertEquals(7861, usingGmailInput.part2())
        assertEquals(7344, usingGitHubInput.part2())
    }

    @Test
    fun `Example - Part 1`() {
        val input = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3            
            """.trimIndent().lines()

        val example = Day14.using(input, columns = 11, rows = 7)
        assertEquals(12, example.part1())
    }

    companion object {
        val usingGmailInput = Day14.using(readPuzzleInput("aoc2024/day14-gm"))
        val usingGitHubInput = Day14.using(readPuzzleInput("aoc2024/day14-gh"))
    }
}