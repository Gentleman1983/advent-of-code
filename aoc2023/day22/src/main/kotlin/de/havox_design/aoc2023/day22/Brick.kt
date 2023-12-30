package de.havox_design.aoc2023.day22

data class Brick(val startPosition: Triple<Int, Int, Int>, val endPosition: Triple<Int, Int, Int>) {
    val fallen: Brick
        get() =
            Brick(
                Triple(startPosition.x(), startPosition.y(), startPosition.z() - 1),
                Triple(endPosition.x(), endPosition.y(), endPosition.z() - 1)
            )

    val canFall: Boolean
        get() =
            canFall(Day22.getBricks())

    fun contains(position: Triple<Int, Int, Int>): Boolean =
        position.x() >= startPosition.x() && position.x() <= endPosition.x() &&
                position.y() >= startPosition.y() && position.y() <= endPosition.y() &&
                position.z() >= startPosition.z() && position.z() <= endPosition.z()

    @SuppressWarnings("kotlin:S3776")
    fun intersect(other: Brick): Boolean {
        return when {
            other.startPosition.z() > endPosition.z() || other.endPosition.z() < startPosition.z() -> false
            else -> {
                for (x in other.startPosition.x()..other.endPosition.x()) {
                    for (y in other.startPosition.y()..other.endPosition.y()) {
                        for (z in other.startPosition.z()..other.endPosition.z()) {
                            when {
                                contains(Triple(x, y, z)) -> return true
                            }
                        }
                    }
                }
                false
            }
        }
    }

    fun canFall(bricks: ArrayList<Brick>): Boolean {
        val f = fallen

        for (brick in bricks) {
            when {
                brick != this && brick.intersect(f) -> return false
            }
        }

        return startPosition.third > 1
    }
}

private fun Triple<Int, Any, Any>.x(): Int =
    this.first

private fun Triple<Any, Int, Any>.y(): Int =
    this.second

private fun Triple<Any, Any, Int>.z(): Int =
    this.third
