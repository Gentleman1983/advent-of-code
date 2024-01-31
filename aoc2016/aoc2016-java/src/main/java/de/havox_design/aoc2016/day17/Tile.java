package de.havox_design.aoc2016.day17;

import de.havox_design.aoc.utils.kotlin.model.directions.UDLRDirection;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

public record Tile(Position2d<Integer> position) {
    public boolean isValid(int rowCount, int colCount) {
        return position.getY() >= 0
                && position.getY() < rowCount
                && position.getX() >= 0
                && position.getX() < colCount;
    }

    public Tile neighbor(UDLRDirection direction) {
        return new Tile(
                new Position2d<>(
                        direction.getDirection().getX() + position.getX(),
                        direction.getDirection().getY() + position.getY()
                )
        );
    }
}
