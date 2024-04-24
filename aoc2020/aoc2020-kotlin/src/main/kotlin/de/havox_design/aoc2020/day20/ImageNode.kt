package de.havox_design.aoc2020.day20

import de.havox_design.aoc2020.day20.JurassicJigsaw.Companion.EMPTY

class ImageNode(val tileData: TileData) {
    val id = tileData.id
    var left: ImageNode? = null
    var right: ImageNode? = null
    var top: ImageNode? = null
    var bottom: ImageNode? = null

    fun linkTop(tiles: List<TileData>): ImageNode? {
        val link = tiles
            .firstOrNull { tileData.top == it.bottom }

        return link
            ?.let {
                val node = ImageNode(it)

                linkTop(node)
                node
            }
    }

    fun linkTop(node: ImageNode?) {
        assert(node == null || node.tileData.bottom == tileData.top)
        this.top = node
        node?.bottom = this
    }

    fun linkBottom(tiles: List<TileData>): ImageNode? {
        val link = tiles
            .firstOrNull { tileData.bottom == it.top }

        return link
            ?.let {
                val node = ImageNode(it)

                linkBottom(node)
                node
            }
    }

    fun linkBottom(node: ImageNode?) {
        assert(node == null || node.tileData.top == tileData.bottom)
        this.bottom = node
        node?.top = this
    }

    fun linkLeft(tiles: List<TileData>): ImageNode? {
        val link = tiles
            .firstOrNull { tileData.left == it.right }

        return link
            ?.let {
                val node = ImageNode(it)

                left = node
                node.right = this
                node
            }
    }

    fun linkRight(tiles: List<TileData>): ImageNode? {
        val link = tiles
            .firstOrNull { tileData.right == it.left }

        return link
            ?.let {
                val node = ImageNode(it)

                right = node
                node.left = this
                node
            }
    }

    fun combine(): List<String> {
        val newData = tileData.trimBorders()
        val rowData = if (right != null) {
            newData.zip(right!!.combine()) { a, b -> a + b }
        } else {
            newData
        }

        return if (left == null && bottom != null) {
            rowData + bottom!!.combine()
        } else {
            rowData
        }
    }

    override fun toString(): String {
        val right = if (right != null) {
            "->$right"
        } else {
            EMPTY
        }
        val bottom = if (left == null && bottom != null) {
            "\n$bottom"
        } else {
            EMPTY
        }

        return "[$id]$right$bottom"
    }
}
