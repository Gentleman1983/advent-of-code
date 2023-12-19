package de.havox_design.aoc2023.day19

data class Assignment(
    val x: Long,
    val m: Long,
    val a: Long,
    val s: Long
) {
    fun total() =
        x + m + a + s
}
