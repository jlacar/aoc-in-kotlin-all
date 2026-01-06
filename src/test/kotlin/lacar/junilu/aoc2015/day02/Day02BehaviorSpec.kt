package lacar.junilu.aoc2015.day02

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import lacar.junilu.aoc2015.day02.Day02.Companion.using
import lacar.junilu.readPuzzleLines

class Day02BehaviorSpec : BehaviorSpec({
    context("Using GitHub puzzle input") {
        val solution = using(readPuzzleLines("aoc2015/day02-gh"))

        Given("A list of box dimensions") {
            When("Calculating the wrapping paper required") {
                Then("The total wrapping paper required should be calculated correctly") {
                    solution.sqFeetOfWrapper() shouldBe 1586300
                }
            }

            When("Calculating the ribbon required") {
                Then("The total ribbon required should be calculated correctly") {
                    solution.feetOfRibbon() shouldBe 3737498
                }
            }
        }
    }

    context("Using GMail puzzle input") {
        val solution = using(readPuzzleLines("aoc2015/day02"))

        Given("A list of box dimensions") {
            When("Calculating the wrapping paper required") {
                Then("The total wrapping paper required should be calculated correctly") {
                    solution.sqFeetOfWrapper() shouldBe 1588178
                }
            }

            When("Calculating the ribbon required") {
                Then("The total ribbon required should be calculated correctly") {
                    solution.feetOfRibbon() shouldBe 3783758
                }
            }
        }
    }
})
