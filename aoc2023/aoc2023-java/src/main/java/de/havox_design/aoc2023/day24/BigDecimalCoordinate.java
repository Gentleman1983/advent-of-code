package de.havox_design.aoc2023.day24;

import java.math.BigDecimal;

public record BigDecimalCoordinate(BigDecimal x, BigDecimal y, BigDecimal z) {
    static BigDecimalCoordinate fromCoordinate(Coordinate coordinate) {
        return new BigDecimalCoordinate(
                BigDecimal.valueOf(coordinate.x()),
                BigDecimal.valueOf(coordinate.y()),
                BigDecimal.valueOf(coordinate.z())
        );
    }
}
