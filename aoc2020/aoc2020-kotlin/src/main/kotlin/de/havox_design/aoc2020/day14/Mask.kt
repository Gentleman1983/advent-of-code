package de.havox_design.aoc2020.day14

class Mask(private val orMask: Long, private val andMask: Long) {
    constructor(orMask: String, andMask: String) : this(orMask.toLong(2), andMask.toLong(2))

    fun apply(value: Long): Long =
        value and andMask or orMask
}
