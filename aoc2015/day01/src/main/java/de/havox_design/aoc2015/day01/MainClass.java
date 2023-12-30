package de.havox_design.aoc2015.day01;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + NotQuiteLisp.processTask1("day01.txt"));
        LOGGER.info(() -> "Solution 2: " + NotQuiteLisp.processTask2("day01.txt"));
    }
}
