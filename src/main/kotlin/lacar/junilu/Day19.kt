package lacar.junilu

/**
 * AoC 2015 - Day 19: Medicine for Rudolph
 *
 * https://adventofcode.com/2015/day/19
 */
class Day19(private val replacements: List<Pair<String, String>>, private val molecule: String) {

    fun part1() = newMolecules().distinct().count()

    fun part2() = stepsToReduceToE()

    private fun newMolecules() =
        replacements.flatMap { (element, replacement) ->
            molecule.allChangesFor(element, replacement)
        }

    private fun stepsToReduceToE(): Int {
        val replacementsForE = replacementsForE()
        val otherReplacements = otherReplacements()
        var reducedMolecule = molecule
        var steps = 1
        while (reducedMolecule.isNotContainedIn(replacementsForE)) {
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

private fun String.isNotContainedIn(replacements: List<String>) =
    replacements.none { this == it }

/**
 * Counts how many times the given substring occurs in this string.
 */
private fun String.countOf(substring: String) =
    this.split(substring).size - 1

/**
 * Returns a list of all new molecules created by replacing an occurrence of the
 * given element with the specified replacement.
 */
private fun String.allChangesFor(element: String, replacement: String) =
    indices.map { i ->
        val remaining = drop(i)
        if (remaining.startsWith(element)) {
            take(i) + replacement + remaining.drop(element.length)
        } else ""
    }.filter { it.isNotBlank() }
