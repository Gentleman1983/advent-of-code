package de.havox_design.aoc2015.day25;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + CodeLock.solvePart1("day25.txt"));
        LOGGER.info(() -> "Solution 2: " + CodeLock.solvePart2("day25.txt"));
    }
}
