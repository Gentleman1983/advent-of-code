package de.havox_design.aoc.utils.kotlin.model.positions

data class Position2d<t>(val x: t, val y: t)

fun Position2d<Int>.east(offset: Int = 1) =
    Position2d(x + offset, y)

fun Position2d<Int>.north(offset: Int = 1) =
    Position2d(x, y - offset)

fun Position2d<Int>.northeast(offset: Int = 1) =
    Position2d(x + offset, y - offset)

fun Position2d<Int>.northwest(offset: Int = 1) =
    Position2d(x - offset, y - offset)

fun Position2d<Int>.south(offset: Int = 1) =
    Position2d(x, y + offset)

fun Position2d<Int>.southeast(offset: Int = 1) =
    Position2d(x + offset, y + offset)

fun Position2d<Int>.southwest(offset: Int = 1) =
    Position2d(x - offset, y + offset)

fun Position2d<Int>.west(offset: Int = 1) =
    Position2d(x - offset, y)
