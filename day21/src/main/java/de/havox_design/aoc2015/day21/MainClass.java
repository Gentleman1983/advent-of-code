package de.havox_design.aoc2015.day21;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + RPGBossFight.solvePart1("day21.txt"));
        LOGGER.info(() -> "Solution 2: " + RPGBossFight.solvePart2("day21.txt"));
    }
}
