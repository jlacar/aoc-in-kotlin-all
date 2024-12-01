package lacar.junilu.aoc2024.day01

import lacar.junilu.*

/**
 * AoC 2024 - Day 1: Historian Hysteria
 */

object Day01 {
    val puzzleInput = readPuzzleInput("aoc2024/day01-gh")

    fun part1(): Int {
        val (list1, list2) = parse(puzzleInput)

        val sorted1 = list1.sorted()
        val sorted2 = list2.sorted()

        return sorted1.mapIndexed { i, n -> Math.abs(n - sorted2[i]) }.sum()
    }

    fun part2(): Int {
        val (list1, list2) = parse(puzzleInput)

        return list1.map { n -> list2.count { it == n } * n }.sum()
    }

    fun parse(input: List<String>): Pair<List<Int>, List<Int>> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        input.map { line ->
            val (n1, n2) = line.split("   ")
            list1.add(n1.toInt())
            list2.add(n2.toInt())
        }
        return Pair(list1, list2)
    }
}


fun main() {
    val totalDistance = Day01.part1()
    println("Part 1: $totalDistance")
    check(totalDistance == 2285373, lazyMessage =  { "FAILED" })

    val similarityScore = Day01.part2()
    println("Part 2: $similarityScore")
    check(similarityScore == 21142653, lazyMessage =  { "FAILED" })
}

/*
   Part 1 - 2285373
   Part 2 - 21142653
 */