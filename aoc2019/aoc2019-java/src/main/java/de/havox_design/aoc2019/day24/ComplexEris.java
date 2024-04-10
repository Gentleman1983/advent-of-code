package de.havox_design.aoc2019.day24;

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ComplexEris {
    private static final char ICON_BUG = '#';

    private Set<Position3d<Long>> bugs = new HashSet<>();

    public ComplexEris(List<String> lines) {
        for (int y = 0; y < 5; y++) {
            String line = lines.get(y);

            for (int x = 0; x < 5; x++) {
                if (line.charAt(x) == ICON_BUG) {
                    bugs.add(new Position3d<>((long) x, (long) y, 0L));
                }
            }
        }
    }

    @SuppressWarnings("squid:S3776")
    public void evolve() {
        Set<Position3d<Long>> newBugs = new HashSet<>();
        long minZ = bugs
                .stream()
                .mapToLong(Position3d::getZ)
                .min().
                orElse(0) - 1;
        long maxZ = bugs
                .stream()
                .mapToLong(Position3d::getZ)
                .max()
                .orElse(0) + 1;

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (x == 2 && y == 2) {
                    continue;
                }

                for (long z = minZ; z <= maxZ; z++) {
                    Position3d<Long> point = new Position3d<>((long) x, (long) y, z);
                    long bugCount = bugCount(point);
                    boolean isBug = bugs.contains(point);

                    if ((!isBug && (bugCount == 1 || bugCount == 2)) || (isBug && bugCount == 1)) {
                        newBugs.add(point);
                    }
                }
            }
        }

        bugs = newBugs;
    }

    public Set<Position3d<Long>> getBugs() {
        return bugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplexEris that = (ComplexEris) o;

        return Objects.equals(bugs, that.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugs);
    }

    private long bugCount(Position3d<Long> position) {
        return adjacent(position).stream()
                .filter(p -> bugs.contains(p))
                .count();
    }

    private Set<Position3d<Long>> adjacent(Position3d<Long> position) {
        Set<Position3d<Long>> positions = new HashSet<>();

        positions.add(new Position3d<>(position.getX() - 1, position.getY(), position.getZ()));
        positions.add(new Position3d<>(position.getX() + 1, position.getY(), position.getZ()));
        positions.add(new Position3d<>(position.getX(), position.getY() - 1, position.getZ()));
        positions.add(new Position3d<>(position.getX(), position.getY() + 1, position.getZ()));

        if (position.getY() == 0L) {
            positions.add(new Position3d<>(2L, 1L, position.getZ() - 1));
        }

        if (position.getY() == 4L) {
            positions.add(new Position3d<>(2L, 3L, position.getZ() - 1));
        }

        if (position.getX() == 0L) {
            positions.add(new Position3d<>(1L, 2L, position.getZ() - 1));
        }

        if (position.getX() == 4L) {
            positions.add(new Position3d<>(3L, 2L, position.getZ() - 1));
        }

        if (position.getX() == 2L && position.getY() == 1L) {
            positions.add(new Position3d<>(0L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(1L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(2L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(3L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 0L, position.getZ() + 1));
        }

        if (position.getX() == 2L && position.getY() == 3L) {
            positions.add(new Position3d<>(0L, 4L, position.getZ() + 1));
            positions.add(new Position3d<>(1L, 4L, position.getZ() + 1));
            positions.add(new Position3d<>(2L, 4L, position.getZ() + 1));
            positions.add(new Position3d<>(3L, 4L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 4L, position.getZ() + 1));
        }

        if (position.getX() == 1L && position.getY() == 2L) {
            positions.add(new Position3d<>(0L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(0L, 1L, position.getZ() + 1));
            positions.add(new Position3d<>(0L, 2L, position.getZ() + 1));
            positions.add(new Position3d<>(0L, 3L, position.getZ() + 1));
            positions.add(new Position3d<>(0L, 4L, position.getZ() + 1));
        }

        if (position.getX() == 3L && position.getY() == 2L) {
            positions.add(new Position3d<>(4L, 0L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 1L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 2L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 3L, position.getZ() + 1));
            positions.add(new Position3d<>(4L, 4L, position.getZ() + 1));
        }

        // Remove any invalid points
        positions.removeIf(p -> p.getX() < 0L || p.getX() > 4L || p.getY() < 0L || p.getY() > 4L);
        positions.removeIf(p -> p.getX() == 2L && p.getY() == 2L);

        return positions;
    }
}
