package de.havox_design.aoc2023.day03

data class Point(val row: Int, val col: Int, val value: Char) {
    fun isNumber(): Boolean =
            value.isDigit()

    fun isSymbol(): Boolean =
            !isNumber() && value != '.'

    fun isGearSymbol(): Boolean =
            value == '*'

    fun getNeighboringCoordinates(): Set<Pair<Int, Int>> =
            setOf(
                    Pair(row - 1, col),
                    Pair(row - 1, col + 1),
                    Pair(row - 1, col - 1),
                    Pair(row, col + 1),
                    Pair(row, col - 1),
                    Pair(row + 1, col),
                    Pair(row + 1, col + 1),
                    Pair(row + 1, col - 1)
            )

    fun getLeftNeighborCoordinate(): Pair<Int, Int> =
            Pair(row, col - 1)

    fun getRightNeighborCoordinate(): Pair<Int, Int> =
            Pair(row, col + 1)
}
