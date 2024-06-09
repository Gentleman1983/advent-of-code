package de.havox_design.aoc2019.day13

class CarePackagePart2(private var filename: String) {
    private val data = parseToIntcode(filename)

    fun processPart1(): Any {
        val computer = Computer(data)

        computer.execute()

        val outputs = computer.outputs()
        val size = outputs.size / 3

        return (0 until size)
            .map { outputs[it * 3 + 2] }
            .count { it.isBlock() }
    }

    fun processPart2(): Any {
        val game = Game()

        return game.play(data)
    }

    private fun parseToIntcode(path: String): Intcode =
        getResourceAsText(path)
            .first()
            .split(",")
            .withIndex()
            .associateTo(mutableMapOf())
            {
                it.index to it.value.toLong()
            }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
