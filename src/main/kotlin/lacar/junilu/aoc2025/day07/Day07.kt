package lacar.junilu.aoc2025.day07

import java.util.BitSet

class Day07(val lines: List<String>) {

    private val width = lines.first().length

    private val manifold = lines.drop(2).chunked(2)

    fun beamSplits(): Int {
        val beamSource = BitSet(width).also { it.set(lines.first().indexOf('S')) }

        return manifold.fold (Pair(beamSource, 0)) { acc, (splitters, _) ->
            val (activeBeams, totalSplits) = acc
            val splits = splitters.mapIndexed { i, ch ->
                if (ch == '^' && activeBeams.get(i)) {
                    activeBeams.set(i - 1)
                    activeBeams.set(i + 1)
                    activeBeams.clear(i)
                    1
                } else 0
            }
            Pair(activeBeams, totalSplits + splits.sum())
        }.second
    }

    fun beamTimeLines(): Long {
        val beamSource = LongArray(width).also { it[lines.first().indexOf('S')] = 1L }

        return manifold.fold(beamSource) { timeLines, (splitters, _) ->
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