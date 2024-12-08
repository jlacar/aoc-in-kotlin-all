package lacar.junilu.aoc2024.day07

import lacar.junilu.print
import lacar.junilu.println

class Day07(private val equations: Map<Long, List<Long>>) {

    // What is their total calibration result?
    fun part1(): Long = equations.map { (testValue, operands) ->
        calibrationResult(testValue, operands) // .also { if (it < 0) it.println() }
    }.sum()

    private fun calibrationResult(testValue: Long, operands: List<Long>): Long {
        val first = listOf(operands.first())
        val calibrations = operands.drop(1).fold(first) { acc, n ->
            acc.map { lastResult -> listOf(
                (lastResult + n).also { "[ $lastResult + $n = $it, ".print() },
                (lastResult * n).also { "$lastResult * $n = $it ]".print() }
            ) }.flatten().also { " -> $it".println() }
        }

//        val expected = (1..<operands.lastIndex).fold(2) { acc, _ -> 2 * acc }
//        if (calibrations.size != expected) {
//            println("expected $expected but only got ${calibrations.size} for $testValue: $operands")
//            return 0
//        }

        return when (testValue in calibrations) {
            true ->  testValue // .also { "$testValue is in $calibrations".println()}
            false -> 0L.also { "$testValue: $operands".println() }
        }
    }

    // What is ...
    fun part2(): Int {
        TODO()
    }

    companion object {
        fun using(lines: List<String>) = Day07(parse(lines))
        private fun parse(lines: List<String>) = lines.associate { equation ->
            val (testValue, operands) = equation.split(": ")
            testValue.toLong() to operands.split(" ").map { it.toLong() }
        }

        fun testUsing(text: String, mockCalibrationResults: List<Long>) = Day07(
            parse(text.lines()),
            mockCalibrationResults
        )
    }

    // region: ===== Test-related infrastructure =====

    private lateinit var mockCalibrationResults: List<Long>
    private lateinit var testPart1Results: Iterator<Long>

    constructor(equations: Map<Long, List<Long>>, testCalibrationResults: List<Long>) : this(equations) {
        mockCalibrationResults = testCalibrationResults
        testPart1Results = mockCalibrationResults.iterator()
    }
}
