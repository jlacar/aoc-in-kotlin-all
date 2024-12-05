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
        val (orderingRules, updatePages) = parse(input)

        // What do you get if you add up the middle page number from those correctly-ordered updates?
        updatePages.filter { pages -> pages.isOrderRight(orderingRules) }.sumOf { it.middleNumber() }
    }

    val part2 get() = solution { input ->
        val (orderingRules, updatePages) = parse(input)

        // What do you get if you add up the middle page numbers after correctly ordering just those updates?
        updatePages.filterNot { pages -> pages.isOrderRight(orderingRules) }
            .map { it.fixOrder(orderingRules) }
            .sumOf { it.middleNumber() }

    }

    private fun parse(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (first, second) = input.joinToString("\n").split("\n\n")

        val pageOrderingRules = first.lines()
            .map { it.split("|").map(String::trim) }
            .map { (left, right) -> Pair(left.toInt(), right.toInt()) }

        val pages = second.lines().map { it.trim().split(",").map(String::toInt) }

        return Pair(pageOrderingRules, pages)
    }
}

// Extensions for page update lists
private fun List<Int>.isOrderRight(orderingRules: List<Pair<Int, Int>>) =
    getOrderedPairs().all { orderingRules.contains(it) }

private fun List<Int>.getOrderedPairs() = mapIndexed { index, page ->
    val rest = this.subList(index + 1, this.size)
    List(rest.size) { page }.zip(rest)
}.flatten()

private fun List<Int>.fixOrder(orderingRules: List<Pair<Int, Int>>): List<Int> {
    val inOrder = getOrderedPairs().filter { orderingRules.contains(it) }
    val fixedOrder = getOrderedPairs().filterNot { orderingRules.contains(it) }.map { it.swap() }
    val flattened = (inOrder + fixedOrder).map { it.toList() }.flatten().toSet()

    return flattened.sortedWith(PageComparator(orderingRules))
}

private class PageComparator(val orderingRules: List<Pair<Int, Int>>) : Comparator<Int> {
    private fun orderOf(page1: Int, page2: Int): Int {
        val rule = orderingRules.firstOrNull() { it == Pair(page1, page2) || it == Pair(page2, page1) } ?: throw NoSuchElementException()
        return when (page1) {
            rule.first -> -1
            rule.second -> 1
            else -> throw UnexpectedException("Invalid rule")
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

private fun List<Int>.middleNumber() = get(lastIndex/2)