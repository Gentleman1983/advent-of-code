package de.havox_design.aoc2020.day20

import de.havox_design.aoc2020.day20.JurassicJigsaw.Companion.EMPTY
import de.havox_design.aoc2020.day20.JurassicJigsaw.Companion.LITERAL_WHITE
import de.havox_design.aoc2020.day20.JurassicJigsaw.Companion.MONSTER_INDICES
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import de.havox_design.aoc.utils.kotlin.model.positions.plus

class TileData(val id: Long, val data: List<String>) {
    val top = data
        .first()
    val bottom = data
        .last()
    val left = data
        .joinToString(EMPTY) { it.first().toString() }
    val right = data
        .joinToString(EMPTY) { it.last().toString() }

    private val borders =
        setOf(top, bottom, left, right)

    fun isCorner(allTiles: List<TileData>) =
        borders.count { border -> allTiles.any { it != this && it.hasBorder(border) } } == 2

    private fun hasBorder(border: String) =
        border in borders || border.reversed() in borders

    fun seaMonsterCount(): Int =
        data
            .mapIndexed { y, line -> line.indices.count { x -> isMonster(Position2d(x, y)) } }
            .sum()

    fun seaRoughness() =
        data
            .sumOf { it.count { char -> char == LITERAL_WHITE } } - seaMonsterCount() * MONSTER_INDICES.size

    private fun isMonster(topLeft: Position2d<Int>): Boolean =
        MONSTER_INDICES
            .map { it + topLeft }
            .all { (x, y) -> y in data.indices && x in data[y].indices && data[y][x] == LITERAL_WHITE }

    private fun flip() =
        TileData(id, data.reversed())

    private fun rotate(): TileData {
        val newData = data
            .indices
            .map { y ->
                data
                    .indices
                    .map { x -> data[x][data.size - 1 - y] }
                    .joinToString(EMPTY)
            }

        return TileData(id, newData)
    }

    fun orientations(): List<TileData> {
        val rot1 = this.rotate()
        val rot2 = rot1.rotate()
        val rot3 = rot2.rotate()

        return listOf(this, rot1, rot2, rot3) + listOf(this.flip(), rot1.flip(), rot2.flip(), rot3.flip())
    }

    fun trimBorders(): List<String> =
        data
            .subList(1, data.lastIndex)
            .map { it.substring(1, it.lastIndex) }

    override fun toString() =
        "TileData(id=$id)"
}
