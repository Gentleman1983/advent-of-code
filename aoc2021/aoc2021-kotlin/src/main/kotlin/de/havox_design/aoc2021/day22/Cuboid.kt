package de.havox_design.aoc2021.day22

import kotlin.math.max
import kotlin.math.min

data class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange) {
    val volume: Long = x.size().toLong() * y.size().toLong() * z.size().toLong()

    fun isEmpty() =
        x.isEmpty() ||
                y.isEmpty() ||
                z.isEmpty()

    fun overlaps(other: Cuboid): Boolean =
        overlap(other).volume > 0

    fun overlap(other: Cuboid): Cuboid =
        Cuboid(overlappingRange(x, other.x), overlappingRange(y, other.y), overlappingRange(z, other.z))

    fun subtract(other: Cuboid): List<Cuboid> {
        val overlap = overlap(other)

        return when {
            overlap.isEmpty() -> listOf(this)
            overlap == this -> emptyList()
            else -> {
                val possibleX = splitRange(x, overlap.x)
                val possibleY = splitRange(y, overlap.y)
                val possibleZ = splitRange(z, overlap.z)

                listOf(
                    Cuboid(x, possibleY.first(), z),
                    Cuboid(x, possibleY.last(), z),
                    Cuboid(possibleX.first(), possibleY[1], z),
                    Cuboid(possibleX.last(), possibleY[1], z),
                    Cuboid(possibleX[1], possibleY[1], possibleZ.first()),
                    Cuboid(possibleX[1], possibleY[1], possibleZ.last()),
                )
                    .filter { !it.isEmpty() }
            }
        }
    }

    private fun splitRange(range: IntRange, overlapRange: IntRange): List<IntRange> {
        val before = range.first until overlapRange.first
        val after = overlapRange.last + 1..range.last

        return listOf(before, overlapRange, after)
    }

    private fun overlappingRange(first: IntRange, second: IntRange) =
        max(first.first, second.first)..min(first.last, second.last)

    private fun IntRange.size(): Int =
        max(0, last - first + 1)
}
