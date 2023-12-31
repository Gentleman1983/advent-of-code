package de.havox_design.aoc2023.day24

data class Hailstone(val start: Position3d, val velocity: Position3d) {
    private val slope = velocity.y.toDouble() / velocity.x
    private val intersectionYaxis = start.y.toDouble() - slope * start.x
    private fun findCollisionTime(xValue: Double) =
        (xValue - start.x) / velocity.x

    fun collide2D(other: Hailstone, range: LongRange): Boolean =
        when (this.slope) {
            other.slope -> false
            else -> calculate2DCollision(other, range)
        }

    private fun calculate2DCollision(other: Hailstone, range: LongRange): Boolean {
        val xIntersect = (other.intersectionYaxis - intersectionYaxis) / (slope - other.slope)
        val yIntersect = slope * xIntersect + intersectionYaxis
        val thisTime = findCollisionTime(xIntersect)
        val otherTime = other.findCollisionTime(xIntersect)

        return when {
            thisTime < 0 || otherTime < 0 -> false
            else -> xIntersect.toLong() in range && yIntersect.toLong() in range
        }
    }
}
