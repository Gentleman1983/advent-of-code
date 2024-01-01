package de.havox_design.aoc2023.day08

enum class Direction(private val symbol: Char) {
    LEFT('L'),
    RIGHT('R');

    companion object {
        fun from(char: Char): Direction {
            if(entries.map { dir -> dir.symbol }.contains(char)) {
                return entries.first { dir -> dir.symbol == char }
            }

            throw IllegalArgumentException("Unknown symbol '${char}'.")
        }
    }
}