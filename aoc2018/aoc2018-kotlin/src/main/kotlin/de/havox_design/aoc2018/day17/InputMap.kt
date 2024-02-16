package de.havox_design.aoc2018.day17

typealias InputMap<T> =
        List<LineWithToken<T>>

fun <T : InputToken> InputMap<T>.getTokens(): InputTokens<T> =
    flatMap { it.tokens() }
        .toSet()
