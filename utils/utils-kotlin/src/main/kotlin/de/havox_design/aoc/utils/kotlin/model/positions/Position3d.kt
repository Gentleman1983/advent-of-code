package de.havox_design.aoc.utils.kotlin.model.positions

import kotlin.math.abs

data class Position3d<t>(var x: t, var y: t, var z: t)

operator fun Position3d<Int>.plus(other: Position3d<Int>) =
    Position3d(x + other.x, y + other.y, z + other.z)

operator fun Position3d<Int>.minus(other: Position3d<Int>) =
    Position3d(x - other.x, y - other.y, z - other.z)

fun Position3d<Int>.length2() =
    x * x + y * y + z * z

fun Position3d<Int>.manhattanDistance() =
    abs(x) + abs(y) + abs(z)


fun Position3d<Int>.manhattanDistance(other: Position3d<Int>) =
    listOf(x - other.x, y - other.y, z - other.z)
        .sumOf { abs(it) }

fun Position3d<Int>.adjacent(offset: Int) =
    (-1..1)
        .flatMap { a ->
            (-1..1)
                .flatMap { b ->
                    (-1..1)
                        .map { c ->
                            Position3d(x + a * offset, y + b * offset, z + c * offset)
                        }
                }
        }

fun Position3d<Int>.neighbours(): List<Position3d<Int>> {
    return listOf(
        Position3d<Int>(x - 1, y, z),
        Position3d<Int>(x + 1, y, z),
        Position3d<Int>(x, y - 1, z),
        Position3d<Int>(x, y + 1, z),
        Position3d<Int>(x, y, z - 1),
        Position3d<Int>(x, y, z + 1)
    )
}
