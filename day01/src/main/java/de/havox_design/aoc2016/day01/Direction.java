package de.havox_design.aoc2016.day01;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public Direction left() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Direction right() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }

    public Point next(Point point, int offset) {
        return switch (this) {
            case NORTH -> new Point(point.x(), point.y() + offset);
            case SOUTH -> new Point(point.x(), point.y() - offset);
            case EAST -> new Point(point.x() + offset, point.y());
            case WEST -> new Point(point.x() - offset, point.y());
        };
    }
}
