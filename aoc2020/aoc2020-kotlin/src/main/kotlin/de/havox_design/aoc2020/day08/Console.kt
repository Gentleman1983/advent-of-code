package de.havox_design.aoc2020.day08

class Console(private val instructions: List<Instruction>) {
    var ip: Int = 0
    var accumulator = 0

    fun processPart1(): Int {
        run()

        return accumulator
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
