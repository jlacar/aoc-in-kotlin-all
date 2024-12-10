# Day X - (title)

## Notes

## Design

## AI Assistance 

Got stuck in part 2 and caved to AI assistance. This is the second version JB AI Assistant gave. It was still wrong and I had to tweak it quite a bit to get it right.

    private fun List<Day09.AmphiFile>.compactByFile(): List<Day09.AmphiFile> {
        val filesDescending = this.sortedByDescending { it.id }
        val compacted = this.toMutableList()
    
        for (file in filesDescending) {
            // Find a suitable span of free space
            val spaceIndex = compacted.indexOfFirst { 
                it.freeSpace >= file.blocks && compacted.indexOf(it) < this.indexOf(file) 
            }
            if (spaceIndex >= 0) {
                // Turn original file position into free space
                compacted[this.indexOf(file)] =
                    Day09.FreeSpace.copy(freeSpace = file.totalSpace) + compacted[this.indexOf(file)].freeSpace
                
                // Insert the file at the target spaceIndex
                val targetFreeSpace = compacted[spaceIndex].freeSpace
                compacted[spaceIndex] = file.copy(freeSpace = 0) // File placed
    
                // Create new free space entry if there's remaining space
                val remainingFree = targetFreeSpace - file.blocks
                if (remainingFree > 0) {
                    compacted.add(spaceIndex + 1, Day09.FreeSpace.copy(freeSpace = remainingFree))
                }
            }
        }
    
        return compacted
    }

I'm going to try to guide AI to a version that works instead of manually correcting it. Here's the tweaked version that worked:

    private fun List<Day09.AmphiFile>.compactByFile(): List<Day09.AmphiFile> {
        val filesIdsDescending = this.tail.map { it.id }.sortedDescending()
        val compacted = this.toMutableList()

        for (fileId in filesIdsDescending) {
            val fileIndex = compacted.indexOfFirst { it.id == fileId }
            if (fileIndex < 1) continue

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

More code 

/*
    // Version 1 by AI - fails test for example

    private fun List<Day09.AmphiFile>.compactByFile(): List<Day09.AmphiFile> {
        val filesDescending = this.sortedByDescending { it.id }
        val compacted = this.toMutableList()

        for (file in filesDescending) {
            // Find a suitable space for this file
            val spaceIndex = compacted.indexOfFirst {
                it.freeSpace >= file.blocks && compacted.indexOf(it) < this.indexOf(file)
            }
            if (spaceIndex >= 0) {
                // Place the file in this span
                compacted[spaceIndex] = file.copy(freeSpace = 0)
                // Adjust the free space
                compacted[this.indexOf(file)] = compacted[this.indexOf(file)].copy(
                    freeSpace = compacted[this.indexOf(file)].freeSpace + file.totalSpace
                )
            }
        }

        return compacted.map { it.spreadToSingles() }.flatten()
    }
*/


/*
    // My code that didn't work

    private fun List<AmphiFile>.compactByFile(): List<AmphiFile> =
        (last().id downTo 1).fold(this) { acc, fileId ->
            acc.moveToFreeSpace(fileId)
        }.map { it.spreadToSingles() }.flatten()

    private fun List<AmphiFile>.moveToFreeSpace(moveId: Int): List<AmphiFile> {
        val moveIndex = indexOfFirst { it.id == moveId }
        val fileToMove = get(moveIndex)
        val toLeft = take(moveIndex)

        val indexOfFree = toLeft.indexOfFirst { it.hasSpaceFor(fileToMove.blocks) }
        if (indexOfFree < 0) return this

        val filledFile = get(indexOfFree)
        val adjustedPred = toLeft.last().copy(freeSpace = toLeft.last().freeSpace + fileToMove.totalSpace)

        return toLeft.take(indexOfFree) +
                filledFile.copy(freeSpace = 0) +
                fileToMove.copy(freeSpace = filledFile.freeSpace - fileToMove.blocks) +
                toLeft.drop(indexOfFree + 1).dropLast(1) +
                adjustedPred +
                subList(moveIndex + 1, size)
    }
*/
