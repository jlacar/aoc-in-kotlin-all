package lacar.junilu.aoc2015.day01

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import lacar.junilu.readPuzzleText

@DisplayName("AoC 2015 - Day 1")
class Day01DescribeSpec : DescribeSpec({
    val solution = Day01(readPuzzleText("aoc2015/day01"))

    describe("Solutions") {
        it("Part 1 - last floor visited") {
            solution.lastFloor() shouldBe 280
        }

        it("Part 2 - position of instruction for first time in basement") {
            solution.positionOfFirstTimeInBasement() shouldBe 1797
        }
    }
})