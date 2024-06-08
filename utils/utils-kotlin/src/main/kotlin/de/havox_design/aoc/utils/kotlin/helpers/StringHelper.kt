package de.havox_design.aoc.utils.kotlin.helpers

import java.util.*

fun String?.parseInts(vararg delimiters: String, radix: Int = 10): List<Int> =
    this
        ?.split(*delimiters)
        ?.mapNotNull { it.trim().toIntOrNull(radix) }
        .orEmpty()

fun String.isUpperCase(locale: Locale = Locale.getDefault()): Boolean =
    this.uppercase(locale) == this

fun String.isLowerCase(locale: Locale = Locale.getDefault()): Boolean =
    !this.isUpperCase(locale)