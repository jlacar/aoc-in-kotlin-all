package lacar.junilu

import java.rmi.UnexpectedException

/**
 * AoC 2015 - Day 7: Some Assembly Required
 *
 * https://adventofcode/2015/day/7
 */
class Day07(instructions: List<String>) {

    private val circuit = Circuit.assembleFrom(instructions)

    fun signalOnWireA(): Int {
        knownSignals.clear()
        return circuit.signalTo("a") ?: throw UnexpectedException("Something's wrong!")
    }

    fun signalOnWireAAfterReplacingB(): Int {
        val a = signalOnWireA()

        knownSignals.clear()
        return Circuit(replaceSegmentB(circuit, a)).signalTo("a") ?: throw UnexpectedException("Something's wrong!")
    }

    private fun replaceSegmentB(circuit: Circuit, signal: Int): Map<String, SignalProvider> {
        val newSegments = mutableMapOf<String, SignalProvider>().apply { putAll(circuit.segments) }
        newSegments["b"] = Value("b", signal.toString())
        return newSegments
    }
}

private val knownSignals = object {
    private val signalTo = mutableMapOf<String, Int?>()

    fun clear() = signalTo.clear()

    fun signalTo(wire: String, segments: SegmentMap): Int? {
        if (signalTo[wire] == null) signalTo[wire] = segments.outputOf(wire)
        return signalTo[wire]
    }
}

private enum class Operation {
    ASSIGN { override fun eval(a: Int, b: Int): Int = a },                  // b is ignored
    NOT { override fun eval(a: Int, b: Int): Int = a.inv() and 0xffff },    // mask to 16-bit value
    AND { override fun eval(a: Int, b: Int): Int = a and b },
    OR { override fun eval(a: Int, b: Int): Int = a or b },
    LSHIFT { override fun eval(a: Int, b: Int): Int = a shl b },
    RSHIFT { override fun eval(a: Int, b: Int): Int = a shr b };

    abstract fun eval(a: Int, b: Int = 0): Int
}

private typealias SegmentMap = Map<String, SignalProvider>

private fun SegmentMap.outputOf(id: String): Int? = this[id]?.outputOf(this)

private sealed interface SignalProvider {
    val name: String
    fun outputOf(segments: SegmentMap): Int?
}

/** Extension function to check if value is a numeric literal */
private fun String.isNumber(): Boolean = all { it.isDigit() }

private class Value(override val name: String, val input: String) : SignalProvider {
    override fun outputOf(segments: SegmentMap): Int? =
        if (input.isNumber()) input.toInt()
        else knownSignals.signalTo(input, segments)
}

private class Gate(override val name: String, val op: Operation, val inputA: String, val inputB: String = "?") :
    SignalProvider {
    override fun outputOf(segments: SegmentMap): Int? {
        val valueA = if (inputA.isNumber()) inputA.toInt() else knownSignals.signalTo(inputA, segments)
        val valueB = if (inputB.isNumber()) inputB.toInt() else knownSignals.signalTo(inputB, segments)
        return if (valueA == null || valueB == null) null else op.eval(valueA, valueB)
    }
}

private class Circuit(val segments: SegmentMap) {

    fun signalTo(id: String): Int? = segments.outputOf(id)

    companion object {
        private const val NO_SIGNAL = "_"

        fun assembleFrom(instructions: List<String>): Circuit {
            val segments = mutableMapOf<String, SignalProvider>()
            segments[NO_SIGNAL] = Value("0", "0")
            instructions.forEach { instruction ->
                val segment = signalProviderFrom(instruction)
                segments[segment.name] = segment
            }
            return Circuit(segments)
        }

        private fun signalProviderFrom(instruction: String): SignalProvider {
            val parts = instruction.split(" ")
            return when (parts.size) {                                                           // Patterns:
                3 -> Value(name = parts.last(), parts.first())                                   //   ? -> wire
                4 -> Gate(name = parts.last(), Operation.NOT, parts[1], NO_SIGNAL)               //   NOT wire -> wire
                5 -> Gate(name = parts.last(), Operation.valueOf(parts[1]), parts[0], parts[2])  //   a OP b -> wire
                else -> throw UnsupportedOperationException("Unsupported instruction: $instruction")
            }
        }
    }
}