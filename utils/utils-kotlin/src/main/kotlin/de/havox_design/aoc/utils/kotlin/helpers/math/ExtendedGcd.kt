package de.havox_design.aoc.utils.kotlin.helpers.math

data class ExtendedGcd(val g: Long, val x: Long, val y: Long) {
    companion object {
        fun extendedGcd(a: Long, b: Long): ExtendedGcd {
            return when {
                a == 0L -> ExtendedGcd(b, 0, 1)
                b == 0L -> ExtendedGcd(a, 1, 0)
                else -> {
                    val (g, x1, y1) = extendedGcd(b, a % b)
                    val x = y1
                    val y = x1 - y1 * (a / b)
                    ExtendedGcd(g, x, y)
                }
            }
        }
    }
}
