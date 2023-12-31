package de.havox_design.aoc2017.day18;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day18.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + Duet.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + Duet.solvePart2(FILENAME));
    }
}
