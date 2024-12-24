package de.havox_design.aoc2024.day24

enum class Operation {
    AND, OR, XOR;

    fun doOperation(left: Boolean, right: Boolean): Boolean {
        return when (this) {
            AND -> left && right
            OR -> left || right
            XOR -> left xor right
        }
    }
}
