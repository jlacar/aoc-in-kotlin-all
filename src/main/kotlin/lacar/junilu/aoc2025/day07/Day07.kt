package lacar.junilu.aoc2025.day07

import java.util.BitSet
import java.util.LinkedList

private fun BitSet.split(index: Int) {
    set(index - 1)
    set(index + 1)
    clear(index)
}

private data class Node(val level: Int, val index: Int)

private data class Edge(val from: Node, val to: Node)

private data class Beam(val source: Node, val index: Int)

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

    private val source = Node(0, lines.first().indexOf('S'))

    private val splitters = lines.drop(2).chunked(2).flatMapIndexed { level, (line, _) ->
        line.mapIndexedNotNull { index, ch -> if (ch == '^') Node(level + 1, index) else null }
    }

    private val levels = splitters.map { it.level }.distinct().sorted()
    private val terminalLevel = levels.last() + 1

    private fun traceBeamsThroughLevels(): Pair<List<Beam>, List<Edge>> {
        val sourceBeam = listOf(Beam(source = source, index = source.index))

        val trace = levels.fold(Pair(sourceBeam, emptyList<Edge>())) { acc, level ->
            val (beamsIn, edges) = acc
            val beamIndices = beamsIn.map { it.index }.distinct()

            val splittersHitByBeam = splitters
                .filter { it.level == level && it.index in beamIndices }

            val newEdges = splittersHitByBeam.flatMap { to ->
                beamsIn.filter { it.index == to.index }.distinct().map { beam ->
                    Edge(beam.source, to)
                }
            }

            val (beamsSplit, beamsNotSplit) = beamsIn.partition { beam -> beam.index in splittersHitByBeam.map { it.index } }

            val newBeams = beamsSplit.flatMap { beam ->
                val splitter = Node(level, beam.index)
                listOf(Beam(splitter, beam.index - 1), Beam(splitter, beam.index + 1))
            }

            Pair(beamsNotSplit + newBeams, edges + newEdges)
        }

        val (beamsOut, edges) = trace
        val terminalEdges = beamsOut.distinct().map { Edge(it.source, Node(terminalLevel, it.index)) }

        return Pair(beamsOut,edges + terminalEdges)
    }

    private fun graphFrom(edges: List<Edge>) = edges.groupBy({ it.from }, { it.to })

    fun beamTimeLines(): Int {
        val graph = graphFrom(traceBeamsThroughLevels().second)

        return lines.first().indices.sumOf { index ->
            graph.findAllPaths(source, Node(terminalLevel, index)).size
        }
    }
}

private typealias Path = List<Node>
private typealias Graph = Map<Node, Path>

private val memo = mutableMapOf<Pair<Node, Node>, List<Path>>()

private fun Graph.findAllPaths(start: Node, end: Node): List<Path> {

    fun dfs(current: Node, end: Node, currentPath: LinkedList<Node>, allPaths: MutableList<Path>) {
        currentPath.addLast(current)

        if (current == end) {
            allPaths.add(currentPath.toList())
        } else {
            for (next in getOrDefault(current, emptyList())) {
                dfs(next, end, currentPath, allPaths)
            }
        }

        currentPath.removeLast()
    }

    val cachedResult = memo[Pair(start, end)]
    if (cachedResult != null) {
        return cachedResult
    }

    val allPaths = mutableListOf<Path>()
    val currentPath = LinkedList<Node>()

    dfs(start, end, currentPath, allPaths)

    memo[Pair(start, end)] = allPaths
    return allPaths
}

/*

.......S.......
.......|.......  1
......|^|......  2
......|.|......
.....|^|^|.....  4
.....|.|.|.....
....|^|^|^|....  6
....|.|.|.|....
...|^|^|||^|...  6+2
...|.|.|||.|...
..|^|^|||^|^|..
..|.|.|||.|.|..
.|^|||^||.||^|.
.|.|||.||.||.|.
|^|^|^|^|^|||^|
|.|.|.|.|.|||.|

 */