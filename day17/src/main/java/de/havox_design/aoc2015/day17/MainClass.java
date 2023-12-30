package de.havox_design.aoc2015.day17;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + NotEnoughEggnod.solvePart1("day17.txt"));
        LOGGER.info(() -> "Solution 2: " + NotEnoughEggnod.solvePart2("day17.txt"));
    }
}
