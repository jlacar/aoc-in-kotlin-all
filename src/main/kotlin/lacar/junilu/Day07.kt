package lacar.junilu

import java.rmi.UnexpectedException

/**
 * AoC 2015 - Day 7: Some Assembly Required
 *
 * https://adventofcode/2015/day/7
 */
class Day07(instructions: List<String>) {

    private val circuit = Circuit.assemble(instructions)

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

    fun signalTo(wire: String, calculateSignalTo: (String) -> Int?): Int? {
        if (signalTo[wire] == null) signalTo[wire] = calculateSignalTo(wire)
        return signalTo[wire]
    }
}

private enum class Op {
    ASSIGN { override fun eval(a: Int, b: Int): Int = a },                  // b is ignored
    NOT { override fun eval(a: Int, b: Int): Int = a.inv() and 0xffff },    // mask to 16-bit value
    AND { override fun eval(a: Int, b: Int): Int = a and b },
    OR { override fun eval(a: Int, b: Int): Int = a or b },
    LSHIFT { override fun eval(a: Int, b: Int): Int = a shl b },
    RSHIFT { override fun eval(a: Int, b: Int): Int = a shr b };

    abstract fun eval(a: Int, b: Int = 0): Int
}

private typealias SegmentMap = Map<String, SignalProvider>

private sealed interface SignalProvider {
    val name: String
    fun outputOf(segments: SegmentMap): Int?
}

/** Extension function to check if value is a numeric literal */
private fun String.isNumber(): Boolean = all { it.isDigit() }

private class Value(override val name: String, val input: String) : SignalProvider {
    override fun outputOf(segments: SegmentMap): Int? =
        if (input.isNumber()) input.toInt()
        else knownSignals.signalTo(input) { id -> segments[id]?.outputOf(segments) }
}

private class Gate(override val name: String, val op: Op, val inputA: String, val inputB: String = "*") :
    SignalProvider {
    override fun outputOf(segments: SegmentMap): Int? {
        val calc: (String) -> Int? = { id -> segments[id]?.outputOf(segments) }
        val valueA = if (inputA.isNumber()) inputA.toInt() else knownSignals.signalTo(inputA, calc)
        val valueB = if (inputB.isNumber()) inputB.toInt() else knownSignals.signalTo(inputB, calc)
        return if (valueA == null || valueB == null) null else op.eval(valueA, valueB)
    }
}

private class Circuit(val segments: SegmentMap) {

    fun signalTo(id: String): Int? = segments[id]?.outputOf(segments)

    companion object {
        private const val NO_SIGNAL = "_"

        fun assemble(instructions: List<String>): Circuit {
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
            return when (parts.size) {
                3 -> Value(name = parts.last(), parts.first())                              // ? -> wire
                4 -> Gate(name = parts.last(), Op.NOT, parts[1], NO_SIGNAL)                 // NOT wire -> wire
                5 -> Gate(name = parts.last(), Op.valueOf(parts[1]), parts[0], parts[2])    // a OP b -> wire
                else -> throw UnsupportedOperationException("Unsupported instruction: $instruction")
            }
        }
    }
}