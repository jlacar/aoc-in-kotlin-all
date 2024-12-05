package lacar.junilu.aoc2024.day05

import lacar.junilu.experimental.solution
import java.rmi.UnexpectedException

/**
 * AoC 2024 - Day 5: Print Queue
 *
 * https://adventofcode.com/2024/day/5
 */
object Day05 {
    val part1 get() = solution { input ->
        val (rules, pages) = parse(input)

        // What do you get if you add up the middle page number from those correctly-ordered updates?
        pages.filter { it.isInRightOrder(rules) }.sumOf { it.middleNumber() }
    }

    val part2 get() = solution { input ->
        val (rules, pages) = parse(input)

        // What do you get if you add up the middle page numbers after correctly ordering just those updates?
        pages.filterNot { it.isInRightOrder(rules) }
            .map { it.sortedWith(UpdatePageComparator(rules)) }
            .sumOf { it.middleNumber() }
    }

    private fun parse(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (firstSection, secondSection) = input.joinToString("\n").split("\n\n")

        val orderingRules = firstSection.lines()
            .map { it.split("|").map(String::trim) }
            .map { (left, right) -> Pair(left.toInt(), right.toInt()) }

        val updatePages = secondSection.lines().map { it.trim().split(",").map(String::toInt) }

        return Pair(orderingRules, updatePages)
    }
}

// region ===== Extensions for page update lists =====

private fun List<Int>.isInRightOrder(orderingRules: List<Pair<Int, Int>>) =
    getOrderedPairs().all { orderingRules.contains(it) }

private fun List<Int>.getOrderedPairs() = mapIndexed { index, page ->
    val rest = this.subList(index + 1, this.size)
    List(rest.size) { page }.zip(rest)
}.flatten()

private fun List<Int>.middleNumber() = get(lastIndex/2)

// region ===== Update Page comparator and helpers =====

private class UpdatePageComparator(val orderingRules: List<Pair<Int, Int>>) : Comparator<Int> {
    private fun orderOf(page1: Int, page2: Int): Int {
        val pagePair = Pair(page1, page2)
        val rule = orderingRules.firstOrNull { it == pagePair || it == pagePair.swap() } ?: throw NoSuchElementException()
        return when (page1) {
            rule.first -> -1
            rule.second -> 1
            else -> throw UnexpectedException("Invalid rule! Comparing $page1 to $page2 against $rule")
        }
    }

    override fun compare(n1: Int?, n2: Int?) = when {
        n1 == null && n2 == null -> 0
        n1 == null -> -1
        n2 == null -> 1
        else -> orderOf(n1, n2)
    }
}

private fun Pair<Int, Int>.swap() = Pair(this.second, this.first)