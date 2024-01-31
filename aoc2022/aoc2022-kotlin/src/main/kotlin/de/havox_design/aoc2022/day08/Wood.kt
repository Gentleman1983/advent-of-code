package de.havox_design.aoc2022.day08

import de.havox_design.aoc.utils.kotlin.model.directions.GeoDirection

class Wood {
    private var rows = emptyList<List<Tree>>().toMutableList()

    fun addRow(row: List<Tree>) {
        rows += row
    }

    fun addRow(row: String) {
        val trees = emptyList<Tree>().toMutableList()

        for (index in row.indices) {
            trees += Tree(row.substring(index, index + 1).toInt())
        }

        addRow(trees)
    }

    fun getRows(): Int = rows.size
    fun getCols(): Int =
        if (rows.isEmpty()) {
            0
        } else {
            rows[0].size
        }

    fun getTree(row: Int, col: Int): Tree = rows[row][col]

    fun calculateScenicScores() {
        for(rowIndex in rows.indices) {
            for(colIndex in rows[rowIndex].indices) {
                getTree(rowIndex, colIndex).scenicScore = calculateScenicScoreOfTree(rowIndex, colIndex)
            }
        }
    }

    fun calculateScenicScoreOfTree(row: Int, col: Int): Int =
        calculateScenicScoreOfTreeInDirection(row, col, GeoDirection.NORTH) *
                calculateScenicScoreOfTreeInDirection(row, col, GeoDirection.EAST) *
                calculateScenicScoreOfTreeInDirection(row, col, GeoDirection.SOUTH) *
                calculateScenicScoreOfTreeInDirection(row, col, GeoDirection.WEST)

    fun calculateScenicScoreOfTreeInDirection(row: Int, col: Int, direction: GeoDirection): Int =
        when (direction) {
            GeoDirection.NORTH -> calculateScenicScoreFromColumn(row, col, 0, row, true)
            GeoDirection.SOUTH -> calculateScenicScoreFromColumn(row, col, row + 1, getRows())
            GeoDirection.WEST -> calculateScenicScoreFromRow(row, col, 0, col, true)
            GeoDirection.EAST -> calculateScenicScoreFromRow(row, col, col + 1, getCols())
            GeoDirection.NONE -> throw IllegalStateException("This should never happen...")
        }

    fun isTreeVisible(row: Int, col: Int): Boolean =
        isTreeVisibleFrom(row, col, GeoDirection.NORTH) ||
                isTreeVisibleFrom(row, col, GeoDirection.EAST) ||
                isTreeVisibleFrom(row, col, GeoDirection.SOUTH) ||
                isTreeVisibleFrom(row, col, GeoDirection.WEST)

    fun isTreeVisibleFrom(row: Int, col: Int, direction: GeoDirection): Boolean =
        when (direction) {
            GeoDirection.NORTH -> isTreeVisibleFromColumn(row, col, 0, row)
            GeoDirection.SOUTH -> isTreeVisibleFromColumn(row, col, row + 1, getRows())
            GeoDirection.WEST -> isTreeVisibleFromRow(row, col, 0, col)
            GeoDirection.EAST -> isTreeVisibleFromRow(row, col, col + 1, getCols())
            GeoDirection.NONE -> throw IllegalStateException("This should never happen...")
        }

    private fun isTreeVisibleFromColumn(row: Int, col: Int, start: Int, end: Int): Boolean {
        var visible = true

        for (index in start until end) {
            visible = getTree(index, col).height < getTree(row, col).height && visible
        }

        return visible
    }

    private fun isTreeVisibleFromRow(row: Int, col: Int, start: Int, end: Int): Boolean {
        var visible = true

        for (index in start until end) {
            visible = getTree(row, index).height < getTree(row, col).height && visible
        }

        return visible
    }

    private fun calculateScenicScoreFromColumn(
        row: Int,
        col: Int,
        start: Int,
        end: Int,
        reverseIndices: Boolean = false
    ): Int {
        val limit = getTree(row, col).height
        var scenicScore = 0

        for (index in start until end) {
            val currentTreeHeight = if (reverseIndices) {
                getTree(end - index - 1, col).height
            } else {
                getTree(index, col).height
            }

            scenicScore++
            if (currentTreeHeight >= limit) {
                break
            }
        }

        return scenicScore
    }

    private fun calculateScenicScoreFromRow(
        row: Int,
        col: Int,
        start: Int,
        end: Int,
        reverseIndices: Boolean = false
    ): Int {
        val limit = getTree(row, col).height
        var scenicScore = 0

        for (index in start until end) {
            val currentTreeHeight = if (reverseIndices) {
                getTree(row, end - index - 1).height
            } else {
                getTree(row, index).height
            }

            scenicScore++
            if (currentTreeHeight >= limit) {
                break
            }
        }

        return scenicScore
    }
}
