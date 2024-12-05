package lacar.junilu.experimental

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleTest {
    @Test
    fun `solution provides answer to puzzle part 1`() {
        val lifeTheUniverseAndEverything = Puzzle.using("dsl/hitchhiker")
            .solve(Day01.part1)

        assertEquals(42, lifeTheUniverseAndEverything)
    }

    @Test
    fun `solution provides answer to puzzle part 2`() {
        val lifeAndTheUniverseWithoutEverything = Puzzle.using("dsl/hitchhiker")
            .solve(Day01.part2)

        assertEquals(42, lifeAndTheUniverseWithoutEverything)
    }
}
