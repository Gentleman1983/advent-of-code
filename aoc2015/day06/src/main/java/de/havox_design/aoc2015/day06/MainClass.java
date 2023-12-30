package de.havox_design.aoc2015.day06;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + HouseDecoratingContest.solvePart1("day06.txt"));
        LOGGER.info(() -> "Solution 2: " + HouseDecoratingContest.solvePart2("day06.txt"));
    }
}
