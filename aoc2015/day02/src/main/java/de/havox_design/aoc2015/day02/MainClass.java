package de.havox_design.aoc2015.day02;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + WrappingPaper.solvePart1("day02.txt"));
        LOGGER.info(() -> "Solution 2: " + WrappingPaper.solvePart2("day02.txt"));
    }
}
