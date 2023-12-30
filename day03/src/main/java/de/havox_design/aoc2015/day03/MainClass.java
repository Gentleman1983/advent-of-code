package de.havox_design.aoc2015.day03;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + PresentDelivery.solvePart1("day03.txt"));
        LOGGER.info(() -> "Solution 2: " + PresentDelivery.solvePart2("day03.txt"));
    }
}
