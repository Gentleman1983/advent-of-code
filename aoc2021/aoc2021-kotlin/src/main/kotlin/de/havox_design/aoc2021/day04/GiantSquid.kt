package de.havox_design.aoc2021.day04

class GiantSquid(private var filename: String) {
    private val ELEMENT_DELIMITER = ","
    private val ID_FIRST_BOARD_ROW = 2
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        getWinningBoard(buildBoards(), parseCalledNumbers()).score()

    fun processPart2(): Any {
        val boards = buildBoards()
            .toMutableList()

        return getLastBoard(boards, parseCalledNumbers()).score()
    }

    private fun buildBoards() =
        buildBoards(data.subList(ID_FIRST_BOARD_ROW, data.size))

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

    private tailrec fun getLastBoard(boards: List<Board>, calledNumbers: Iterator<Int>): Board {
        return if (boards.size == 1 && boards.first().hasWon()) {
            boards.first()
        } else {
            val calledNumber = calledNumbers.next()

            getLastBoard(boards.filter { !it.hasWon() }.onEach { it.mark(calledNumber) }, calledNumbers)
        }
    }

    private fun parseRow(row: String, y: Int) =
        row
            .chunked(3)
            .mapIndexed { x, value -> Pair(value.trim().toInt(), BingoCell(x, y)) }

    private fun parseCalledNumbers() =
        data
            .first()
            .split(ELEMENT_DELIMITER)
            .map { it.toInt() }
            .iterator()

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
