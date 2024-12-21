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

    companion object {
        val usingGmailInput = Day14.using(readPuzzleInput("aoc2024/day14-gm"))
        val usingGitHubInput = Day14.using(readPuzzleInput("aoc2024/day14-gh"))
    }
}