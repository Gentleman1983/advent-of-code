package de.havox_design.aoc2016.day09;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day09.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + ExplosivesInCyberspace.solvePart1(FILENAME));
        LOGGER.info(() -> "Solution for part 2: " + ExplosivesInCyberspace.solvePart2(FILENAME));
    }
}
