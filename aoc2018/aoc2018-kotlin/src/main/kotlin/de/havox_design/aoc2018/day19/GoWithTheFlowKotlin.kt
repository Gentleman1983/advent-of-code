package de.havox_design.aoc2018.day19

class GoWithTheFlowKotlin(private var filename: String) {
    private val range = (1..300)

    fun processTask1(): Any =
        calculateValueOnRegister0()

    fun processTask2(): Any =
        calculateValueOnRegister0(mutableListOf(1, 0, 0, 0, 0, 0))

    private fun calculateValueOnRegister0(registerState: RegisterState = mutableListOf(0, 0, 0, 0, 0, 0)): Int {
        val input = getResourceAsText(filename)
        val isPartTwo = registerState[0] == 1
        val pointer = InstructionPointer("#ip (\\d+)".parseFor(input[0]) { (i) -> i.toInt() })
        val instructions = input.drop(1).map {
            "([^ ]+) (\\d+) (\\d+) (\\d+)"
                .parseFor(it) { (opcode, a, b, c) ->
                    Instruction(opcode, a.toInt(), b.toInt(), c.toInt())
                }
        }
        var next = registerState[pointer.binding]

        @SuppressWarnings("kotlin:S6615")
        var target = 0

        while (next in instructions.indices) {
            registerState[pointer.binding] = next
            instructions[next].invoke(registerState)
            if (isPartTwo && registerState.drop(1).all { it > 0 }) {
                target = registerState.max()
                break
            }
            next = pointer.update(registerState)
        }

        if (isPartTwo) {
            registerState[0] = (1..target)
                .mapNotNull { if (target % it == 0) it else null }
                .sum()
        }

        return registerState[0]
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}

inline fun <T> String.parseFor(
    input: CharSequence,
    mapper: (MatchResult.Destructured) -> T
) =
    (toRegex().matchEntire(input)
        ?: throw IllegalArgumentException("Wrong format."))
        .destructured.let(mapper)
