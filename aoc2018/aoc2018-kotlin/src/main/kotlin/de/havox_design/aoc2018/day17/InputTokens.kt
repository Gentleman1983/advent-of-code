package de.havox_design.aoc2018.day17

typealias InputTokens<T> = Set<T>

fun <T : InputToken> InputTokens<T>.active() =
    filter { it.isActive() }

fun <T : InputToken> InputTokens<T>.activeAndSorted() =
    active()
        .sortedBy { it.point }
