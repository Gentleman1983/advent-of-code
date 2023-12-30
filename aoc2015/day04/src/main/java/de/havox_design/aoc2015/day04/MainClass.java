package de.havox_design.aoc2015.day04;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + AdventCoins.solvePart1("day04.txt"));
        LOGGER.info(() -> "Solution 2: " + AdventCoins.solvePart2("day04.txt"));
    }
}
