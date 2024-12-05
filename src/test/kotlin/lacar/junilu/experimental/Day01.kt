package lacar.junilu.experimental

import lacar.junilu.keyValuePair

class Day01 {
    companion object {
        val part1 = solution { lines ->
            val props = parse(lines)

            // answer to part 1
            props.values.reduce { a, b -> a + b }.sum()
        }

        val part2 = solution { lines ->

            // answer to part 2 uses part 1
            part1(lines) as Int - (parse(lines)["everything"]?.sum() ?: 0)
        }

        private fun parse(lines: List<String>): Map<String, List<Int>> =
            lines.drop(1).associate { line ->
                keyValuePair(line, ": ") { it.split(" ").map(String::toInt) }
            }
    }
}
