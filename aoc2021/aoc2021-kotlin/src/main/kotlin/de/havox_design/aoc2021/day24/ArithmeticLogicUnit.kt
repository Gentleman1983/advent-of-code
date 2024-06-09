package de.havox_design.aoc2021.day24

class ArithmeticLogicUnit(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        findDiffs()
            .flatMap { (first, second, diff) ->
                val match = DIGITS
                    .filter { it in DIGITS && it + diff in DIGITS }
                    .maxOf { it }
                listOf(first to match, second to match + diff)
            }
            .sortedBy { it.first }
            .map { it.second }
            .joinToString("")

    fun processPart2(): Any =
        findDiffs()
            .flatMap { (first, second, diff) ->
                val match = DIGITS
                    .filter { it in DIGITS && it + diff in DIGITS }
                    .minOf { it }
                listOf(first to match, second to match + diff)
            }
            .sortedBy { it.first }
            .map { it.second }
            .joinToString("")

    private fun findDiffs(): List<Triple<Int, Int, Int>> {
        val rawOps = data
            .replace("\r", "")
            .split("inp w")
            .filter { it.isNotBlank() }
            .map { block ->
                block
                    .trim()
                    .lines()
                    .map { Operation.parse(it) }
            }
        val pushOut = Operation.DivideOperation(Argument.Register(MAPPING.getValue('z')), Argument.Value(26))

        return buildList {
            val stack = ArrayDeque<Pair<Int, Int>>()

            rawOps
                .forEachIndexed { index, block ->
                    if (pushOut in block) {
                        val diff =
                            block.last { it is Operation.AddOperation && it.a == Argument.Register(MAPPING.getValue('x')) } as Operation.AddOperation
                        val linked = stack.removeLast()

                        add(Triple(linked.first, index, linked.second + (diff.b as Argument.Value).value))
                    } else {
                        val diff =
                            block.last { it is Operation.AddOperation && it.a == Argument.Register(MAPPING.getValue('y')) } as Operation.AddOperation

                        stack.addLast(index to (diff.b as Argument.Value).value)
                    }
                }
        }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private val DIGITS = 1..9
        val MAPPING =
            listOf(
                'w',
                'x',
                'y',
                'z'
            )
                .withIndex()
                .associate { it.value to it.index }
    }
}
