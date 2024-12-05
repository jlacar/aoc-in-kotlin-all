package lacar.junilu.experimental

import lacar.junilu.readPuzzleInput

class Puzzle(private var puzzleInput: List<String>) {
    fun solve(solution: (List<String>) -> Any): Any = solution.invoke(puzzleInput)

    companion object {
        fun using(fileName: String): Puzzle = Puzzle(readPuzzleInput(fileName))
    }
}

fun solution(block: (List<String>) -> Any) = block
