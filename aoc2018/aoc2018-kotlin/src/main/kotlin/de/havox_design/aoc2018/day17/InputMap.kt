package de.havox_design.aoc2018.day17

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate

typealias InputMap<T> =
        List<LineWithToken<T>>

fun <T : InputToken> InputMap<T>.getTokens(): InputTokens<T> =
    flatMap { it.tokens() }
        .toSet()

fun <T : InputToken> InputMap<T>.at(point: Coordinate) =
    this[point.y].at(point.x)

fun <T : InputToken> List<String>.asInputMap(
    tokenProcessor: (Int, String) -> Set<T>,
    baseProcessor: (String) -> String
) =
    this
        .mapIndexed { i, line ->
            InputLine(tokenProcessor(i, line), baseProcessor(line))
        }

fun <T : InputToken> InputMap<T>.render(offset: Coordinate = Coordinate(0, 0)): List<String> =
    map { it.render(offset) }
