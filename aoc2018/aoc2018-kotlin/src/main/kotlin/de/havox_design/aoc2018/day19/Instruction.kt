package de.havox_design.aoc2018.day19

data class Instruction(val opcode: String, val a: Int, val b: Int, val c: Int) {
    fun invoke(state: RegisterState) {
        state[c] = opcodes[opcode]!!.invoke(state, a, b)
    }
}
