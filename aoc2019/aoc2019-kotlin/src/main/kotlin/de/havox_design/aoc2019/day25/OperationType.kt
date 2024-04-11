package de.havox_design.aoc2019.day25

enum class OperationType(val code: String, val argCount: Int, val run: (IntCode, List<Param>) -> Unit) {
    ADD(
        "01",
        3,
        { intCode, params ->
            binaryOperation(intCode, params) { arg1, arg2 -> arg1 + arg2 }
        }
    ),
    MULTIPLY(
        "02",
        3,
        { intCode, params ->
            binaryOperation(intCode, params) { arg1, arg2 -> arg1 * arg2 }
        }
    ),
    INPUT(
        "03",
        1,
        { intCode, params ->
            val solutionIndex = params[0].solutionIndex(intCode)
            val nextInput = intCode.nextInput()

            intCode[solutionIndex] = nextInput
        }
    ),
    OUTPUT(
        "04",
        1,
        { intCode, params ->
            val arg1 = params[0].resolveValue(intCode)

            intCode.output(arg1)
        }
    ),
    JUMP_TRUE(
        "05",
        2,
        { intCode, params ->
            val arg1 = params[0].resolveValue(intCode)
            val arg2 = params[1].resolveValue(intCode)

            if (arg1 != 0L) {
                intCode.instructionPointer = arg2.toInt()
            }
        }
    ),
    JUMP_FALSE(
        "06",
        2,
        { intCode, params ->
            val arg1 = params[0].resolveValue(intCode)
            val arg2 = params[1].resolveValue(intCode)

            if (arg1 == 0L) {
                intCode.instructionPointer = arg2.toInt()
            }
        }
    ),
    LESS_THAN(
        "07",
        3,
        { intCode, params ->
            binaryOperation(intCode, params) { arg1, arg2 -> if (arg1 < arg2) 1 else 0 }
        }
    ),
    EQUALS(
        "08",
        3,
        { intCode, params ->
            binaryOperation(intCode, params) { arg1, arg2 -> if (arg1 == arg2) 1 else 0 }
        }
    ),
    ADJUST_OFFSET(
        "09",
        1,
        { intCode, params ->
            intCode.relativeBase += params[0].resolveValue(intCode).toInt()
        }
    );

    val size = argCount + 1

    companion object {
        fun fromCode(code: String): OperationType =
            entries
                .first { it.code == code }
    }
}

private fun binaryOperation(intCode: IntCode, params: List<Param>, operation: (Long, Long) -> Long) {
    val arg1 = params[0].resolveValue(intCode)
    val arg2 = params[1].resolveValue(intCode)
    val solutionIndex = params[2].solutionIndex(intCode)

    intCode[solutionIndex] = operation(arg1, arg2)
}
