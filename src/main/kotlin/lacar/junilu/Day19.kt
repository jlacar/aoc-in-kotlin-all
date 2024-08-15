package lacar.junilu

class Day19(
    private val replacements: List<Pair<String, String>>,
    private val molecule: String
) : Solution<Int> {

    override fun part1() = newMolecules().distinct().count()

    override fun part2() = stepsToReduceToE()

    private fun newMolecules() =
        replacements.flatMap { (left, right) ->
            molecule.replaceOccurrencesOf(left, right)
        }

    private fun stepsToReduceToE(): Int {
        val finalRedux = replacements.filter { it.first == "e" }.map { Pair(it.second, it.first) }
        val intermediateRedux = replacements.filterNot { it.first == "e" }.map { Pair(it.second, it.first) }
        var newMolecule = molecule
        var steps = 0
        while (finalRedux.none { it.first == newMolecule }) {
            val redux = intermediateRedux.first { newMolecule.contains(it.first) }
            steps += newMolecule.occurrencesOf(redux.first)
            newMolecule = newMolecule.replace(redux.first, redux.second)
        }
        return steps + 1
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

private fun String.occurrencesOf(substring: String) = this.split(substring).size - 1

private fun String.replaceOccurrencesOf(left: String, right: String) =
    indices.map { i ->
        val remaining = drop(i)
        if (remaining.startsWith(left)) {
            take(i) + right + remaining.drop(left.length)
        } else ""
    }.filter { it.isNotBlank() }
