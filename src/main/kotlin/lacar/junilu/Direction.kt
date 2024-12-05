package lacar.junilu

enum class CardinalDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    companion object {
    }
}

enum class RelativeDirection {
    LEFT, RIGHT, FORWARD, BACKWARD;

    companion object {
    }
}

data class Coordinate(val row: Int = 0, val col: Int = 0) {
//    fun move(cardinalDirection: CardinalDirection, distance: Int = 1): Coordinate {
//
//    }

    companion object {
//        private fun nextPosition()
    }
}