package de.havox_design.aoc2021.day16

import java.math.BigInteger

data class Packet(
    val version: Int,
    val type: Int,
    val body: Body,
) {
    fun value(): BigInteger = body.value()
}
