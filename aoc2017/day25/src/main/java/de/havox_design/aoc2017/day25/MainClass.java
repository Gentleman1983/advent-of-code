package de.havox_design.aoc2017.day25;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day25.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + TheHaltingProblem.solvePart1(FILENAME));
    }
}
