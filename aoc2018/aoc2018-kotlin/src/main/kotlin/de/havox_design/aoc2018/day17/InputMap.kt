package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

typealias InputMap<T> =
        List<LineWithToken<T>>

fun <T : InputToken> InputMap<T>.getTokens(): InputTokens<T> =
    flatMap { it.tokens() }
        .toSet()
