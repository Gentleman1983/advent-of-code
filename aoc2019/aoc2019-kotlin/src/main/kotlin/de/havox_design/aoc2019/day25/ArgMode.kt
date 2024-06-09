package de.havox_design.aoc2019.day25

enum class ArgMode(
    val resolveValue: (Long, IntCode) -> Long,
    val resolveSolutionIndex: (Long, IntCode) -> Int
) {
    POSITION(
        { value, intCode -> intCode[value.toInt()] },
        { value, _ -> value.toInt() }
    ),
    IMMEDIATE(
        { value, _ -> value },
        { _, _ -> throw kotlin.IllegalArgumentException() }
    ),
    RELATIVE(
        { value, intCode -> intCode[intCode.relativeBase + value.toInt()] },
        { value, intCode -> value.toInt() + intCode.relativeBase }
    )
}
