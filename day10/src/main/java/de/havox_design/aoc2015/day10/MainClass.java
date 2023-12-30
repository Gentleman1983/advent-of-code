package de.havox_design.aoc2015.day10;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + Elves.solvePart1("day10.txt"));
        LOGGER.info(() -> "Solution 2: " + Elves.solvePart2("day10.txt"));
    }
}
