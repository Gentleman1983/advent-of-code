package de.havox_design.aoc2017.day15;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day15.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + Day15.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + Day15.solvePart1(FILENAME));
    }
}
