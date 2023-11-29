package de.havox_design.aoc2016.day21;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day21.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + Day21.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + Day21.solvePart2(FILENAME));
    }
}
