package lacar.junilu.aoc2024.day09

class Day09(val diskMap: List<AmphiFile>) {

    data class AmphiFile(val id: Int, val blocks: Int, val freeSpace: Int = 0)

    fun part1(): Long {
        // What is the resulting filesystem checksum?
        return 0L
    }

    companion object {
        private fun Char.asInt() = this - '0'

        fun using(input: String) = Day09(
            (input + "0").chunked(size = 2).mapIndexed { id, fileDesc ->
                val (blocks, freeSpace) = fileDesc.toCharArray()
                AmphiFile(id = id, blocks = blocks.asInt(), freeSpace = freeSpace.asInt())
            }
        )
    }
}