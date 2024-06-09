package de.havox_design.aoc2021.day24

import de.havox_design.aoc2021.day24.ArithmeticLogicUnit.Companion.MAPPING

sealed interface Operation {
    fun apply(regs: IntArray, input: IntArray, pos: Int)

    data class InputOperation(val reg: Int) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[reg] = input[pos]
        }
    }

    data class AddOperation(val a: Argument, val b: Argument) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[(a as Argument.Register).reg] = a.getValue(regs) + b.getValue(regs)
        }
    }

    data class MultiplyOperation(val a: Argument, val b: Argument) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[(a as Argument.Register).reg] = a.getValue(regs) * b.getValue(regs)
        }
    }

    data class DivideOperation(val a: Argument, val b: Argument) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[(a as Argument.Register).reg] = a.getValue(regs) / b.getValue(regs)
        }
    }

    data class ModuloOperation(val a: Argument, val b: Argument) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[(a as Argument.Register).reg] =
                ((a.getValue(regs) % b.getValue(regs)) + b.getValue(regs)) % b.getValue(regs)
        }
    }

    data class EqualityOperation(val a: Argument, val b: Argument) : Operation {
        override fun apply(regs: IntArray, input: IntArray, pos: Int) {
            regs[(a as Argument.Register).reg] = if (a.getValue(regs) == b.getValue(regs)) 1 else 0
        }
    }

    companion object {
        fun parse(raw: String): Operation {
            val tokens = raw.split(" ")
            return when (tokens[0]) {
                "inp" -> InputOperation(MAPPING.getValue(tokens[1].first()))
                "add" -> AddOperation(parseArgument(tokens[1]), parseArgument(tokens[2]))
                "mul" -> MultiplyOperation(parseArgument(tokens[1]), parseArgument(tokens[2]))
                "div" -> DivideOperation(parseArgument(tokens[1]), parseArgument(tokens[2]))
                "mod" -> ModuloOperation(parseArgument(tokens[1]), parseArgument(tokens[2]))
                "eql" -> EqualityOperation(parseArgument(tokens[1]), parseArgument(tokens[2]))
                else -> error("Unknown operation \"$raw\"")
            }
        }

        private fun parseArgument(raw: String): Argument =
            try {
                Argument.Value(raw.toInt())
            } catch (_: Exception) {
                Argument.Register(MAPPING.getValue(raw.first()))
            }
    }
}