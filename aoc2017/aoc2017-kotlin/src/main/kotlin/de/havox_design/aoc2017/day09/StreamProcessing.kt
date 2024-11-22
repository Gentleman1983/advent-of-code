package de.havox_design.aoc2017.day09

class StreamProcessing(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val stream = data
            .replace(Regex("""[^{}<>!]|!."""), "")
            .replace(Regex("""<.*?>"""), "")

        require(stream.count { it == '{' } == stream.count { it == '}' }) {
            "Invalid stream."
        }

        val queue = stream
            .toMutableList()
        var score = 0
        var depth = 0

        while (queue.isNotEmpty()) {
            when (queue.removeFirst()) {
                '{' -> depth++
                '}' -> score += depth--
            }
        }

        return score
    }

    fun processPart2(): Any {
        val stream = data
            .replace(Regex("""!."""), "")

        return """<(.*?)>"""
            .toRegex()
            .findAll(stream)
            .sumOf { it.groupValues[1].length }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
