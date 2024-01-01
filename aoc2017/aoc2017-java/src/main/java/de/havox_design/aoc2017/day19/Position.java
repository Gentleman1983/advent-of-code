package de.havox_design.aoc2017.day19;

public class Position {
    private final int x;
    private final int y;
    private final Direction direction;

    public Position(final int posX, final int posY, final Direction direction) {
        this.x = posX;
        this.y = posY;
        this.direction = direction;
    }

    public Position forward() {
        return switch (direction) {
            case Direction.UP -> new Position(x, y - 1, direction);
            case Direction.DOWN -> new Position(x, y + 1, direction);
            case Direction.LEFT -> new Position(x - 1, y, direction);
            case Direction.RIGHT -> new Position(x + 1, y, direction);
        };
    }

    public Position left() {
        return new Position(x, y, direction.left());
    }

    public Position right() {
        return  new Position(x, y, direction.right());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + ", direction=" + direction + "]";
    }
}
