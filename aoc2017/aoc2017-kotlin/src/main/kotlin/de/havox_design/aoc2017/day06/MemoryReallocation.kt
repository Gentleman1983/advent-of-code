package de.havox_design.aoc2017.day06

class MemoryReallocation(private var filename: String) {
    private val data = getResourceAsText(filename)
    private val numbers = data
        .map(String::toInt)
    private val distributions = getDistributions()

    fun processPart1(): Any =
        distributions
            .first

    fun processPart2(): Any =
        distributions
            .second

    private fun getDistributions(): Pair<Int, Int> {
        var order = 0
        val blocks = numbers
            .toMutableList()
        val distributions = hashMapOf<String, Int>()

        do {
            val (start, value) = blocks
                .withIndex()
                .maxBy { (_, num) -> num }

            distributions[blocks.toString()] = order++

            blocks[start] = 0
            value
                .distribute(blocks.size)
                .forEachIndexed { index, distribution ->
                    blocks[(start + index + 1) % blocks.size] += distribution
                }
        } while (blocks.toString() !in distributions)

        return distributions.size to distributions.size - distributions.getValue(blocks.toString())
    }

    private fun Int.distribute(size: Int): List<Int> {
        val full = this / size
        val remainder = this % size

        return List(size) { full + if (it < remainder) 1 else 0 }
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLine()!!
            .split("""\s""".toRegex())
}
