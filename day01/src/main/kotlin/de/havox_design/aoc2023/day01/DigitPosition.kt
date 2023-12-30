package de.havox_design.aoc2023.day01

class DigitPosition(val digit: Digit, val position: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DigitPosition) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position
    }

    override fun toString(): String {
        return "DigitPosition(word=${digit.word}, position=$position)"
    }
}