package lacar.junilu.aoc2025.day08

import lacar.junilu.common.Point3D

private typealias JunctionBox = Point3D
private typealias Circuit = Set<JunctionBox>

class Day08(val boxes: List<JunctionBox>) {

    fun part1(takeCount: Int): Int {
        val circuits = boxes.map { mutableSetOf(it) }.toMutableSet()

        boxPairs()
            .sortedBy { (_, distance) -> distance }
            .take(takeCount)
            .forEach { (nextClosestPair, _) ->
                circuits.removeIf { it.size == 1 && it.first() in nextClosestPair }
                val commonConnections = circuits.filter { conn -> nextClosestPair.any { it in conn } }
                if (commonConnections.isEmpty()) {
                    circuits.add(nextClosestPair)
                } else {
                    circuits.removeAll(commonConnections.toSet())
                    circuits.add(commonConnections.fold(nextClosestPair) {
                        acc, conn -> acc.addAll(conn); acc
                    })
                }
            }

        return circuits
            .sortedBy { it.size }
            .takeLast(3)
            .map { it.size }
            .reduce { acc, size -> acc * size }
    }

    fun part2(): Long {
        val circuits = boxes.map { mutableSetOf(it) }.toMutableSet()
        val boxPairs = boxPairs().sortedBy { (_, distance) -> distance }

        val lastTwoBoxes = boxPairs.asSequence()
            .takeWhile { circuits.size > 1 }
            .fold(boxPairs.first().first) { _, (nextClosestPair, _) ->
                val lastPair = nextClosestPair.toSet()
                circuits.removeIf { it.size == 1 && it.first() in nextClosestPair }
                val existingConnections = circuits.filter { conn -> nextClosestPair.any { it in conn } }
                if (existingConnections.isEmpty()) {
                    circuits.add(nextClosestPair)
                } else {
                    circuits.removeAll(existingConnections.toSet())
                    circuits.add(existingConnections.fold(nextClosestPair) {
                        acc, conn -> acc.addAll(conn); acc
                    })
                }
                lastPair.toMutableSet()
            }

        return lastTwoBoxes.fold(1L) { acc, next -> acc * next.x }
    }

    private fun boxPairs() = boxes.flatMapIndexed { index, box1 ->
        boxes.subList(index + 1, boxes.size).map { box2 ->
            mutableSetOf(box1, box2) to box1.distanceTo(box2)
        }
    }
}

/*

*[Point3D(x=162, y=817, z=812), Point3D(x=425, y=690, z=689)] -> 316.90219311326956
*[Point3D(x=162, y=817, z=812), Point3D(x=431, y=825, z=988)] -> 321.560258738545
*[Point3D(x=906, y=360, z=560), Point3D(x=805, y=96, z=715)] -> 322.36935338211043
*[Point3D(x=431, y=825, z=988), Point3D(x=425, y=690, z=689)] -> 328.11888089532425
*[Point3D(x=862, y=61, z=35), Point3D(x=984, y=92, z=344)] -> 333.6555109690233
*[Point3D(x=52, y=470, z=668), Point3D(x=117, y=168, z=530)] -> 338.33858780813046
*[Point3D(x=819, y=987, z=18), Point3D(x=941, y=993, z=340)] -> 344.3893145845266
*[Point3D(x=906, y=360, z=560), Point3D(x=739, y=650, z=466)] -> 347.59890678769403
*[Point3D(x=346, y=949, z=466), Point3D(x=425, y=690, z=689)] -> 350.786259708102
*[Point3D(x=906, y=360, z=560), Point3D(x=984, y=92, z=344)] -> 352.936254867646
[Point3D(x=592, y=479, z=940), Point3D(x=425, y=690, z=689)] -> 367.9823365326113

162,817,812 ... 425,690,689 ... 431,825,988 ... 346,949,466
57,618,57
906,360,560 ... 805,96,715 ... 739,650,466 ... 862,61,35 ... 984,92,344
592,479,940
352,342,300
466,668,158
542,29,236
52,470,668 ... 117,168,530
216,146,977
819,987,18 ... 941,993,340
970,615,88

*/