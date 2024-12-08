package lacar.junilu.aoc2024.day07

import lacar.junilu.readPuzzleInput

fun main() {
//    val input = listOf(
//        "190: 10 19",
//        "3267: 81 40 27",
//        "83: 17 5",
//        "156: 15 6",
//        "7290: 6 8 6 15",
//        "161011: 16 10 13",
//        "192: 17 8 14",
//        "21037: 9 7 18 13",
//        "292: 11 6 16 20"
//    )

    val input = readPuzzleInput("aoc2024/day07-gh")

    // Specify which operators to use
    val operators = listOf<(Long, Long) -> Long>(
        { a, b -> a + b },                // Addition
        { a, b -> a * b },                // Multiplication
        { a, b -> concatenate(a, b) }     // Concatenation
    )

// Part 1 - 303766880536
// Part 2 - 337041851384440

    val totalCalibrationResult = input.map { parseLine(it) }
        .filter { (target, numbers) -> canMatchTarget(target, numbers, operators) }
        .sumOf { it.first }

    println("Total calibration result: $totalCalibrationResult")
}

// Parse a line to extract the target value and the list of numbers
fun parseLine(line: String): Pair<Long, List<Long>> {
    val parts = line.split(": ")
    val target = parts[0].toLong()
    val numbers = parts[1].split(" ").map { it.toLong() }
    return target to numbers
}

// Check if the numbers can match the target using any combination of the given operators
fun canMatchTarget(target: Long, numbers: List<Long>, operators: List<(Long, Long) -> Long>): Boolean {
    if (numbers.isEmpty()) return false

    val numOperators = numbers.size - 1
    val operatorCombinations = generateOperatorCombinations(numOperators, operators)

    for (operatorSet in operatorCombinations) {
        if (evaluateExpression(numbers, operatorSet) == target) {
            return true
        }
    }
    return false
}

// Generate all combinations of the provided operators
fun generateOperatorCombinations(
    numOperators: Int,
    operators: List<(Long, Long) -> Long>
): List<List<(Long, Long) -> Long>> {
    if (numOperators == 0) return listOf(emptyList())
    return operators.flatMap { op ->
        generateOperatorCombinations(numOperators - 1, operators).map { listOf(op) + it }
    }
}

// Evaluate the expression with the given numbers and operators, left-to-right
fun evaluateExpression(numbers: List<Long>, operators: List<(Long, Long) -> Long>): Long {
    var result = numbers[0]
    for (i in operators.indices) {
        val nextNumber = numbers[i + 1]
        result = operators[i](result, nextNumber)
    }
    return result
}

// Concatenate two numbers by joining their digits
fun concatenate(left: Long, right: Long): Long {
    return "$left$right".toLong()
}