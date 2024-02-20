package de.havox_design.aoc2018.day19

typealias Opcode = (RegisterState, Int, Int) -> Int

val addr: Opcode = { state, a, b -> state[a] + state[b] }
val addi: Opcode = { state, a, b -> state[a] + b }
val banr: Opcode = { state, a, b -> state[a] and state[b] }
val bani: Opcode = { state, a, b -> state[a] and b }
val borr: Opcode = { state, a, b -> state[a] or state[b] }
val bori: Opcode = { state, a, b -> state[a] or b }
val eqir: Opcode = { state, a, b -> if (a == state[b]) 1 else 0 }
val eqri: Opcode = { state, a, b -> if (state[a] == b) 1 else 0 }
val eqrr: Opcode = { state, a, b -> if (state[a] == state[b]) 1 else 0 }
val gtir: Opcode = { state, a, b -> if (a > state[b]) 1 else 0 }
val gtri: Opcode = { state, a, b -> if (state[a] > b) 1 else 0 }
val gtrr: Opcode = { state, a, b -> if (state[a] > state[b]) 1 else 0 }
val mulr: Opcode = { state, a, b -> state[a] * state[b] }
val muli: Opcode = { state, a, b -> state[a] * b }
val setr: Opcode = { state, a, _ -> state[a] }
val seti: Opcode = { _, a, _ -> a }

val opcodes = mapOf(
    "addr" to addr,
    "addi" to addi,
    "banr" to banr,
    "bani" to bani,
    "borr" to borr,
    "bori" to bori,
    "eqir" to eqir,
    "eqri" to eqri,
    "eqrr" to eqrr,
    "gtir" to gtir,
    "gtri" to gtri,
    "gtrr" to gtrr,
    "mulr" to mulr,
    "muli" to muli,
    "setr" to setr,
    "seti" to seti
)
