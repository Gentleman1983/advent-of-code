package de.havox_design.aoc2024.day17

class ChronospatialComputer(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val state = parseInitialState(data)

        var pc = 0
        while (pc < state.program.size) {
            val opcode = state.program[pc]
            val literalOperand = state.program[pc + 1]
            val comboOperand = lazy {
                when (literalOperand) {
                    in 0..3 -> literalOperand.toLong()
                    4 -> state.a
                    5 -> state.b
                    6 -> state.c
                    else -> throw RuntimeException("Should not reach here")
                }
            }

            when (opcode) {
                OPCODE_ADV -> {
                    state.a = dv(state, comboOperand)
                    pc += 2
                }

                OPCODE_BXL -> {
                    state.b = state.b xor literalOperand.toLong()
                    pc += 2
                }

                OPCODE_BST -> {
                    state.b = comboOperand.value % 8
                    pc += 2
                }

                OPCODE_JNZ -> {
                    pc = if (state.a != 0L) {
                        literalOperand
                    } else {
                        pc + 2
                    }
                }

                OPCODE_BXC -> {
                    state.b = state.b xor state.c
                    pc += 2
                }

                OPCODE_OUT -> {
                    state.output += (comboOperand.value % 8).toInt()
                    pc += 2
                }

                OPCODE_BDV -> {
                    state.b = dv(state, comboOperand)
                    pc += 2
                }

                OPCODE_CDV -> {
                    state.c = dv(state, comboOperand)
                    pc += 2
                }

                else -> throw RuntimeException("Should not reach here")
            }
        }

        return parseOutput(state.output)
    }

    fun processPart2(): Any =
        0L

    private fun dv(state: State, comboOperand: Lazy<Long>) =
        state.a / (1 shl comboOperand.value.toInt())

    private fun parseOutput(output: Output): String =
        output
            .joinToString(separator = ",")

    private fun parseInitialState(input: List<String>): State {
        val registers: List<Register> = input
            .take(3)
            .map { row ->
                row
                    .split(": ")
                    .last()
                    .toLong()
            }
        val program: Program = input
            .last()
            .split(": ")
            .last()
            .split(",")
            .map(String::toInt)

        return State(a = registers[REGISTER_A], b = registers[REGISTER_B], c = registers[REGISTER_C], program = program)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val OPCODE_ADV = 0
        private const val OPCODE_BXL = 1
        private const val OPCODE_BST = 2
        private const val OPCODE_JNZ = 3
        private const val OPCODE_BXC = 4
        private const val OPCODE_OUT = 5
        private const val OPCODE_BDV = 6
        private const val OPCODE_CDV = 7

        private const val REGISTER_A = 0
        private const val REGISTER_B = 1
        private const val REGISTER_C = 2
    }
}
