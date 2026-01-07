package lacar.junilu.aoc2015.day02

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import lacar.junilu.aoc2015.day02.Day02.Companion.using
import lacar.junilu.readPuzzleLines

class Day02BehaviorSpec : BehaviorSpec({
    Context("Using GitHub puzzle input") {
        val solution = using(readPuzzleLines("aoc2015/day02-gh"))

        Given("A list of box dimensions") {
            When("Calculating the wrapping paper needed") {
                Then("The answer should be correct") {
                    solution.sqFeetOfWrapper() shouldBe 1586300
                }
            }

            When("Calculating the ribbon needed") {
                Then("The answer should be correct") {
                    solution.feetOfRibbon() shouldBe 3737498
                }
            }
        }
    }

    Context("Using GMail puzzle input") {
        val solution = using(readPuzzleLines("aoc2015/day02"))

        Given("A list of box dimensions") {
            When("Calculating the wrapping paper needed") {
                Then("The answer should be correct") {
                    solution.sqFeetOfWrapper() shouldBe 1588178
                }
            }

            When("Calculating the ribbon needed") {
                Then("The answer should be correct") {
                    solution.feetOfRibbon() shouldBe 3783758
                }
            }
        }
    }
})
