package de.havox_design.aoc2016.day17;

public record Tile(int row, int col) {
    public boolean isValid(int rowCount, int colCount) {
        return row >= 0
                && row < rowCount
                && col >= 0
                && col < colCount;
    }

    public Tile neighbor(Direction direction) {
        return switch (direction) {
            case Direction.UP -> new Tile(row - 1, col);
            case Direction.DOWN -> new Tile(row + 1, col);
            case Direction.LEFT -> new Tile(row, col - 1);
            case Direction.RIGHT -> new Tile(row, col + 1);
        };
    }
}
