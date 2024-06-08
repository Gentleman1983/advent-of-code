package de.havox_design.aoc.utils.kotlin.helpers.math

fun Iterable<Long>.product() =
    reduce { acc, item -> acc * item }
