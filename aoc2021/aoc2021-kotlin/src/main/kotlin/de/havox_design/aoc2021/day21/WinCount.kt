package de.havox_design.aoc2021.day21

data class WinCount(val p1: Long, val p2: Long) {
    operator fun plus(other: WinCount) =
        WinCount(p1 + other.p1, p2 + other.p2)

    fun inverted() =
        WinCount(p2, p1)
}
