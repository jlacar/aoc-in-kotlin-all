package lacar.junilu.aoc2015.day01

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import lacar.junilu.readPuzzleText

@DisplayName("AoC 2015 - Day 1")
class Day01FunSpec : FunSpec({
    val solution = Day01(readPuzzleText("aoc2015/day01"))

    test("Part 1 solution: last floor visited") {
        solution.lastFloor() shouldBe 280
    }

    test("Part 2 solution: position of instruction for first time in basement") {
        solution.firstPositionInBasement() shouldBe 1797
    }
})
