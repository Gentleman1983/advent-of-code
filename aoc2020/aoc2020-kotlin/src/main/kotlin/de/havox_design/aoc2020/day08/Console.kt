package de.havox_design.aoc2020.day08

import de.havox_design.aoc2020.day08.Instruction.Companion.ICON_JUMP
import de.havox_design.aoc2020.day08.Instruction.Companion.ICON_NO_OPERATION

class Console(private val instructions: List<Instruction>) {
    var ip: Int = 0
    var accumulator = 0

    fun processPart1(): Int {
        run()

        return accumulator
    }

    fun processPart2(): Int {
        val jumps = instructions
            .filter { it.name == ICON_JUMP }
        val nops = instructions
            .filter { it.name == ICON_NO_OPERATION }

        jumps
            .forEach {
                it.name = ICON_NO_OPERATION
                if (run() == Status.TERMINATE) {
                    return@processPart2 accumulator
                }
                it.name = ICON_JUMP
            }

        nops
            .forEach {
                it.name = ICON_JUMP
                if (run() == Status.TERMINATE) {
                    return@processPart2 accumulator
                }
                it.name = ICON_NO_OPERATION
            }

        throw IllegalStateException("No answer")
    }

    private fun run(): Status {
        accumulator = 0
        ip = 0

        val seen = mutableSetOf<Int>()
        while (ip !in seen && ip in instructions.indices) {
            seen.add(ip)

            val next = instructions[ip]

            next.apply(this)
        }

        return if (ip in instructions.indices) {
            Status.LOOP
        } else {
            Status.TERMINATE
        }
    }

    companion object {
        fun of(lines: List<String>): Console =
            Console(lines.map(Instruction::of))
    }
}
