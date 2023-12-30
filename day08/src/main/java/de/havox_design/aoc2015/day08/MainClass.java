package de.havox_design.aoc2015.day08;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + Matchsticks.solvePart1("day08.txt"));
        LOGGER.info(() -> "Solution 2: " + Matchsticks.solvePart2("day08.txt"));
    }
}
