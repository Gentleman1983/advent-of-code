package de.havox_design.aoc2019.day03;

import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;

public record GridPoint(int x, int y) {
    public int distanceTo(GridPoint gridPoint) {
        return Math.abs(this.x - gridPoint.x) + Math.abs(this.y - gridPoint.y);
    }

    public GridPoint moveTo(FourDirections direction) {
        int x = this.x + direction.dx();
        int y = this.y + direction.dy();

        return new GridPoint(x, y);
    }


    public boolean isAt(GridPoint gridPoint) {
        return isAt(gridPoint.x(), gridPoint.y());
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
}
