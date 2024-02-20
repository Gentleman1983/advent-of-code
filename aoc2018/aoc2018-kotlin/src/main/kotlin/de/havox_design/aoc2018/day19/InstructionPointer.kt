package de.havox_design.aoc2018.day19

data class InstructionPointer(val binding: Int) {

    private var value = 0

    @SuppressWarnings("kotlin:S6524")
    fun update(registerState: RegisterState): Int {
        value = registerState[binding] + 1

        return value
    }
}
