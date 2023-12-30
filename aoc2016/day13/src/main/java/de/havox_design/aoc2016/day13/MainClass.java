package de.havox_design.aoc2016.day13;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day13.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + Day13.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + Day13.solvePart2(FILENAME));
    }
}