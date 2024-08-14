package lacar.junilu

class Day19(
    private val replacements: List<Pair<String, String>>,
    private val molecule: String
) : Solution<Int> {

    override fun part1(): Int {
        return replacements.flatMap { (left, right) ->
            molecule.replaceOccurrencesOf(left, right)
        }.distinct().size
    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        fun using(input: List<String>) = Day19(
            input.dropLast(2).map { line ->
                val (left, right) = line.split(" => ")
                Pair(left, right)
            },
            molecule = input.last()
        )
    }
}

private fun String.replaceOccurrencesOf(left: String, right: String) =
    indices.map { i ->
        val rest = drop(i)
        if (rest.startsWith(left)) {
            take(i) + right + rest.drop(left.length)
        } else ""
    }.filter { it.isNotBlank() }
