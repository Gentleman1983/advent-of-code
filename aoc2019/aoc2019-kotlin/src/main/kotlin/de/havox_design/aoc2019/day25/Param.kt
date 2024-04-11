package de.havox_design.aoc2019.day25

data class Param(val mode: ArgMode, val value: Long) {
    fun resolveValue(intCode: IntCode): Long =
        mode
            .resolveValue(value, intCode)

    fun solutionIndex(intCode: IntCode): Int =
        mode
            .resolveSolutionIndex(value, intCode)
}
