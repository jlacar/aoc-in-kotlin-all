package lacar.junilu.aoc2025.day07

private fun Char.isSplitter() = this == '^'

/**
 * AoC 2025 Day 07 - Laboratories
 *
 * Puzzle page: https://adventofcode.com/2025/day/7
 */
class Day07(val lines: List<String>) {

    private val width = lines.first().length
    private val source = lines.first().indexOf('S')

    private val manifold = lines.drop(2).chunked(2)

    fun beamSplits(): Int {
        val beams = BooleanArray(width).also { it[source] = true }
        return manifold.fold (0) { totalSplits, (splitters, _) ->
            splitters.mapIndexed { i, ch ->
                if (ch.isSplitter() && beams[i]) {
                    beams[i - 1] = true
                    beams[i + 1] = true
                    beams[i] = false
                    1
                } else 0
            }.sum() + totalSplits
        }
    }

    fun beamTimeLines(): Long {
        val beamSource = LongArray(width).also { it[source] = 1L }
        return manifold.fold(beamSource) { timeLines, (splitters, _) ->
            splitters.forEachIndexed { i, ch ->
                if (ch.isSplitter()) {
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