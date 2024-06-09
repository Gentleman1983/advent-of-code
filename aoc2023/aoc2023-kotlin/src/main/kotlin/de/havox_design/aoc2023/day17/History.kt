package de.havox_design.aoc2023.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped

data class History(
    val lastDirection: FourDirectionsFlipped, val location: Coordinate, val count: Int
) {
    @SuppressWarnings("kotlin:S6510")
    fun moveDirections(maxConsecutive: Int, minConsecutive: Int): List<History> {
        when (count) {
            0 -> {
                return listOf(FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.DOWN)
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
