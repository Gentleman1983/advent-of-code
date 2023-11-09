package de.havox_design.aoc2015.day05;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + NiceStrings.solvePart1("day05.txt"));
        LOGGER.info(() -> "Solution 2: " + NiceStrings.solvePart2("day05.txt"));
    }
}
