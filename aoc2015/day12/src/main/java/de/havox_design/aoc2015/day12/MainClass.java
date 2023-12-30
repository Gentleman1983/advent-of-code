package de.havox_design.aoc2015.day12;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + JSONAbacus.solvePart1("day12.txt"));
        LOGGER.info(() -> "Solution 2: " + JSONAbacus.solvePart2("day12.txt"));
    }
}
