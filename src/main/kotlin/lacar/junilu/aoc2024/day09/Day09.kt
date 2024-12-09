package lacar.junilu.aoc2024.day09

import lacar.junilu.println
import lacar.junilu.aoc2024.day09.Day09.AmphiFile as AmphiFile1

class Day09(val diskMap: List<AmphiFile>) {

    // What is the resulting filesystem checksum?
    fun part1(): Long = diskMap.compact()
        .checkSum()

    // Moving whole files instead of single blocks, what is the resulting filesystem checksum?
    fun part2(): Long = diskMap.compact(wholeFiles = true)
        .checkSum()

    data class AmphiFile(val id: Int, val blocks: Int, val freeSpace: Int = 0)

    private val freeBlock = AmphiFile(id = -1, blocks = 1, freeSpace = 0)

    private fun freeSpace(blocks: Int) = List(blocks) { freeBlock }

    private fun toSingles(file: AmphiFile): List<AmphiFile> {
        return List(file.blocks) { file.copy(blocks = 1, freeSpace = 0) } + freeSpace(file.freeSpace)
    }

    private fun List<AmphiFile>.compact(wholeFiles: Boolean = false): List<AmphiFile> {
        val singles = map { toSingles(it) }.flatten()
        val toMove = singles.reversed().iterator()
        var moveIndex = singles.size

        fun nextFromEnd(): AmphiFile {
            while (true) {
                val nextToMove = toMove.next().also { --moveIndex }
                if (nextToMove != freeBlock) return nextToMove
            }
        }

        val compacted = mutableListOf<AmphiFile>()
        singles.mapIndexed { index, single ->
            when {
                single == freeBlock && index < moveIndex -> compacted.add(nextFromEnd())
                else -> compacted.add(single)
            }
        }
        return compacted.dropLast(singles.size - moveIndex)
    }


    companion object {
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
        0 -> index.toLong() * file.id.toLong()
        else -> 0
    }
}.sum()