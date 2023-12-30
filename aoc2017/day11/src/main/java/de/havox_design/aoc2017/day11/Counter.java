package de.havox_design.aoc2017.day11;

import java.util.stream.IntStream;

public class Counter {
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private int furthest = 0;

    Counter moveTo(Cardinal cardinal) {
        x += cardinal.getX();
        y += cardinal.getY();
        z += cardinal.getZ();

        furthest = Math
                .max(furthest, countStepsFromOrigin());

        return this;
    }

    int countStepsFromOrigin() {
        return IntStream
                .of(x, y, z)
                .map(Math::abs)
                .max()
                .orElse(0);
    }

    int getFurthest() {
        return furthest;
    }
}
