package lacar.junilu.aoc2015.day01

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import lacar.junilu.readPuzzleLines

private val solution = Day01(readPuzzleLines("aoc2015/day01").first())

@DisplayName("AoC 2015 - Day 1")
class Day01FunSpec : FunSpec({
    test("Part 1 solution: last floor visited") {
        solution.lastFloor() shouldBe 280
    }

    test("Part 2 solution: position of instruction for first time in basement") {
        solution.positionOfFirstTimeInBasement() shouldBe 1797
    }
})
