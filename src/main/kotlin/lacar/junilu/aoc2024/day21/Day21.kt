package lacar.junilu.aoc2024.day21

import lacar.junilu.common.Cursor
import lacar.junilu.common.Cursor.Move.*
import lacar.junilu.common.toGrid
import lacar.junilu.println

/**
 * https://adventofcode.com/2024/day/21
 */
class Day21(val codes: List<String>) {

    private val numericKeys =
        """
        789
        456
        123
        .0A
        """.trimIndent()

    private val directionalKeys =
        """
        .^A
        <v>
        """.trimIndent()

    data class Keypad(val keys: String, val controlledKeypad: Keypad? = null) {
        private val keysGrid = keys.toGrid()
        private val gap = '.'
        private val cursorAtA = Cursor(keysGrid).positionAt('A')

        fun directionalKeyCode(moves: List<Cursor.Move>) =
            moves.map {
                when (it) {
                    UP -> '^'
                    DOWN -> 'v'
                    LEFT -> '<'
                    RIGHT -> '>'
                    SELECT -> 'A'
                }
            }.joinToString("")

        fun movesFor(code: String): List<Cursor.Move> =
            when (controlledKeypad) {
                null -> {
                    val track = Pair(cursorAtA, emptyList<Cursor.Move>())
                    code.fold(track) { acc, nextKey ->
                        val (currentCursor, moves) = acc
                        currentCursor.select(nextKey, gap, moves)
                    }.second
                }
                else -> {
                    val controlCode = directionalKeyCode(controlledKeypad.movesFor(code)).also { it.println() }
                    val track = Pair(cursorAtA, emptyList<Cursor.Move>())
                    controlCode.fold(track) { acc, nextKey ->
                        val (currentCursor, moves) = acc
                        currentCursor.select(nextKey, gap, moves)
                    }.second.also { directionalKeyCode(it).println() }
                }
            }
    }

    private val doorKeypad = Keypad(numericKeys)
    private val area1Keypad = Keypad(directionalKeys, doorKeypad)
    private val area2Keypad = Keypad(directionalKeys, area1Keypad)
//    private val area3Keypad = Keypad(directionalKeys, area2Keypad)

    fun part1(): Int {
        return codes.sumOf { code ->
            val moves = area2Keypad.movesFor(code)
            "code $code size = ${moves.size} * ${code.dropLast(1).toInt()}".println()

            area2Keypad.movesFor(code).size.also { "code $code size = $it * ${code.dropLast(1)}".println() } *
            code.dropLast(1).toInt()
        }

//        return codes.sumOf { code ->
//            val moves = area1Keypad.movesFor(code)
//            """
//            $code
//            $moves
//            """.trimIndent().println()
//
//            moves.size
//////            area3Keypad.movesFor(code).size * code.dropLast(1).toInt()
//        }
    }
}

fun main() {
    Day21( //listOf("341A")
        """
        208A
        540A
        685A
        879A
        826A
        """.trimIndent().lines()
//        """
//        671A
//        083A
//        582A
//        638A
//        341A
//        """.trimIndent().lines()
//        """
//        029A
//        980A
//        179A
//        456A
//        379A
//        """.trimIndent().lines()
    ).part1().println()
}



//  3     7   9    A
// ^A <<^^A >>A vvvA

//     3               7       9           A
//  ^  A    < <   ^ ^  A  > >  A   v v v   A
// <A >A v<<A A >^A A >A vA A ^A v<A A A >^A

//               3                                 7               9                          A
//         ^     A             < <         ^ ^     A       > >     A            v v v         A
//    <    A  >  A   v  < <    A A  >   ^  A A  >  A   v   A A  ^  A    v  <    A A A  >   ^  A
// v<<A >>^A vA ^A v<A <A A >>^A A vA <^A >A A vA ^A v<A >^A A <A >A  v<A <A >>^A A A vA <^A >A

//    <    ^  >  A   v  < <    < <  >   ^  A A  >  A   v   > >  ^  A    <  v
// <v<A >>^A vA ^A <vA <A A >>^A A vA <^A >A A vA ^A <vA >^A A <A >A <v<A >A  >^A A A vA <^A >A

/*
                6                               7                       1                             A
        ^ ^     A             < <         ^     A           v v         A       > >         v         A
   <    A A  >  A   v  < <    A A  >   ^  A  >  A   v  <    A A  >   ^  A   v   A A    <    A  >   ^  A
v<<A >>^A A vA ^A v<A <A A >>^A A vA <^A >A vA ^A v<A <A >>^A A vA <^A >A v<A >^A A v<<A >>^A vA <^A >A
74 * 671

                        0                   8                              3                     A
            <           A         ^ ^ ^     A       >         v  v         A           v         A
  v  < <    A  > >   ^  A    <    A A A  >  A   v   A    <    A  A  >   ^  A   v  <    A  >   ^  A
v<A <A A >>^A vA A <^A >A v<<A >>^A A A vA ^A v<A >^A v<<A >>^A  A vA <^A >A v<A <A >>^A vA <^A >A
70 * 83

                                  5               8                       2                           A
        ^ ^           <           A         ^     A           v v         A       >         v         A
   <    A A   v  <    A  > >   ^  A    <    A  >  A   v  <    A A  >   ^  A   v   A    <    A  >   ^  A
v<<A >>^A A v<A <A >>^A vA A <^A >A v<<A >>^A vA ^A v<A <A >>^A A vA <^A >A v<A >^A v<<A >>^A vA <^A >A
76 * 582

                6                     3                               8                               A
        ^ ^     A           v         A             <         ^ ^     A       >         v v v         A
   <    A A  >  A   v  <    A  >   ^  A   v  < <    A  >   ^  A A  >  A   v   A    <    A A A  >   ^  A
v<<A >>^A A vA ^A v<A <A >>^A vA <^A >A v<A <A A >>^A vA <^A >A A vA ^A v<A >^A v<<A >>^A A A vA <^A >A
74 * 638

              3                               4                     1                             A
        ^     A             < <         ^     A           v         A       > >         v         A
   <    A  >  A   v  < <    A A  >   ^  A  >  A   v  <    A  >   ^  A   v   A A    <    A  >   ^  A
v<<A >>^A vA ^A v<A <A A >>^A A vA <^A >A vA ^A v<A <A >>^A vA <^A >A v<A >^A A v<<A >>^A vA <^A >A
72 * 341

 */