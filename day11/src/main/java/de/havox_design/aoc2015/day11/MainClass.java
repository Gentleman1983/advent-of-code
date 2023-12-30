package de.havox_design.aoc2015.day11;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + SantasPasswordPolicy.solvePart1("day11.txt"));
        LOGGER.info(() -> "Solution 2: " + SantasPasswordPolicy.solvePart2("day11.txt"));
    }
}
