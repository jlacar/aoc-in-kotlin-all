package lacar.junilu

class Day19(
    private val replacements: List<Pair<String, String>>,
    private val molecule: String
) : Solution<Int> {

    override fun part1() = newMolecules().distinct().count()

    override fun part2() = stepsToReduceToE()

    private fun newMolecules() =
        replacements.flatMap { (element, replacement) ->
            molecule.replaceOccurrencesOf(element, replacement)
        }

    private fun stepsToReduceToE(): Int {
        val toE = replacementsForE()
        val otherReplacements = otherReplacements()
        var reducedMolecule = molecule
        var steps = 1
        while (reducedMolecule.isNotConvertible(toE)) {
            val (element, replacement) = otherReplacements.first { (element, _) -> reducedMolecule.contains(element) }
            steps += reducedMolecule.countOf(element)
            reducedMolecule = reducedMolecule.replace(element, replacement)
        }
        return steps
    }

    private fun replacementsForE() = replacements.filter { it.first == "e" }.map { it.second }
    private fun otherReplacements() = replacements.filterNot { it.first == "e" }.map { Pair(it.second, it.first) }

    companion object {
        fun using(input: List<String>) = Day19(
            input.dropLast(2)
                .map { it.split(" => ") }
                .map { (element, replacement) -> Pair(element, replacement) },
            molecule = input.last()
        )
    }
}

private fun String.isNotConvertible(toE: List<String>) =
    toE.none { this == it }

/**
 * Counts how many times the given substring occurs in this string.
 */
private fun String.countOf(substring: String) =
    this.split(substring).size - 1

private fun String.replaceOccurrencesOf(left: String, right: String) =
    indices.map { i ->
        val remaining = drop(i)
        if (remaining.startsWith(left)) {
            take(i) + right + remaining.drop(left.length)
        } else ""
    }.filter { it.isNotBlank() }
