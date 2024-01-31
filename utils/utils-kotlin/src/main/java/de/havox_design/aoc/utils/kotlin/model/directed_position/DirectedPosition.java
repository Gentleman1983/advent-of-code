package de.havox_design.aoc.utils.kotlin.model.directed_position;

import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

public class DirectedPosition {
    private final Position2d<Integer> position;
    private final FourDirections direction;

    public DirectedPosition(final Position2d<Integer> position, final FourDirections direction) {
        this.position = position;
        this.direction = direction;
    }

    public DirectedPosition forward() {
        return switch (direction) {
            case FourDirections.UP -> new DirectedPosition(new Position2d<>(getX(), getY() - 1), direction);
            case FourDirections.DOWN -> new DirectedPosition(new Position2d<>(getX(), getY() + 1), direction);
            case FourDirections.LEFT -> new DirectedPosition(new Position2d<>(getX() - 1, getY()), direction);
            case FourDirections.RIGHT -> new DirectedPosition(new Position2d<>(getX() + 1, getY()), direction);
        };
    }

    public DirectedPosition left() {
        return new DirectedPosition(new Position2d<>(getX(), getY()), direction.turnLeft());
    }

    public DirectedPosition right() {
        return new DirectedPosition(new Position2d<>(getX(), getY()), direction.turnRight());
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Override
    public String toString() {
        return String.format("[x=%s, y=%s, direction=%s]", getX(), getY(), direction);
    }
}
