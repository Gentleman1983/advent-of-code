package de.havox_design.aoc.utils.kotlin.helpers.math

fun Iterable<Long>.product() =
    reduce { acc, item -> acc * item }

fun Long.pow(e: Int): Long = Math.pow(this.toDouble(), e.toDouble()).toLong()