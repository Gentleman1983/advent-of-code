package de.havox_design.aoc2018.day13;

import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped;

import java.util.Map;

public enum Path {
    VERTICAL(
            Map.of(
                    FourDirectionsFlipped.UP, FourDirectionsFlipped.UP,
                    FourDirectionsFlipped.DOWN, FourDirectionsFlipped.DOWN
            )
    ),
    HORIZONTAL(
            Map.of(
                    FourDirectionsFlipped.LEFT, FourDirectionsFlipped.LEFT,
                    FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.RIGHT
            )
    ),
    CROSSING(
            Map.of(
                    FourDirectionsFlipped.DOWN, FourDirectionsFlipped.DOWN,
                    FourDirectionsFlipped.UP, FourDirectionsFlipped.UP,
                    FourDirectionsFlipped.LEFT, FourDirectionsFlipped.LEFT,
                    FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.RIGHT
            )
    ),
    SW_NE(
            Map.of(
                    FourDirectionsFlipped.UP, FourDirectionsFlipped.RIGHT,
                    FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.UP,
                    FourDirectionsFlipped.LEFT, FourDirectionsFlipped.DOWN,
                    FourDirectionsFlipped.DOWN, FourDirectionsFlipped.LEFT
            )
    ),
    NW_SE(
            Map.of(
                    FourDirectionsFlipped.UP, FourDirectionsFlipped.LEFT,
                    FourDirectionsFlipped.LEFT, FourDirectionsFlipped.UP,
                    FourDirectionsFlipped.RIGHT, FourDirectionsFlipped.DOWN,
                    FourDirectionsFlipped.DOWN, FourDirectionsFlipped.RIGHT
            )
    );

    public final Map<FourDirectionsFlipped, FourDirectionsFlipped> directionMap;

    Path(Map<FourDirectionsFlipped, FourDirectionsFlipped> directionMap) {
        this.directionMap = directionMap;
    }
}
