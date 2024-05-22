package de.havox_design.aoc2021.day04

class GiantSquid(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val calledNumbers = data
            .first()
            .split(",")
            .map { it.toInt() }
            .iterator()
        val boards = buildBoards(data.subList(2, data.size))

        return getWinningBoard(boards, calledNumbers).score()
    }

    fun processPart2(): Any =
        0L

    private fun buildBoards(lines: List<String>) =
        lines
            .filter { it.isNotBlank() }
            .chunked(5)
            .map(::buildBoard)

    private fun buildBoard(lists: List<String>): Board {
        return lists.flatMapIndexed { y, row -> parseRow(row, y) }
            .toMap()
            .let(::Board)
    }

    private tailrec fun getWinningBoard(boards: List<Board>, calledNumbers: Iterator<Int>): Board {
        return if (boards.any { it.hasWon() }) {
            boards.first { it.hasWon() }
        } else {
            val calledNumber = calledNumbers.next()

            getWinningBoard(boards.onEach { it.mark(calledNumber) }, calledNumbers)
        }
    }

    private fun parseRow(row: String, y: Int) =
        row
            .chunked(3)
            .mapIndexed { x, value -> Pair(value.trim().toInt(), BingoCell(x, y)) }


    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
