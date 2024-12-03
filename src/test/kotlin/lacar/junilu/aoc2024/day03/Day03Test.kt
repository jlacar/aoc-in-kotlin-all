package lacar.junilu.aoc2024.day03

import lacar.junilu.aoc2024.day03.Day03Test.Companion.mulRegex2
import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `Solution - Part 1`() {
        val n = puzzleInputGitHub.sumOf { line ->
            val matches = parse(line)
            matches.sumOf { (n1, n2) -> n1.toLong() * n2.toLong() }.also { it.println() }
        }
        assertEquals(173731097, n)
    }
    // not 675697

    @Test
    fun `Solution - Part 2`() {
        assertEquals(-1, Day03.part2())
    }

    @Test
    fun `Example - Part 1`() {
        val matches = parse(puzzleInputGitHub[5])
        assertEquals(161, matches.sumOf { (n1, n2) -> n1 * n2 })
    }

    // 30905247 + 28925954 + 31209286 + 29093800 + 26557378 + 27039432

//        val matches = parse("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
//        println("matches:")
//        matches.forEach(::println)

//        val n = pairs.sumOf { (n1, n2) -> n1 * n2 }
//        assertEquals(Pair(8, 5), pairs.last())

//        val ghPairs = parse(puzzleInputGitHub.first())
//        assertEquals(Pair(292,56), ghPairs.last())

    companion object {
        val puzzleInputGitHub = readPuzzleInput("aoc2024/day03-gh")

        //        val mulRegex = """.*(?<inst>mul\(\d+,\d+\)).*""".toRegex()
        val mulRegex2 = """mul\((\d+,\d+)\)""".toRegex()
        fun parse(line: String): List<Pair<Int, Int>> {
            val matches = mulRegex2.findAll(line)
            val ops = matches.map { it.groupValues[1] }
            return ops.map {
                val (n1, n2) = it.split(",").map(String::toInt)
                Pair(n1, n2)
            }.toList()
        }
    }
}

//var s = line
//while (s.isNotEmpty()) {
//    s = s.dropWhile { it != 'm' }
//    if (s.startsWith("mul(")) {
//        val matches = mulRegex2.find(s)
//        if (matches != null) {
//            val (inst, rest) = matches.destructured
////                        rest.println()
//            ops += inst
//            s = rest
//        } else {
//            s = s.drop(1)
//        }
//    } else {
//        s = s.drop(1)
//    }
//}
