package de.havox_design.aoc2021.day22

data class RebootStep(val on: Boolean, val cuboid: Cuboid) {
    fun clampTo(newRange: IntRange): RebootStep =
        RebootStep(on, cuboid.overlap(Cuboid(newRange, newRange, newRange)))
}
