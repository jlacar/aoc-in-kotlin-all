package lacar.junilu.aoc2024.day09

import lacar.junilu.tail
import lacar.junilu.aoc2024.day09.Day09.AmphiFile as AmphiFile1

class Day09(val diskMap: List<AmphiFile>) {

    // What is the resulting filesystem checksum?
    fun part1(): Long = diskMap.compactByBlock()
        .checkSum()
//        .also { it.joinToString(",\n").println() }.checkSum()

    // Moving whole files instead of single blocks, what is the resulting filesystem checksum?
    fun part2(): Long = diskMap.compactByFile()
        .checkSum()
//        .also { it.joinToString(",\n").println() }.checkSum()

    data class AmphiFile(val id: Int, val blocks: Int, val freeSpace: Int = 0) {
        val totalSpace: Int get() = blocks + freeSpace
        fun hasSpaceFor(blocks: Int) = freeSpace >= blocks
    }

    private fun AmphiFile.spreadToSingles(): List<AmphiFile> =
        List(blocks) { copy(blocks = 1, freeSpace = 0) } + List(freeSpace) { FreeSpace }

    fun List<AmphiFile>.checkSum(): Long = asSequence().mapIndexed { index, file ->
        when (file.freeSpace) {
            0 -> index.toLong() * file.id
            else -> 0
        }
    }.sum()

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

    // Version 2.0 - AI-assisted with tweaks to get it right. See original in README
    private fun List<Day09.AmphiFile>.compactByFile(): List<Day09.AmphiFile> {
        val filesIdsDescending = this.tail.map { it.id }.sortedDescending()
        val compacted = this.toMutableList()

        for (fileId in filesIdsDescending) {
            val fileIndex = compacted.indexOfFirst { it.id == fileId }
            val file = compacted[fileIndex]

            // Find a suitable span of free space
            val spaceIndex = compacted.indexOfFirst {
                it.hasSpaceFor(file.blocks) && compacted.indexOf(it) < fileIndex
            }
            if (spaceIndex < 0) continue

            // Turn original file position into free space and combined it with its predecessors free space
            val predecessor = compacted[fileIndex - 1]
            compacted[fileIndex - 1] = predecessor.copy(freeSpace = predecessor.freeSpace + file.totalSpace)
            compacted.removeAt(fileIndex)

            // Zero out target's free space and insert file after target with an remaining free space from target
            val target = compacted[spaceIndex]
            compacted[spaceIndex] = target.copy(freeSpace = 0)
            compacted.add(spaceIndex + 1, file.copy(freeSpace = target.freeSpace - file.blocks))
        }

        return compacted.map { it.spreadToSingles() }.flatten()
    }

    companion object {
        val FreeSpace = AmphiFile(-1, blocks = 0, freeSpace = 1)

        fun using(input: String) = Day09(
            (input + "0")
                .chunked(2)
                .mapIndexed { id, fileDesc ->
                    val (blocks, freeSpace) = fileDesc.map { it - '0' }
                    AmphiFile1(id = id, blocks = blocks, freeSpace = freeSpace)
                }
        )
    }
}