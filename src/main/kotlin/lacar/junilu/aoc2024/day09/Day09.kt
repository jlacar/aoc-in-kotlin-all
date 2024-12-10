package lacar.junilu.aoc2024.day09

import lacar.junilu.println
import lacar.junilu.aoc2024.day09.Day09.AmphiFile as AmphiFile1

class Day09(val diskMap: List<AmphiFile>) {

    // What is the resulting filesystem checksum?
    fun part1(): Long = diskMap.compactByBlock()
        .checkSum()
//        .also { it.joinToString(",\n").println() }.checkSum()

    // Moving whole files instead of single blocks, what is the resulting filesystem checksum?
    fun part2(): Long = diskMap.compactByFile()
//        .checkSum()
        .also { it.joinToString(",\n").println() }.checkSum()

    data class AmphiFile(val id: Int, val blocks: Int, val freeSpace: Int = 0) {
        val totalSpace: Int get() = blocks + freeSpace
    }

    private fun AmphiFile.spreadToSingles(): List<AmphiFile> =
        List(blocks) { copy(blocks = 1, freeSpace = 0) } + List(freeSpace) { FreeSpace }

    private fun List<AmphiFile>.compactByBlock(): List<AmphiFile> {
        val singles = map { it.spreadToSingles() }.flatten()
        val toMove = singles.reversed().iterator()
        var moveIndex = singles.size

        fun nextFromEnd(): AmphiFile {
            while (true) {
                val nextToMove = toMove.next().also { --moveIndex }
                if (nextToMove != FreeSpace) return nextToMove
            }
        }

        val compacted = mutableListOf<AmphiFile>()
        singles.mapIndexed { index, single ->
            when {
                single == FreeSpace && index < moveIndex -> compacted.add(nextFromEnd())
                else -> compacted.add(single)
            }
        }
        return compacted.dropLast(singles.size - moveIndex)
    }

    private fun List<AmphiFile>.compactByFile(): List<AmphiFile> =
        (last().id downTo 1).fold(this) { acc, fileId ->
            acc.moveToFreeSpace(fileId)
        }.map { it.spreadToSingles() }.flatten()

    private fun List<AmphiFile>.moveToFreeSpace(moveId: Int): List<AmphiFile> {
        val moveIndex = indexOfFirst { it.id == moveId }
        val fileToMove = get(moveIndex)
        val toLeft = take(moveIndex)

        val indexOfFree = toLeft.indexOfFirst { it.freeSpace >= fileToMove.blocks }
        if (indexOfFree < 0) return this

        val fileWithFreeSpace = get(indexOfFree)
        val adjustedPred = toLeft.last().copy(freeSpace = toLeft.last().freeSpace + fileToMove.totalSpace)

        return this.take(indexOfFree) +
                fileWithFreeSpace.copy(freeSpace = 0) +
                fileToMove.copy(freeSpace = fileWithFreeSpace.freeSpace - fileToMove.blocks) +
                toLeft.drop(indexOfFree + 1).dropLast(1) +
                adjustedPred +
                subList(moveIndex + 1, size)
    }

    companion object {
        val FreeSpace = AmphiFile(-1, blocks = 0, freeSpace = 1)

        private fun Char.asInt() = this - '0'

        fun using(input: String) = Day09(
            (input + "0").chunked(size = 2).mapIndexed { id, fileDesc ->
                val (blocks, freeSpace) = fileDesc.toCharArray()
                AmphiFile1(id = id, blocks = blocks.asInt(), freeSpace = freeSpace.asInt())
            }
        )
    }
}

fun List<Day09.AmphiFile>.checkSum(): Long = asSequence().mapIndexed { index, file ->
    when (file.freeSpace) {
        0 -> index.toLong() * if (file.id >= 0) file.id.toLong() else 0L
        else -> 0
    }
}.sum()