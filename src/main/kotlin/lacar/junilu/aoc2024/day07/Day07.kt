package lacar.junilu.aoc2024.day07

class Day07(private val equations: Map<Int, List<Int>>) {
    // What is their total calibration result?
    fun part1() = equations.map { (testValue, operands) ->
        calibrationResult(testValue, operands)
    }.sum()

    private fun calibrationResult(testValue: Int, operands: List<Int>): Int {
        return testPart1Results.next()
    }

    // What is ...
    fun part2(): Int {
        TODO()
    }

    companion object {
        fun using(lines: List<String>) = Day07(parse(lines))
        private fun parse(lines: List<String>) = lines.associate { equation ->
            val (testValue, operands) = equation.split(": ")
            testValue.toInt() to operands.split(" ").map { it.toInt() }
        }

        fun testUsing(text: String, mockCalibrationResults: List<Int>) = Day07(
            parse(text.lines()),
            mockCalibrationResults
        )

    }

    // region: ===== Test-related infrastructure =====

    private lateinit var mockCalibrationResults: List<Int>
    private lateinit var testPart1Results: Iterator<Int>

    constructor(equations: Map<Int, List<Int>>, testCalibrationResults: List<Int>) : this(equations) {
        mockCalibrationResults = testCalibrationResults
        testPart1Results = mockCalibrationResults.iterator()
    }
}
