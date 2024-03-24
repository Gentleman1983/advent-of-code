package de.havox_design.aoc2019.day13

class Computer(private val program: Intcode, var inputSupplier: ((Int) -> Long)? = null, val resumeOnOutput: Boolean = false) {
    private val state = ComputerState()


    fun execute() {
        while (!program.read(state.counter).isHaltOpcode() && !state.pause) {
            val opcode = program.read(state.counter)
            when {
                opcode.isAddOpCode() -> addCommand(opcode.opCodePositions())
                opcode.isMulOpCode() -> mulCommand(opcode.opCodePositions())
                opcode.isInputOpCode() -> inputCommand(opcode.opCodePositions()[0])
                opcode.isOutputOpCode() -> outputCommand(opcode.opCodePositions()[0])
                opcode.isJumpIfTrueOpCode() -> jumpIfTrue(opcode.opCodePositions())
                opcode.isJumpIfFalseOpCode() -> jumpIfFalse(opcode.opCodePositions())
                opcode.isLessThanOpCode() -> isLessThan(opcode.opCodePositions())
                opcode.isEqualsOpCode() -> isEquals(opcode.opCodePositions())
                opcode.isAdjustRelativeAddress() -> adjustRelativeAddress(opcode.opCodePositions())
                else -> throw IllegalArgumentException(opcode.opCodePositions())
            }
        }
        if (program.read(state.counter).isHaltOpcode())
            state.isReady = true
    }


    private fun Long.opCodePositions() = this.toString().padStart(5, '0').substring(0, 3).reversed()

    private fun Long.isHaltOpcode() = this == 99L
    private fun Long.isAddOpCode() = this.toString().endsWith("1")
    private fun Long.isMulOpCode() = this.toString().endsWith("2")
    private fun Long.isInputOpCode() = this.toString().endsWith("3")
    private fun Long.isOutputOpCode() = this.toString().endsWith("4")
    private fun Long.isJumpIfTrueOpCode() = this.toString().endsWith("5")
    private fun Long.isJumpIfFalseOpCode() = this.toString().endsWith("6")
    private fun Long.isLessThanOpCode() = this.toString().endsWith("7")
    private fun Long.isEqualsOpCode() = this.toString().endsWith("8")
    private fun Long.isAdjustRelativeAddress() = this.toString().endsWith("9")

    private fun addCommand(mode: String) = mathCommand(mode) { o1, o2 -> o1 + o2 }
    private fun mulCommand(mode: String) = mathCommand(mode) { o1, o2 -> o1 * o2 }

    private fun outputCommand(mode: Char) {
        val operand1Address = readValue(state.counter + 1, mode)

        state.addOutput(operand1Address)
        state.increaseCounter(2)
        state.increaseInputCounter()

        if (resumeOnOutput)
            state.pause = true
    }


    private fun inputCommand(mode: Char) {
        if (inputSupplier != null) {
            writeValue(state.counter + 1, inputSupplier!!.invoke(state.inputCounter), mode)
            state.increaseCounter(2)
            state.increaseInputCounter()
        } else
            throw IllegalStateException("Supplier is not specified")
    }


    private fun isEquals(modes: String) = compareCommand(modes) { x, y -> x == y }
    private fun isLessThan(modes: String) = compareCommand(modes) { x, y -> x < y }

    private fun jumpIfFalse(modes: String) = jumpCommand(modes) { it == 0L }
    private fun jumpIfTrue(modes: String) = jumpCommand(modes) { it != 0L }

    private fun mathCommand(modes: String, op: (Long, Long) -> Long) {
        val operand1 = readValue(state.counter + 1, modes[0])
        val operand2 = readValue(state.counter + 2, modes[1])

        val result = op(operand1, operand2)
        writeValue(state.counter + 3, result, modes[2])
        state.increaseCounter(4)
    }

    private fun readValue(pointer: Int, mode: Char): Long {
        val value = program.read(pointer)
        return when (mode) {
            '0' -> program.read(value.toInt())
            '1' -> value
            '2' -> program.read(state.relativeAddressBase + value)
            else -> throw IllegalArgumentException("unsupported mode $mode")
        }
    }

    private fun writeValue(pointer: Int, writeValue: Long, mode: Char) {
        val addressValue = program.read(pointer)
        val address = when (mode) {
            '0' -> addressValue
            '2' -> addressValue + state.relativeAddressBase
            else -> throw IllegalArgumentException("unsupported mode $mode")
        }
        program.write(address.toInt(), writeValue)
    }

    private fun jumpCommand(modes: String, condition: (Long) -> Boolean) {
        val operand1 = readValue(state.counter + 1, modes[0])
        if (condition(operand1)) {
            state.counter = readValue(state.counter + 2, modes[1]).toInt()
        } else
            state.increaseCounter(3)
    }

    private fun adjustRelativeAddress(modes: String) {
        val operand1 = readValue(state.counter + 1, modes[0])

        state.adjustRelativeAddress(operand1.toInt())
        state.increaseCounter(2)

    }

    private fun compareCommand(modes: String, compare: (Long, Long) -> Boolean) {
        val operand1 = readValue(state.counter + 1, modes[0])
        val operand2 = readValue(state.counter + 2, modes[1])

        if (compare(operand1, operand2))
            writeValue(state.counter + 3, 1, modes[2])
        else
            writeValue(state.counter + 3, 0, modes[2])

        state.increaseCounter(4)
    }

    fun isReady() = state.isReady


    fun resume() {
        state.pause = false
        execute()
    }

    fun output() = state.outputs.last()

    fun outputs() = state.outputs
}

fun List<Int>.toIntCode(): Intcode {
    return this.withIndex().associateTo(mutableMapOf()) {
        it.index to it.value.toLong()
    }
}