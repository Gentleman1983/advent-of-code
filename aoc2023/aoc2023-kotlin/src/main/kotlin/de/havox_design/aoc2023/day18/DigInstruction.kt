package de.havox_design.aoc2023.day18

import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped

data class DigInstruction(val direction: FourDirectionsFlipped, val amount: Int, val colorCode: String) {
    fun transformInstruction(): DigInstruction {
        val steps = colorCode.drop(1).take(5).toInt(16)
        val direction = when (colorCode.last()) {
            '0' -> FourDirectionsFlipped.RIGHT
            '1' -> FourDirectionsFlipped.DOWN
            '2' -> FourDirectionsFlipped.LEFT
            '3' -> FourDirectionsFlipped.UP
            else -> error("invalid $colorCode")
        }

        return copy(direction = direction, amount = steps)
    }
}
