package de.havox_design.aoc2016.day01;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day01.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + NoTimeForATaxicab.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + NoTimeForATaxicab.solvePart2(FILENAME));
    }
}
