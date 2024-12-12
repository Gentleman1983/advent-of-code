package de.havox_design.aoc.utils.kotlin.model.field

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

data class Field<T>(val field: List<List<T>>) {

    constructor(x: Int, y: Int, element: () -> T) : this(List(x * y) {
        element.invoke()
    }
        .chunked(x))

    val numberOfX = field
        .first()
        .size
    val numberOfY = field
        .size
    val allPositions: Sequence<Position2d<Int>> = sequence {
        xIndices
            .forEach { y ->
                yIndices
                    .forEach { x ->
                        yield(Position2d(x, y))
                    }
            }
    }

    val xIndices = 0 until numberOfX
    val yIndices = 0 until numberOfY

    operator fun get(position: Position2d<Int>) =
        field[position.y][position.x]

    operator fun contains(position: Position2d<Int>): Boolean {
        return position.x in xIndices &&
                position.y in yIndices
    }

    fun search(element: T) = sequence {
        (0 until numberOfY).flatMap { y ->
            (0 until numberOfX).map { x ->
                if (field[y][x] == element) {
                    yield(Position2d(x, y))
                }
            }
        }
    }


    fun insertAt(position: Position2d<Int>, element: T) =
        this
            .map { positionOfElement, cell ->
                if (position == positionOfElement) {
                    element
                } else {
                    cell
                }
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

    fun slice(xRange: IntRange, yRange: IntRange) =
        field
            .slice(yRange)
            .map { row ->
                row
                    .slice(xRange)
            }
            .reversed()
            .toField()

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

    fun highlight(highlight: (position: Position2d<Int>, cell: T) -> Boolean) {
        val highlightColor = "\u001b[" + 43 + "m"
        val defaultColor = "\u001b[" + 0 + "m"

        this
            .map { position, cell ->
                if (highlight(position, cell)) {
                    "$highlightColor$cell$defaultColor"
                } else {
                    cell.toString()
                }
            }
            .print()
    }


    override fun toString() =
        field
            .reversed()
            .joinToString("\n") { row ->
                row
                    .joinToString(" ")
            }
}

fun <T> List<List<T>>.toField() =
    Field(this.reversed())

fun <T> T.print() =
    this
        .also { println(it) }