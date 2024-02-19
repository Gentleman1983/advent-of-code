package de.havox_design.aoc2018.day19

data class InstructionPointer(val binding: Int) {

    private var value = 0

    fun update(registerState: RegisterState): Int {
        value = registerState[binding] + 1
        return value
    }
}
