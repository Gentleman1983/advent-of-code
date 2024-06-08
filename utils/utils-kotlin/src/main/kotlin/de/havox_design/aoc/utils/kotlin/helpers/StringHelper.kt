package de.havox_design.aoc.utils.kotlin.helpers

fun String?.parseInts(vararg delimiters: String, radix: Int = 10): List<Int> =
    this
        ?.split(*delimiters)
        ?.mapNotNull { it.trim().toIntOrNull(radix) }
        .orEmpty()