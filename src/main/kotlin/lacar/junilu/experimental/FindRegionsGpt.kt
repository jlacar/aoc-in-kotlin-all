package lacar.junilu.experimental

class FindRegionsGpt {
}

data class Region(val symbol: Char, val size: Int, val cells: List<Pair<Int, Int>>)

//fun findRegions(grid: Array<CharArray>): List<Region> {
fun findRegions(grid: List<String>): List<Region> {
    val rows = grid.size
    val cols = grid[0].lastIndex
    val visited = Array(rows) { BooleanArray(cols) } // Track visited cells
    val regions = mutableListOf<Region>() // List to store all regions

    // Directions for moving: up, down, left, right
    val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    fun isValidCell(r: Int, c: Int, symbol: Char): Boolean {
        return r in 0 until rows && c in 0 until cols && !visited[r][c] && grid[r][c] == symbol
    }

    // DFS to explore a region
    fun dfs(r: Int, c: Int, symbol: Char): List<Pair<Int, Int>> {
        val stack = mutableListOf(Pair(r, c))
        val regionCells = mutableListOf<Pair<Int, Int>>()

        while (stack.isNotEmpty()) {
            val (currentR, currentC) = stack.removeLast()
            if (!isValidCell(currentR, currentC, symbol)) continue

            visited[currentR][currentC] = true
            regionCells.add(Pair(currentR, currentC))

            // Add neighboring cells
            for ((dr, dc) in directions) {
                stack.add(Pair(currentR + dr, currentC + dc))
            }
        }
        return regionCells
    }

    // Traverse the grid to find all regions
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (!visited[r][c]) {
                val symbol = grid[r][c]
                val regionCells = dfs(r, c, symbol)
                if (regionCells.isNotEmpty()) {
                    regions.add(Region(symbol, regionCells.size, regionCells))
                }
            }
        }
    }
    return regions
}

fun main() {
//    val grid = arrayOf(
//        "AABCC".toCharArray(),
//        "AABCE".toCharArray(),
//        "DDBCC".toCharArray(),
//        "AABCE".toCharArray(),
//        "AABCE".toCharArray()
//    )
    val grid = """
        AABCC
        AABCE
        DDBCC
        AABCE        
        """.trimIndent().lines()

    val regions = findRegions(grid)
    regions.forEach { region ->
        println("Region with symbol '${region.symbol}': size = ${region.size}, cells = ${region.cells}")
    }
}
