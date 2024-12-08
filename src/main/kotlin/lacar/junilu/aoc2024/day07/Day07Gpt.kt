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

    val totalCalibrationResult = input.map { parseLine(it) }
        .filter { (target, numbers) -> canMatchTarget(target, numbers) }
        .sumOf { it.first }

    println("Total calibration result: $totalCalibrationResult")
}



// Check if the numbers can match the target using any combination of +, *, and ||
fun canMatchTarget(target: Long, numbers: List<Long>): Boolean {
    if (numbers.isEmpty()) return false

    val numOperators = numbers.size - 1
    val operatorCombinations = generateOperatorCombinations(numOperators)

    for (operators in operatorCombinations) {
        if (evaluateExpression(numbers, operators) == target) {
            return true
        }
    }
    return false
}

// Generate all combinations of +, *, and || operators
fun generateOperatorCombinations(numOperators: Int): List<List<Char>> {
    if (numOperators == 0) return listOf(emptyList())
    val operators = listOf('+', '*', '|') // Use '|' to represent concatenation
    return operators.flatMap { op ->
        generateOperatorCombinations(numOperators - 1).map { listOf(op) + it }
    }
}

// Evaluate the expression with the given numbers and operators, left-to-right
fun evaluateExpression(numbers: List<Long>, operators: List<Char>): Long {
    var result = numbers[0]
    for (i in operators.indices) {
        val nextNumber = numbers[i + 1]
        result = when (operators[i]) {
            '+' -> result + nextNumber
            '*' -> result * nextNumber
            '|' -> concatenate(result, nextNumber) // Handle concatenation
            else -> throw IllegalArgumentException("Unsupported operator: ${operators[i]}")
        }
    }
    return result
}



/*
fun main() {
    val input = listOf(
        "190: 10 19",
        "3267: 81 40 27",
        "83: 17 5",
        "156: 15 6",
        "7290: 6 8 6 15",
        "161011: 16 10 13",
        "192: 17 8 14",
        "21037: 9 7 18 13",
        "292: 11 6 16 20"
    )

    val totalCalibrationResult = input.map { parseLine(it) }
        .filter { (target, numbers) -> canMatchTarget(target, numbers) }
        .sumOf { it.first }

    println("Total calibration result: $totalCalibrationResult")
}

// Parse a line to extract the target value and the list of numbers
fun parseLine(line: String): Pair<Int, List<Int>> {
    val parts = line.split(": ")
    val target = parts[0].toInt()
    val numbers = parts[1].split(" ").map { it.toInt() }
    return target to numbers
}

// Check if the numbers can match the target using any combination of +, *, and ||
fun canMatchTarget(target: Int, numbers: List<Int>): Boolean {
    if (numbers.isEmpty()) return false

    val numOperators = numbers.size - 1
    val operatorCombinations = generateOperatorCombinations(numOperators)

    for (operators in operatorCombinations) {
        if (evaluateExpression(numbers, operators) == target) {
            return true
        }
    }
    return false
}

// Generate all combinations of +, *, and || operators
fun generateOperatorCombinations(numOperators: Int): List<List<Char>> {
    if (numOperators == 0) return listOf(emptyList())
    val operators = listOf('+', '*', '|') // Use '|' to represent concatenation
    return operators.flatMap { op ->
        generateOperatorCombinations(numOperators - 1).map { listOf(op) + it }
    }
}

// Evaluate the expression with the given numbers and operators, left-to-right
fun evaluateExpression(numbers: List<Int>, operators: List<Char>): Int {
    var result = numbers[0]
    for (i in operators.indices) {
        val nextNumber = numbers[i + 1]
        result = when (operators[i]) {
            '+' -> result + nextNumber
            '*' -> result * nextNumber
            '|' -> concatenate(result, nextNumber) // Handle concatenation
            else -> throw IllegalArgumentException("Unsupported operator: ${operators[i]}")
        }
    }
    return result
}

// Concatenate two numbers by joining their digits
fun concatenate(left: Int, right: Int): Int {
    return "$left$right".toInt()
}
*/

/*
How It Works

Parsing Input:
Each line is parsed into a target value and a list of numbers.

Operator Combinations:
Generates all combinations of +, *, and || for the given number of operator positions.
Uses recursion to construct the combinations.

Evaluating Expressions:
Evaluates the expression with the given numbers and operators in left-to-right order.
Includes a helper function concatenate to implement the || operator.

Checking Validity:
For each equation, tests all operator combinations to see if any produce the target value.

Output:
Sums the target values of all valid equations.

Example Run

For the input:

190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20

The program evaluates each equation:

Valid Equations:
190: 10 * 19
3267: 81 * 40 + 27 or 81 + 40 * 27
156: 15 || 6
7290: 6 * 8 || 6 * 15
192: 17 || 8 + 14
292: 11 + 6 * 16 + 20

Total Calibration Result:

190 + 3267 + 156 + 7290 + 192 + 292 = 11387

Performance Considerations

Operator Combinations:
For n numbers, there are 3^(n-1) operator combinations (due to 3 operators).
The solution may become slow for large inputs, but it's efficient for the given problem size.

Optimization Ideas:
Use memoization to cache results for repeated subproblems.
Prune impossible combinations early based on intermediate results.

Output

The program prints:

Total calibration result: 11387

This matches the example given in the problem description. Let me know if you’d like further optimizations or explanations! 😊
You said:
update the program so it can handle Long values
ChatGPT said:
ChatGPT

Updating the program to handle Long values is straightforward in Kotlin. By changing all Int data types to Long, we ensure that the program can handle larger numbers, as Long has a wider range (-2^63 to 2^63-1) compared to Int (-2^31 to 2^31-1). Below is the updated program:
Updated Kotlin Program with Long Support

fun main() {
    val input = listOf(
        "190: 10 19",
        "3267: 81 40 27",
        "83: 17 5",
        "156: 15 6",
        "7290: 6 8 6 15",
        "161011: 16 10 13",
        "192: 17 8 14",
        "21037: 9 7 18 13",
        "292: 11 6 16 20"
    )

    val totalCalibrationResult = input.map { parseLine(it) }
        .filter { (target, numbers) -> canMatchTarget(target, numbers) }
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

// Check if the numbers can match the target using any combination of +, *, and ||
fun canMatchTarget(target: Long, numbers: List<Long>): Boolean {
    if (numbers.isEmpty()) return false

    val numOperators = numbers.size - 1
    val operatorCombinations = generateOperatorCombinations(numOperators)

    for (operators in operatorCombinations) {
        if (evaluateExpression(numbers, operators) == target) {
            return true
        }
    }
    return false
}

// Generate all combinations of +, *, and || operators
//fun generateOperatorCombinations(numOperators: Int): List<List<Char>> {
//    if (numOperators == 0) return listOf(emptyList())
//    val operators = listOf('+', '*', '|') // Use '|' to represent concatenation
//    return operators.flatMap { op ->
//        generateOperatorCombinations(numOperators - 1).map {
//            listOf(op) + it
//        }
//    }
//}

fun generateOperatorCombinations(numOperators: Int): List<List<Char>> {
    if (numOperators == 0) return listOf(emptyList())
    val operators = listOf('+', '*')
    return operators.flatMap { op ->
        generateOperatorCombinations(numOperators - 1).map { listOf(op) + it }
    }
}

// Evaluate the expression with the given numbers and operators, left-to-right
fun evaluateExpression(numbers: List<Long>, operators: List<Char>): Long {
    var result = numbers[0]
    for (i in operators.indices) {
        val nextNumber = numbers[i + 1]
        result = when (operators[i]) {
            '+' -> result + nextNumber
            '*' -> result * nextNumber
            '|' -> concatenate(result, nextNumber) // Handle concatenation
            else -> throw IllegalArgumentException("Unsupported operator: ${operators[i]}")
        }
    }
    return result
}

// Concatenate two numbers by joining their digits
fun concatenate(left: Long, right: Long): Long {
    return "$left$right".toLong()
}

*/