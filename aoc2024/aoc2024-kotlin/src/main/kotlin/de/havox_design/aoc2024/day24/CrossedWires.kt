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

    @SuppressWarnings("kotlin:S1481", "kotlin:S3776")
    fun processPart2(): Any {
        val formulas = data
            .second
        val formulaLookup = formulas
            .entries
            .flatMap { (key, value) ->
                listOf(value to key, value.copy(left = value.right, right = value.left) to key)
            }
            .toMap()
        val swapped = mutableListOf<String>()
        var carry = ""
        var cright: String?
        var nextCarry = ""
        var sum: String

        (0..<45)
            .forEach { num ->
                val suffix = num
                    .toString()
                    .padStart(2, IDENTIFIER_LEADING_ZERO)
                var s1 = formulaLookup[Formula(
                    "${IDENTIFIER_INPUT1_WIRES}${suffix}",
                    "${IDENTIFIER_INPUT2_WIRES}${suffix}",
                    Operation.XOR
                )] ?: error("No s1 for $num")
                var cleft = formulaLookup[Formula(
                    "${IDENTIFIER_INPUT1_WIRES}${suffix}",
                    "${IDENTIFIER_INPUT2_WIRES}${suffix}",
                    Operation.AND
                )] ?: error("No cleft for $num")

                if (carry.isNotBlank()) {
                    cright = formulaLookup[Formula(carry, s1, Operation.AND)]

                    if (cright == null) {
                        swap(s1, cleft, swapped)
                            .also { (a, b) ->
                                cleft = a
                                s1 = b
                            }

                        cright = formulaLookup[Formula(carry, s1, Operation.AND)] ?: error("No cright for $num")
                    }

                    sum = formulaLookup[Formula(s1, carry, Operation.XOR)] ?: error("No sum for $num")

                    if (s1.startsWith(IDENTIFIER_SOLUTION_WIRES)) {
                        swap(s1, sum, swapped)
                            .also { (a, b) ->
                                sum = a
                                s1 = b
                            }
                    }

                    if (cleft.startsWith(IDENTIFIER_SOLUTION_WIRES)) {
                        swap(cleft, sum, swapped)
                            .also { (a, b) ->
                                sum = a
                                cleft = b
                            }
                    }

                    if (cright!!.startsWith(IDENTIFIER_SOLUTION_WIRES)) {
                        swap(cright!!, sum, swapped)
                            .also { (a, b) ->
                                sum = a
                                cright = b
                            }
                    }

                    nextCarry = formulaLookup[Formula(cright!!, cleft, Operation.OR)] ?: error("No nextCarry for $num")

                    if (nextCarry.startsWith(IDENTIFIER_SOLUTION_WIRES) && nextCarry != IDENTIFIER_MAX_SOLUTION_WIRE) {
                        swap(nextCarry, sum, swapped)
                            .also { (a, b) ->
                                sum = a
                                nextCarry = b
                            }
                    }
                }

                carry = if (carry.isBlank()) {
                    cleft
                } else {
                    nextCarry
                }
            }

        return swapped
            .sorted()
            .joinToString(DELIMITER_ANSWER)
    }

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

    private fun swap(a: String, b: String, swapped: MutableList<String>): Pair<String, String> {
        swapped.add(a)
        swapped.add(b)

        return a to b
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

        private const val IDENTIFIER_INPUT1_WIRES = "x"
        private const val IDENTIFIER_INPUT2_WIRES = "y"
        private const val IDENTIFIER_LEADING_ZERO = '0'
        private const val IDENTIFIER_MAX_SOLUTION_WIRE = "z45"
        private const val IDENTIFIER_SOLUTION_WIRES = "z"

        private const val DELIMITER_ANSWER = ","
    }
}

private fun Boolean.toLong() =
    if (this) {
        1L
    } else {
        0L
    }
