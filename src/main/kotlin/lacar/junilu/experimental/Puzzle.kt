package lacar.junilu.experimental

import lacar.junilu.readPuzzleInput

class Puzzle(private var puzzleInput: List<String>) {
    fun solve(solution: (List<String>) -> Any): Any = solution.invoke(puzzleInput)

    companion object {
        fun using(fileName: String) = Puzzle(readPuzzleInput(fileName))

        fun usingText(text: String) = Puzzle(text.lines())
    }
}

fun solution(block: (List<String>) -> Any) = block
