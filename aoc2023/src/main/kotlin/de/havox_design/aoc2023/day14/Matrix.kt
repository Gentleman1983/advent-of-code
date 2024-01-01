package de.havox_design.aoc2023.day14

data class Matrix(val matrix: MutableList<CharArray>) {
    private val ICON_ROUND_STONE = 'O'
    private val ICON_SQUARE_STONE = '#'

    fun cycle(cycles: Int = 1): Matrix = apply {
        repeat(cycles) {
            rollNorth()
                .rollWest()
                .rollSouth()
                .rollEast()
        }
    }

    fun rollNorth(): Matrix =
        apply {
            matrix
                .first()
                .indices
                .forEach { columnIndex ->

                    matrix
                        .indices
                        .joinToString("") { rowIndex ->
                            matrix[rowIndex][columnIndex].toString()
                        }
                        .split(ICON_SQUARE_STONE)
                        .joinToString(ICON_SQUARE_STONE.toString()) {
                            it
                                .toCharArray()
                                .sortedDescending()
                                .joinToString("")
                        }

                        .forEachIndexed { rowIndex, c ->
                            matrix[rowIndex][columnIndex] = c
                        }
                }
        }

    fun rollSouth(): Matrix =
        apply {
            matrix
                .first()
                .indices
                .forEach { columnIndex ->

                    matrix
                        .indices
                        .joinToString("") { rowIndex ->
                            matrix[rowIndex][columnIndex].toString()
                        }
                        .split(ICON_SQUARE_STONE)
                        .joinToString(ICON_SQUARE_STONE.toString()) {
                            it
                                .toCharArray()
                                .sorted()
                                .joinToString("")
                        }

                        .forEachIndexed { rowIndex, c ->
                            matrix[rowIndex][columnIndex] = c
                        }
                }
        }

    fun rollWest(): Matrix =
        apply {
            matrix
                .forEachIndexed { rowIndex, row ->
                    matrix[rowIndex] = row
                        .joinToString("")
                        .split(ICON_SQUARE_STONE)
                        .joinToString(ICON_SQUARE_STONE.toString()) {
                            it
                                .toCharArray()
                                .sortedDescending()
                                .joinToString("")
                        }
                        .toCharArray()
                }
        }

    fun rollEast(): Matrix =
        apply {
            matrix
                .forEachIndexed { rowIndex, row ->
                    matrix[rowIndex] = row
                        .joinToString("")
                        .split(ICON_SQUARE_STONE)
                        .joinToString(ICON_SQUARE_STONE.toString()) {
                            it
                                .toCharArray()
                                .sorted()
                                .joinToString("")
                        }
                        .toCharArray()
                }
        }

    fun northLoad(): Int =
        matrix
            .first()
            .indices
            .sumOf { columnIndex ->
                matrix.indices
                    .sumOf { rowIndex ->
                        when (ICON_ROUND_STONE) {
                            matrix[rowIndex][columnIndex] -> matrix.size - rowIndex
                            else -> 0
                        }
                    }
            }

    override fun toString(): String =
        matrix
            .joinToString("\n") { line -> line.joinToString("") }
}

fun Matrix(input: List<String>): Matrix =
    input
        .map(String::toCharArray)
        .toMutableList()
        .let(::Matrix)