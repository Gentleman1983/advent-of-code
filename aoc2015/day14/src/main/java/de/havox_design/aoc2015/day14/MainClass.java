package de.havox_design.aoc2015.day14;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + ReindeerOlympics.solvePart1("day14.txt"));
        LOGGER.info(() -> "Solution 2: " + ReindeerOlympics.solvePart2("day14.txt"));
    }
}
