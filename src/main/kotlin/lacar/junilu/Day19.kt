package lacar.junilu

class Day19(
    private val replacements: List<Pair<String, String>>,
    private val molecule: String
) : Solution<Int> {

    override fun part1() = distinctMoleculesFromReplacements()

    override fun part2() = fewestReplacementsFrom("e")

    private fun distinctMoleculesFromReplacements() =
        replacements.flatMap { (left, right) ->
            molecule.replaceOccurrencesOf(left, right)
        }.distinct().size

    private fun fewestReplacementsFrom(startingMolecule: String): Int {
        return 0
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
        val remaining = drop(i)
        if (remaining.startsWith(left)) {
            take(i) + right + remaining.drop(left.length)
        } else ""
    }.filter { it.isNotBlank() }
