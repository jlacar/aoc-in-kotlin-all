package lacar.junilu.aoc2015.day01

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
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

    context("Examples - Part 1") {
        withData(
            nameFn = { "given directions ${it.input} last floor should be ${it.expected}" },
            TestData("(())", 0),
            TestData("()()", 0),
            TestData("(((", 3),
            TestData("(()(()(", 3),
            TestData("))(((((", 3),
            TestData("())", -1),
            TestData("))(", -1),
            TestData(")))", -3),
            TestData(")())())", -3),
        ) { (directions, expectedFloor) ->
            Day01(directions).lastFloor() shouldBe expectedFloor
        }
    }

    context("Examples - Part 2") {
        withData(
            nameFn = { "given directions ${it.input} first position in basement should be ${it.expected}" },
            TestData(")", 1),
            TestData("()()))", 5),
            TestData("()))())(())", 3)
        ) { (directions, expectedPosition) ->
            Day01(directions).firstPositionInBasement() shouldBe expectedPosition
        }
    }
})

internal data class TestData(val input: String, val expected: Int)