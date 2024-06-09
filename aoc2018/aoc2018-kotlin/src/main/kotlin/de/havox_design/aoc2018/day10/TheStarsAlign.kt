package de.havox_design.aoc2018.day10

class TheStarsAlign(private var filename: String) {
    private val POSITION = "position=<([^,]+),([^>]+)> velocity=<([^,]+),([^>]+)>"

    fun processTask1(): Any {
        val answer = process(getResourceAsText(filename)).first
        println("The answer for task 1 is:")
        for(line in answer) {
            println(line)
        }

        return answer
    }

    fun processTask2(): Any =
        process(getResourceAsText(filename)).second

    private fun process(input: List<String>): Pair<List<String>, Int> {
        val points = input.parseWith(POSITION)
        { (x, y, xVelocity, yVelocity) -> MovingPoint(x, y, xVelocity, yVelocity) }
        var answer: Boundary? = null

        for (i in generateSequence(1) { it + 1 }) {
            val current = getBoundary(points.map(MovingPoint::forward))
            if (current.getArea() < (answer?.getArea() ?: Long.MAX_VALUE)) {
                answer = current
                continue
            }
            return with(answer!!) {
                Array(getHeight()) {
                    StringBuilder()
                        .apply {
                        append(CharArray(getWidth()) { '.' })
                    }
                }
            }
                .apply {
                points
                    .map { answer.getOffset(it.reverse()) }
                    .forEach { (x, y) -> this[y][x] = '#' }
            }
                .map { it.toString() } to i - 1
        }
        throw IllegalStateException("No results.")
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    private inline fun <T> List<CharSequence>.parseWith(pattern: String, mapper: (MatchResult.Destructured) -> T) =
        map { pattern.parseFor(it, mapper) }

    private inline fun <T> String.parseFor(input: CharSequence, mapper: (MatchResult.Destructured) -> T) =
        (toRegex().matchEntire(input) ?: throw IllegalArgumentException("Wrong format."))
            .destructured
            .let(mapper)
}