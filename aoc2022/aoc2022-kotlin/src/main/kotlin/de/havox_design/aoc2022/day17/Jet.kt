package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

enum class Jet(private val code: String) {
    LEFT("<"),
    RIGHT(">"),
    DOWN("v"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun getJetForCode(code: String): Jet =
            entries.firstOrNull() { jet -> jet.code == code } ?: UNKNOWN

        fun getPositionForJet(jet: Jet): Position2d<Long> =
            when (jet) {
                LEFT -> Position2d(-1L, 0L)
                RIGHT -> Position2d(1L, 0L)
                DOWN -> Position2d(0L, -1L)
                else -> Position2d(0L, 0L)
            }
    }
}

