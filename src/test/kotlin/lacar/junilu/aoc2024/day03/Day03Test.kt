package lacar.junilu.aoc2024.day03

import lacar.junilu.println
import lacar.junilu.readPuzzleInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `Solution - Part 1`() {
        val matches = parse(puzzleInputGitHub)
        val n = matches.sumOf { (n1, n2) -> n1 * n2 }
        assertEquals(173731097, n)
    }

    @Test
    fun `Solution - Part 2`() {
        val matches = parse2(puzzleInputGitHub)
        val n = matches.sumOf { (n1, n2) -> n1 * n2 }
        assertEquals(93729253, n)
    }

    @Test
    fun `Example - Part 1`() {
        val matches = parse("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
        assertEquals(161, matches.sumOf { (n1, n2) -> n1 * n2 })
    }

    @Test
    fun `Example - Part 2`() {
        val matches = parse2(readPuzzleInput("aoc2024/day03-gh")[0])
//        matches.forEach { it.println() }
//        val matches = parse2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
//        assertEquals(48, matches.sumOf { (n1, n2) -> n1 * n2 })
    }

//        val matches = parse(puzzleInputGitHub[1])
// 30905247 + 28925954 + 31209286 + 29093800 + 26557378 + 27039432

//        println("matches:")
//        matches.forEach(::println)

//        val n = pairs.sumOf { (n1, n2) -> n1 * n2 }
//        assertEquals(Pair(8, 5), pairs.last())

//        val ghPairs = parse(puzzleInputGitHub.first())
//        assertEquals(Pair(292,56), ghPairs.last())

    companion object {
        val puzzleInputGitHub = readPuzzleInput("aoc2024/day03-gh").joinToString("")

        //        val mulRegex = """.*(?<inst>mul\(\d+,\d+\)).*""".toRegex()
        val mulRegex1 = """mul\((\d+,\d+)\)""".toRegex()
        fun parse(line: String): List<Pair<Int, Int>> {
            val matches = mulRegex1.findAll(line)
            val ops = matches.map { it.groupValues[1] }
            return ops.map {
                val (n1, n2) = it.split(",").map(String::toInt)
                Pair(n1, n2)
            }.toList()
        }

        val mulRegex2 = """mul\((\d+,\d+)\)|(don't\(\))|(do\(\))""".toRegex()
        fun parse2(line: String): List<Pair<Int, Int>> {
            val matches = mulRegex2.findAll(line)

//            matches.forEach { it.groupValues[0].println() }

            var emit = true
            val products = mutableListOf<Pair<Int, Int>>()
            matches.forEach { match ->
                when (match.groupValues[0]) {
                    "do()" -> emit = true
                    "don't()" -> emit = false
                    else -> if (emit) {
                        val (n1, n2) = match.groupValues[0].drop(4).dropLast(1).split(",").map(String::toInt)
                        products.add(Pair(n1, n2))
                    }
                }
            }
            return products
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
