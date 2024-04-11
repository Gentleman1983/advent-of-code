package de.havox_design.aoc.utils.kotlin.model.directions

enum class GeoDirection() {
    NORTH {
        override val opposite: GeoDirection
            get() = SOUTH
    },
    SOUTH {
        override val opposite: GeoDirection
            get() = NORTH
    },
    WEST {
        override val opposite: GeoDirection
            get() = EAST
    },
    EAST {
        override val opposite: GeoDirection
            get() = WEST
    },
    NONE {
        override val opposite: GeoDirection
            get() = NONE
    };

    abstract val opposite: GeoDirection
}