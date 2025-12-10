package lacar.junilu.aoc2025.day07

import java.util.BitSet
import java.util.LinkedList

private fun BitSet.split(index: Int) {
    set(index - 1)
    set(index + 1)
    clear(index)
}

class Day07(val lines: List<String>) {

    private val width = lines.first().length

    private fun newBitSet(): BitSet = BitSet(width)

    fun beamSplits(): Int {
        fun String.findAllSplitters(): BitSet = newBitSet()
            .also { bits -> forEachIndexed { i, ch -> if (ch == '^') bits.set(i) } }

        val initialBeam = newBitSet().also { it.set(lines.first().indexOf('S')) }

        val levels = lines.drop(2).chunked(2)

        return levels.fold(Pair(initialBeam, 0)) { acc, (nextLevel, _) ->
            val (beamsIn, totalSplits) = acc
            val splitters = nextLevel.findAllSplitters()
            val beamsOut = newBitSet().apply { or(beamsIn) }
            val splits = (0 until width).fold(totalSplits) { acc, i ->
                if (beamsIn.get(i) && splitters.get(i)) {
                    beamsOut.split(i)
                    acc + 1
                } else {
                    acc
                }
            }
            Pair(beamsOut, splits)
        }.second
    }

    fun beamTimeLines(): Long = lines.drop(2).chunked(2).fold (
            LongArray(width).also { it[lines.first().indexOf('S')] = 1L}
        ) { timeLines, (splitters, _) ->
            splitters.forEachIndexed { i, ch ->
                if (ch == '^') {
                    timeLines[i - 1] += timeLines[i]
                    timeLines[i + 1] += timeLines[i]
                    timeLines[i] = 0
                }
            }
            timeLines
        }.sum()
}

/*

```
The Manifold:    Beams            Levels and # of beams

.......S.......  .......S.......  L0 = 1
.......|.......  .......1.......
......|^|......  ......1^1......  L1 = 1,1 = 2
......|.|......  ......|.|......
.....|^|^|.....  .....1^2^1.....  L2 = 1,2,1 = 4
.....|.|.|.....  .....|.|.|.....
....|^|^|^|....  ....1^3^3^1....  L3 = 1,3,3,1 = 8
....|.|.|.|....  ....|.|.|.|....
...|^|^|||^|...  ...1^4^3|1^1...  L4 = 1,4,3,3,1,1 = 13
...|.|.|||.|...  ...|.|.|||.|...
..|^|^|||^|^|..  ..1^5^4|4^2^1..  L5 = 1,5,4,3,4,2,1 = 20
..|.|.|||.|.|..  ..|.|.|||.|.|..
.|^|||^||.||^|.  .1^1|4^7|.|1^1.  L6 = 1,1,5,4,7,4,2,1,1 = 26
.|.|||.||.||.|.  .|.|||.||.||.|.
|^|^|^|^|^|||^|  1^2^A^B^B^||1^1  L7 = 1,2,10,11,11,2,1,1,1 = 40
|.|.|.|.|.|||.|  |.|.|.|.|.|||.|
                 1 2 A B B 211 1
```

 */