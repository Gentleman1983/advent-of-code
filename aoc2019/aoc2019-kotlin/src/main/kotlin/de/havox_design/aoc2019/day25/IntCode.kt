package de.havox_design.aoc2019.day25

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class IntCode(
    originalProgram: List<Long>,
    private val inputs: BlockingQueue<Long> = LinkedBlockingQueue(),
    val outputs: BlockingQueue<Long> = LinkedBlockingQueue(),
    val inputNotifier: () -> Unit = {}
) {
    private var memory = originalProgram.toMutableList()
    private var executionDuration = 0
    var instructionPointer = 0
    var relativeBase = 0

    val running: Boolean
        get() = memory[instructionPointer] != 99L

    fun runProgram() {
        instructionPointer = 0

        var instruction = memory[instructionPointer]

        while (instruction != 99L) {
            instruction = runInstruction(instruction)
        }
    }

    fun runUtilInput() {
        var instruction = memory[instructionPointer]

        while (instruction != 99L && !(instruction.toString().endsWith("3") && inputs.isEmpty())) {
            instruction = runInstruction(instruction)
        }
    }

    private fun runInstruction(instruction: Long): Long {
        executionDuration += 1

        val initialInstructionPointer = instructionPointer
        val paddedInstruction = instruction.toString().padStart(5, '0')
        val opCode = paddedInstruction.substring(3, 5)
        val operation = OperationType.fromCode(opCode)
        val params = (0 until operation.argCount).map { paramIndex ->
            val operationIndex = 2 - paramIndex
            val value = paddedInstruction.substring(operationIndex..operationIndex).toInt()
            val type = ArgMode.entries[value]
            Param(type, memory[instructionPointer + paramIndex + 1])
        }

        operation.run(this, params)

        if (instructionPointer == initialInstructionPointer) {
            instructionPointer += operation.size
        }

        return memory[instructionPointer]
    }

    fun output(value: Long) {
        outputs += value
    }

    fun nextInput(): Long {
        return inputs
            .ifEmpty {
                inputNotifier()
                inputs
            }
            .take()
    }

    operator fun set(index: Int, value: Long) {
        if (index !in memory.indices) {
            resizeMemory(index)
        }

        memory[index] = value
    }

    operator fun get(index: Int): Long {
        if (index !in memory.indices) {
            resizeMemory(index)
        }

        return memory[index]
    }

    private fun resizeMemory(maxIndex: Int) {
        val currentMemory = memory

        memory = MutableList(maxIndex + 1) { 0L }
        currentMemory.forEachIndexed { index, value ->
            memory[index] = value
        }
    }

    fun sendAscii(commands: Collection<String>) {
        commands.forEach { sendAscii(it) }
    }

    fun sendAscii(command: String) {
        command.forEach { char -> inputs.add(char.code.toLong()) }
        inputs.add(10L)
    }
}
