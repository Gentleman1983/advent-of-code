package de.havox_design.aoc2016.day14;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day14.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + OneTimePad.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + OneTimePad.solvePart2(FILENAME));
    }
}
