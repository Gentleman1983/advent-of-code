package de.havox_design.aoc2018.day18

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate
import de.havox_design.aoc2018.day17.InputToken

data class Acre(
    override var point: Coordinate,
    var content: Content
) : InputToken(point) {

    override fun isActive() =
        true

    override fun toString() =
        content
            .output
            .toString()

    fun next(state: Area) =
        when (content) {
            Content.OPEN ->
                if (state.near(this, 3, Content.TREES)) {
                    Content.TREES
                } else {
                    content
                }

            Content.TREES ->
                if (state.near(this, 3, Content.LUMBERYARD)) {
                    Content.LUMBERYARD
                } else {
                    content
                }

            Content.LUMBERYARD ->
                if (
                    state.near(this, 1, Content.LUMBERYARD) &&
                    state.near(this, 1, Content.TREES)
                ) {
                    Content.LUMBERYARD
                } else {
                    Content.OPEN
                }
        }
}
