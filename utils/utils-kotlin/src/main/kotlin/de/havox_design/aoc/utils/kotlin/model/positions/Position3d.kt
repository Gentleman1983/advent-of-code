package de.havox_design.aoc.utils.kotlin.model.positions

import kotlin.math.abs

data class Position3d<t>(var x: t, var y: t, var z: t)

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