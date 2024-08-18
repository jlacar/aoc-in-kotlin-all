package lacar.junilu

import kotlin.math.max
import kotlin.math.min

/**
 * AoC 2015 Day 15: Science for Hungry People
 *
 * https://adventofcode.com/2015/day/15
 */
class Day15(private val ingredients: List<Ingredient>, private val teaspoonsTotal: Int) {

    fun highestCookieScore(): Int {
        val properties = listOf("capacity", "durability", "flavor", "texture")
        return proportions(ingredients.size, teaspoonsTotal)
            .maxOf { portion ->
                cookieScore(portion, properties)
            }
    }

    fun highest500CalorieCookieScore(): Int {
        val properties = listOf("capacity", "durability", "flavor", "texture", "calories")
        return proportions(ingredients.size, teaspoonsTotal)
            .maxOf { portion ->
                cookieScore(portion, properties) { prop: String, quantity: Int ->
                    if (prop == "calories") quantity == 500 else true
                }
            }
    }

    private fun proportions(parts: Int, amountToApportion: Int): Sequence<IntArray> = sequence {
        val start = if (parts == 1) amountToApportion else 0
        for (portionSize in (start..amountToApportion)) {
            if (parts <= 1) {
                yield(intArrayOf(portionSize))
            }  else {
                for (portion in proportions(parts - 1, amountToApportion - portionSize)) {
                    yield(intArrayOf(portionSize) + portion)
                }
            }
        }
    }

    private fun cookieScore(
        portions: IntArray,
        properties: List<String>,
        select: (String, Int) -> Boolean = { _, _ -> true }
    ): Int {
        val portionScores = portions.mapIndexed { i, portionSize -> ingredients[i].scoreFor(portionSize) }
        val propertySums = properties.map { property ->
            portionScores.sumOf { score: Map<String, Int> ->
                score.getOrDefault(property, 0)
            }.let { quantity -> if (select(property, quantity)) quantity else 0 }
        }
        return propertySums.take(4).fold(1) { acc, sum -> acc * max(0, sum) } *
                min(1, propertySums.last())
    }

    companion object {
        fun using(input: List<String>, teaspoonsTotal: Int) = Day15 (
            input.map { Ingredient(properties(it)) },
            teaspoonsTotal
        )

        private fun properties(line: String) = toKeyValuePairMap(
            line.substringAfter(": "),
            itemDelimiter = ", ",
            keyValueDelimiter = " ",
            transform = String::toInt
        )
    }

    data class Ingredient(val props: Map<String, Int>) {
        fun scoreFor(portionSize: Int) = props.map { (k, v) -> k to (v * portionSize) }.toMap()
    }
}
