package de.havox_design.aoc2019.day24;

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SimpleEris {
    private static final char ICON_BUG = '#';

    private Set<Position2d<Integer>> bugs = new HashSet<>();

    public SimpleEris(List<String> lines) {
        for (int y = 0; y < 5; y++) {
            String line = lines.get(y);

            for (int x = 0; x < 5; x++) {
                if (line.charAt(x) == ICON_BUG) {
                    bugs.add(new Position2d<>(x, y));
                }
            }
        }
    }

    public void evolve() {
        Set<Position2d<Integer>> newBugs = new HashSet<>();

        for (var x = 0; x < 5; x++) {
            for (var y = 0; y < 5; y++) {
                Position2d<Integer> point = new Position2d<>(x, y);
                long bugCount = bugCount(point);
                boolean isBug = bugs.contains(point);

                if ((!isBug && (bugCount == 1 || bugCount == 2)) || (isBug && bugCount == 1)) {
                    newBugs.add(point);
                }
            }
        }

        bugs = newBugs;
    }

    public long biodiversityRating() {
        int i = 0;
        long rating = 0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (bugs.contains(new Position2d<>(x, y))) {
                    rating += 1L << i;
                }

                i++;
            }
        }

        return rating;
    }

    public Set<Position2d<Integer>> getBugs() {
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

        SimpleEris that = (SimpleEris) o;

        return Objects.equals(bugs, that.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugs);
    }

    private long bugCount(Position2d<Integer> position) {
        return calculateAdjacentPositions(position)
                .stream()
                .filter(p -> bugs.contains(p))
                .count();
    }

    private Set<Position2d<Integer>> calculateAdjacentPositions(Position2d<Integer> position) {
        return Set.of(
                new Position2d<>(position.getX() + 1, position.getY()),
                new Position2d<>(position.getX() - 1, position.getY()),
                new Position2d<>(position.getX(), position.getY() + 1),
                new Position2d<>(position.getX(), position.getY() - 1)
        );
    }
}
