package de.havox_design.aoc2015.day23;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + Turing.solvePart1("day23.txt"));
        LOGGER.info(() -> "Solution 2: " + Turing.solvePart2("day23.txt"));
    }
}
