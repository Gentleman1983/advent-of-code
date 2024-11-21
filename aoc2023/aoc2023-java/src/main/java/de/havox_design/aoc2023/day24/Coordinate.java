package de.havox_design.aoc2023.day24;

public record Coordinate(long x, long y, long z) {
    private static final int ID_X = 0;
    private static final int ID_Y = 1;
    private static final int ID_Z = 2;

    static Coordinate read(String description) {
        String[] parts = description.split(", ");
        return new Coordinate(
                Long.parseLong(parts[ID_X].strip()),
                Long.parseLong(parts[ID_Y].strip()),
                Long.parseLong(parts[ID_Z].strip())
        );
    }
}
