package lacar.junilu.experimental

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PuzzleTest {
    @Test
    fun `solve provides answer to puzzle part 1`() {
        val lifeTheUniverseAndEverything = Puzzle.using("dsl/hitchhiker")
            .answerTo(Day01.part1)

        assertEquals(42, lifeTheUniverseAndEverything)
    }
}
