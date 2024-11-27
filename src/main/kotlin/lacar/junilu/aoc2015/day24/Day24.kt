package lacar.junilu.aoc2015.day24

/**
 * AoC 2015 - Day 24: It Hangs in the Balance
 *
 * https://adventofcode.com/2015/day/24
 */
class Day24(val weights: List<Int>) {

    companion object {
        fun using(input: List<String>) = Day24(input.map { it.toInt() })
    }
}
