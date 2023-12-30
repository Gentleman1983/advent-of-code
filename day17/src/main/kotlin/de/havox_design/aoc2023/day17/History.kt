package de.havox_design.aoc2023.day17

data class History(
    val lastDirection: FourDirectionFlipped, val location: Coordinate, val count: Int
) {
    @SuppressWarnings("kotlin:S6510")
    fun moveDirections(maxConsecutive: Int, minConsecutive: Int): List<History> {
        when (count) {
            0 -> {
                return listOf(FourDirectionFlipped.RIGHT, FourDirectionFlipped.DOWN)
                    .map { History(it, it + location, 1) }
            }
            else -> {
                val leftRight =
                    listOf(lastDirection.turnLeft(), lastDirection.turnRight())
                        .map { History(it, it + location, 1) }
                val forward = History(lastDirection, lastDirection + location, count + 1)

                return when {
                    count < minConsecutive -> {
                        listOf(forward)
                    }

                    count == maxConsecutive -> {
                        leftRight
                    }

                    else -> leftRight + forward
                }
            }
        }
    }
}