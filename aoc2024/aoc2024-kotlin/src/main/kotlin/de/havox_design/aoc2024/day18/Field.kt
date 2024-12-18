package de.havox_design.aoc2024.day18

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Field<T>(val field: List<List<T>>) {
    constructor(x: Int, y: Int, element: () -> T) : this(List(x * y) { element.invoke() }.chunked(x))

    val width = field.first().size
    val height = field.size

    private val xIndices = 0 until width
    private val yIndices = 0 until height

    operator fun get(position: Position2d<Int>) =
        field[position.y][position.x]

    operator fun contains(position: Position2d<Int>): Boolean {
        return position.x in xIndices && position.y in yIndices
    }

    @SuppressWarnings("kotlin:S6611")
    fun insertAt(positionMap: Map<Position2d<Int>, T>) =
        this
            .map { position, cell ->
                if (positionMap.containsKey(position)) {
                    positionMap[position]!!
                } else {
                    cell
                }
            }

    fun <R> map(it: (position: Position2d<Int>, cell: T) -> R): Field<R> {
        return field
            .mapIndexed { y, xs ->
                xs
                    .mapIndexed { x, cell ->
                        val position = Position2d(x, y)

                        it(position, cell)
                    }
            }
            .reversed()
            .toField()
    }

    override fun toString() = field
        .reversed()
        .joinToString(System.lineSeparator()) { row ->
            row
                .joinToString(" ")
        }
}

private fun <T> List<List<T>>.toField() =
    Field(this.reversed())
