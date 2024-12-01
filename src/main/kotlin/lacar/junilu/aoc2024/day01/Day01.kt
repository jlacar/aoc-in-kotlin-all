package lacar.junilu.aoc2024.day01

import lacar.junilu.println

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */

fun parse(input: List<String>): Pair<List<Int>, List<Int>> {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    input.map { line ->
        val (n1, n2) = line.split("   ").map { it.toInt() }
        list1.add(n1)
        list2.add(n2)
    }
    return Pair(list1, list2)
}

fun main() {
    val (list1, list2) = parse(lacar.junilu.readPuzzleInput("aoc2024/day01-gh"))

    val sorted1 = list1.sorted()
    val sorted2 = list2.sorted()

    sorted1.mapIndexed { i, n -> Math.abs(n - sorted2[i]) }.sum().println()

    sorted1.map { n -> sorted2.count { it == n } * n }.sum().println()
}