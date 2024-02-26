package de.havox_design.aoc2023.day13

data class Pattern(val rows: List<String>) {
    private val ICON_ASH = '.'
    private val ICON_ROCK = '#'
    private val numColumns = rows[0].length

    override fun toString(): String {
        return rows
            .joinToString("\n")
    }

    private fun column(index: Int): String {
        return rows
            .map { it[index] }
            .joinToString("")
    }

    fun mirrorValue(pleaseDoNotBe: Int = 0): Int {
        val rowScores = (0..<rows.lastIndex)
            .filter(::isMirrorRow)
            .map { (it + 1) * 100 }
        val columnScores = (0..<numColumns)
            .filter(::isMirrorColumn)
            .map { it + 1 }

        return (rowScores + columnScores + 0)
            .first { it != pleaseDoNotBe }
    }

    fun smudgeMirrorValue(): Int {
        val originalValue = mirrorValue()

        for (rowIndex in rows.indices) {
            for (columnIndex in rows[0].indices) {
                val pattern = Pattern(rows.flipMirror(rowIndex, columnIndex))
                val newValue = pattern.mirrorValue(originalValue)

                when {
                    newValue != 0 && newValue != originalValue -> {
                        return newValue
                    }
                }
            }
        }
        return originalValue
    }

    @SuppressWarnings("kotlin:S6510")
    private fun isMirrorRow(index: Int): Boolean {
        var top = index
        var bottom = index + 1

        when {
            bottom > rows.lastIndex -> return false
            else -> {
                while (top >= 0 && bottom <= rows.lastIndex) {
                    when {
                        rows[top] != rows[bottom] -> {
                            return false
                        }

                        else -> {
                            top--
                            bottom++
                        }
                    }
                }
                return true
            }
        }
    }

    @SuppressWarnings("kotlin:S6510")
    private fun isMirrorColumn(index: Int): Boolean {
        var left = index
        var right = index + 1

        when (right) {
            numColumns -> return false
            else -> {
                while (left >= 0 && right < numColumns) {
                    when {
                        column(left) != column(right) -> {
                            return false
                        }

                        else -> {
                            left--
                            right++
                        }
                    }
                }
                return true
            }
        }
    }

    private fun List<String>.flipMirror(rowIndex: Int, columnIndex: Int): List<String> {
        val mutableList = this.toMutableList()
        val rowToReplace = mutableList[rowIndex].toMutableList()
        rowToReplace[columnIndex] = when {
            rowToReplace[columnIndex] == ICON_ASH -> ICON_ROCK
            else -> ICON_ASH
        }
        mutableList[rowIndex] = rowToReplace.joinToString("")

        return mutableList.toList()
    }
}