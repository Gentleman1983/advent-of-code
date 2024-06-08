package de.havox_design.aoc2020.day20

import de.havox_design.aoc.utils.kotlin.helpers.math.product
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class JurassicJigsaw(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val tiles = buildTileData(data)
        val corners = tiles
            .filter { it.isCorner(tiles) }

        return corners
            .map { it.id }
            .product()
    }

    fun processPart2(): Any {
        val tiles = buildTileData(data)
        val topLeft = buildGrid(tiles)

        val final = TileData(0, topLeft.combine())
            .orientations()
            .first { it.seaMonsterCount() > 0 }

        return final
            .seaRoughness()
    }

    private fun buildTileData(text: String) =
        text
            .replace(CARRIAGE_RETURN, EMPTY)
            .trim()
            .split(DOUBLE_NEWLINE)
            .map { section ->
                val lines = section
                    .lines()
                val id = TILE_REGEX
                    .matchEntire(lines[0])!!
                    .groups[1]!!
                    .value
                    .toLong()

                TileData(id, lines.subList(1, lines.size))
            }

    private fun buildGrid(tiles: List<TileData>): ImageNode {
        var start = ImageNode(tiles.first())
        val remaining = tiles
            .drop(1)
            .flatMap { it.orientations() }
            .toMutableList()

        buildChainRight(start, remaining)
        start = buildChainLeft(start, remaining)

        var current = start

        while (current.linkBottom(remaining) != null) {
            current = current.bottom!!
            remaining.removeIf { it.id == current.id }
            buildChainRight(current, remaining)
        }

        current = start

        while (current.linkTop(remaining) != null) {
            current = current.top!!
            remaining.removeIf { it.id == current.id }
            buildChainRight(current, remaining)
        }

        return current
    }

    private fun buildChainLeft(start: ImageNode, remaining: MutableList<TileData>): ImageNode {
        var above = start.top
        var below = start.bottom
        var current = start

        while (current.linkLeft(remaining) != null) {
            current = current.left!!
            remaining.removeIf { it.id == current.id }
            above = above?.left
            below = below?.left
            current.linkTop(above)
            current.linkBottom(below)
        }

        return current
    }

    private fun buildChainRight(start: ImageNode, remaining: MutableList<TileData>) {
        var above = start.top
        var below = start.bottom
        var current = start

        while (current.linkRight(remaining) != null) {
            current = current.right!!
            remaining.removeIf { it.id == current.id }
            above = above?.right
            below = below?.right
            current.linkTop(above)
            current.linkBottom(below)
        }
    }

    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()

    companion object {
        private const val CARRIAGE_RETURN = "\r"
        private const val DOUBLE_NEWLINE = "\n\n"
        const val EMPTY = ""
        const val LITERAL_WHITE = '#'

        val MONSTER = """
    |                  # 
    |#    ##    ##    ###
    | #  #  #  #  #  #   
  """
            .trimMargin()
        val MONSTER_INDICES = MONSTER
            .lines()
            .flatMapIndexed { y, line ->
                line
                    .withIndex()
                    .filter { (_, value) -> value == LITERAL_WHITE }
                    .map { (x, _) -> Position2d(x, y) }
            }

        private val TILE_REGEX = """Tile (\d+):""".toRegex()
    }
}
