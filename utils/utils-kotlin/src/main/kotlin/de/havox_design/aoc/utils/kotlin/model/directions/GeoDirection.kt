package de.havox_design.aoc.utils.kotlin.model.directions

enum class GeoDirection() {
    NORTH {
        override val left: GeoDirection
            get() = WEST
        override val opposite: GeoDirection
            get() = SOUTH
        override val right: GeoDirection
            get() = EAST
    },
    SOUTH {
        override val left: GeoDirection
            get() = EAST
        override val opposite: GeoDirection
            get() = NORTH
        override val right: GeoDirection
            get() = WEST
    },
    WEST {
        override val left: GeoDirection
            get() = SOUTH
        override val opposite: GeoDirection
            get() = EAST
        override val right: GeoDirection
            get() = NORTH
    },
    EAST {
        override val left: GeoDirection
            get() = NORTH
        override val opposite: GeoDirection
            get() = WEST
        override val right: GeoDirection
            get() = SOUTH
    },
    NONE {
        override val left: GeoDirection
            get() = NONE
        override val opposite: GeoDirection
            get() = NONE
        override val right: GeoDirection
            get() = NONE
    };

    abstract val left: GeoDirection
    abstract val opposite: GeoDirection
    abstract val right: GeoDirection

    fun left(times: Int): GeoDirection {
        return if (times == 1) {
            left
        } else {
            left.left(times - 1)
        }
    }

    fun opposite(times: Int): GeoDirection {
        return if (times == 1) {
            opposite
        } else {
            opposite.left(times - 1)
        }
    }

    fun right(times: Int): GeoDirection {
        return if (times == 1) {
            right
        } else {
            right.right(times - 1)
        }
    }
}
