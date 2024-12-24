package de.havox_design.aoc2024.day24

class CrossedWires(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any {
        val (assignments, formulas) = data
        val current = assignments
            .toMutableMap()
        val zValues = formulas
            .filter { formula ->
                formula
                    .key
                    .startsWith(IDENTIFIER_SOLUTION_WIRES)
            }
            .map { it.key to getValue(it.key, current, assignments, formulas) }
            .map { (key, value) ->
                val index = key
                    .drop(1)
                    .toInt()

                value.toLong() shl index
            }
            .fold(0L) { acc, value ->
                acc or value
            }

        return zValues
    }

    fun processPart2(): Any =
        0L

    @SuppressWarnings("kotlin:S6611")
    private fun getValue(
        key: String,
        current: MutableMap<String, Boolean>,
        assignments: Map<String, Boolean>,
        formulas: Map<String, Formula>
    ): Boolean =
        current
            .getOrPut(key) {
                if (key in assignments) {
                    assignments[key]!!
                }

                val formula = formulas
                    .getValue(key)

                formula
                    .operation
                    .doOperation(
                        getValue(formula.left, current, assignments, formulas),
                        getValue(formula.right, current, assignments, formulas)
                    )
            }

    private fun parseInput(input: List<String>): Pair<Map<String, Boolean>, Map<String, Formula>> {
        val assignments = input
            .takeWhile { row ->
                row
                    .isNotBlank()
            }
            .associate { line ->
                val (l, r) = WIRE_REGEX
                    .find(line)!!
                    .destructured

                l to (r.toInt() == 1)
            }

        val formulas = input
            .dropWhile { row ->
                row
                    .isNotBlank()
            }
            .drop(1)
            .associate {
                val (l1, op, l2, r, _) = CONNECTION_REGEX
                    .find(it)!!
                    .destructured

                r to Formula(l1, l2, Operation.valueOf(op.uppercase()))
            }

        return assignments to formulas
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private val CONNECTION_REGEX = Regex("""(.+) (AND|XOR|OR) (.+) -> (.+)""")
        private val WIRE_REGEX = Regex("""(.+): (\d)""")

        private const val IDENTIFIER_SOLUTION_WIRES = "z"
    }
}

private fun Boolean.toLong() =
    if (this) {
        1L
    } else {
        0L
    }
