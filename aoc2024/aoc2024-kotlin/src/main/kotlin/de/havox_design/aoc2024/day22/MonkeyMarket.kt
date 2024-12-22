package de.havox_design.aoc2024.day22

class MonkeyMarket(private var filename: String) {
    private val data = parseInput(getResourceAsText(filename))

    fun processPart1(): Any =
        data
            .sumOf { number ->
                (0..<MAX_SIZE)
                    .fold(number) { acc, _ ->
                        acc.nextSecretNumber()
                    }
            }

    fun processPart2(): Any =
        0L

    private fun Long.mixInto(other: Long) =
        this xor other

    private fun Long.prune() =
        this % MODULO_FACTOR

    private fun Long.nextSecretNumber(): Long {
        val step1 =
            (this * 64)
                .mixInto(this)
                .prune()
        val step2 =
            (step1 / 32)
                .mixInto(step1)
                .prune()
        val step3 =
            (step2 * 2048)
                .mixInto(step2)
                .prune()

        return step3
    }

    private fun parseInput(input: List<String>) =
        input
            .map(String::toLong)

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val MAX_SIZE = 2000
        private const val MODULO_FACTOR = 16777216L
    }
}
