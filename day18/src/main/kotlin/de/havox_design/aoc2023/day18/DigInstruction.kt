package de.havox_design.aoc2023.day18

import de.havox_design.aoc2023.day17.FourDirectionFlipped

data class DigInstruction(val direction: FourDirectionFlipped, val amount: Int, val colorCode: String) {
    fun transformInstruction(): DigInstruction {
        val steps = colorCode.drop(1).take(5).toInt(16)
        val direction = when (colorCode.last()) {
            '0' -> FourDirectionFlipped.RIGHT
            '1' -> FourDirectionFlipped.DOWN
            '2' -> FourDirectionFlipped.LEFT
            '3' -> FourDirectionFlipped.UP
            else -> error("invalid $colorCode")
        }
        return copy(direction = direction, amount = steps)
    }
}
