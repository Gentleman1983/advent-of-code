package de.havox_design.aoc2017.day22;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day22.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + SporificaVirus.solvePart1(FILENAME, 10000));
        LOGGER.info(() -> "Solution for part 2: " + SporificaVirus.solvePart2(FILENAME, 10000000));
    }
}
