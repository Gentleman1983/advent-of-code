package de.havox_design.aoc2016.day17;

import de.havox_design.aoc.utils.kotlin.model.directions.UDLRDirection;

public record Tile(int row, int col) {
    public boolean isValid(int rowCount, int colCount) {
        return row >= 0
                && row < rowCount
                && col >= 0
                && col < colCount;
    }

    public Tile neighbor(UDLRDirection direction) {
        return switch (direction) {
            case UDLRDirection.UP -> new Tile(row - 1, col);
            case UDLRDirection.DOWN -> new Tile(row + 1, col);
            case UDLRDirection.LEFT -> new Tile(row, col - 1);
            case UDLRDirection.RIGHT -> new Tile(row, col + 1);
        };
    }
}
