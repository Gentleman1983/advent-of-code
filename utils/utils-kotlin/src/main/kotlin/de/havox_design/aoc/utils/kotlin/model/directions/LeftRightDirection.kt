package de.havox_design.aoc.utils.kotlin.model.directions

enum class LeftRightDirection(private val symbol: Char) {
    LEFT('L'),
    RIGHT('R');

    companion object {
        fun from(char: Char): LeftRightDirection {
            if (entries.map { dir -> dir.symbol }.contains(char)) {
                return entries.first { dir -> dir.symbol == char }
            }

            throw IllegalArgumentException("Unknown symbol '${char}'.")
        }
    }
}
