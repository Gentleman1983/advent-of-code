package de.havox_design.aoc2023.day24

data class Hailstone(val start: Position3d, val velocity: Position3d) {
    private val slope = velocity.y.toDouble() / velocity.x
    private val intersectionYaxis = start.y.toDouble() - slope * start.x
    private fun findCollisionTime(xValue: Double) =
        (xValue - start.x) / velocity.x

    private fun f(time: Long) =
        Position3d(start.x + velocity.x * time, start.y + velocity.y * time, start.z + velocity.z * time)

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

    fun collide3D(other: Hailstone, range: LongRange) =
        range.any { f(it) == other.f(it) }

    fun collide2DByCalculatingT(other: Hailstone, range: LongRange): Boolean {
        println("considering $this and $other")
        val x = start.x.toDouble()
        val y = start.y.toDouble()
        val f = velocity.x.toDouble()
        val g = velocity.y.toDouble()
        val v = other.start.x.toDouble()
        val w = other.start.y.toDouble()
        val k = other.velocity.x.toDouble()
        val l = other.velocity.y.toDouble()

        val q = (y * g * f + g * f * g * (v / f) - g * f * g * (x / f) - v * g * f) / (g * f * l - k)
        val t = (v + k * q - x) / f
        val xCross1 = x + f * t
        val xCross2 = v + k * q
        val yCross1 = y + g * t
        val yCross2 = w + l * q
        assert(xCross1 == xCross2) { "Error calculating x crossing point $xCross1 $xCross2 delta = ${xCross1 - xCross2}" }
        assert(yCross1 == yCross2) { "Error calculating y crossing point $yCross1 $yCross2 delta = ${yCross1 - yCross2}" }
        return xCross1.toLong() in range && yCross1.toLong() in range
    }
}
