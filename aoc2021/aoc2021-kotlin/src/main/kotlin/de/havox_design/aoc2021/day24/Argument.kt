package de.havox_design.aoc2021.day24

sealed interface Argument {
    fun getValue(regs: IntArray): Int

    data class Value(val value: Int) : Argument {
        override fun getValue(regs: IntArray): Int =
            value
    }

    data class Register(val reg: Int) : Argument {
        override fun getValue(regs: IntArray): Int =
            regs[reg]
    }
}