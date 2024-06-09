package de.havox_design.aoc.utils.kotlin.helpers.math

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun Iterable<Long>.product() =
    reduce { acc, item -> acc * item }

fun Long.pow(e: Int): Long = Math.pow(this.toDouble(), e.toDouble()).toLong()

fun absoluteMax(num1: Long, num2: Long) =
    max(abs(num1), abs(num2))

fun absoluteMin(num1: Long, num2: Long) =
    min(abs(num1), abs(num2))