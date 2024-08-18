package lacar.junilu

import kotlinx.serialization.json.*

/**
 * AoC 2015 - Day 12: JSAbacusFramework.io
 *
 * http://adventofcode.com/2015/day/12
 */
class Day12(val input: String) {
    private val json = Json.decodeFromString<JsonElement>(input)

    fun sumOfAllNumbersInDocument() = deepSumOfNumbersIn(json)

    fun sumOfAllNumbersExcludingObjectsWithRed() = deepSumOfNumbersIn(json) { element ->
        element.values.any() { it == JsonPrimitive("red") }
    }
}

private fun deepSumOfNumbersIn(element: JsonElement, skip: (JsonObject) -> Boolean = { false }): Int =
    when (element) {
        is JsonPrimitive -> element.intOrNull ?: 0
        is JsonObject -> if (skip(element)) 0 else element.values.sumOf { deepSumOfNumbersIn(it, skip) }
        is JsonArray -> element.sumOf { deepSumOfNumbersIn(it, skip) }
        else -> 0
    }