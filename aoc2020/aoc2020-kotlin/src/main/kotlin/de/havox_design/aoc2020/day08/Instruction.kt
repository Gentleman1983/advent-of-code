package de.havox_design.aoc2020.day08

data class Instruction(var name: String, val value: Int) {
    fun apply(console: Console) {
        when (name) {
            ICON_ACCUMULATOR -> {
                console.accumulator += value
                console.ip += 1
            }
            ICON_JUMP -> {
                console.ip += value
            }
            ICON_NO_OPERATION -> {
                console.ip += 1
            }
        }
    }

    companion object {
        private const val DELIMITOR_OPERATION_VALUE = " "
        const val ICON_ACCUMULATOR = "acc"
        const val ICON_JUMP = "jmp"
        const val ICON_NO_OPERATION = "nop"

        fun of(line: String): Instruction {
            val (key, value) = line
                .split(DELIMITOR_OPERATION_VALUE)

            return Instruction(key, value.toInt())
        }
    }
}
