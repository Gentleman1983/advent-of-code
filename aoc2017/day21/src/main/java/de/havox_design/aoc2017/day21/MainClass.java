package de.havox_design.aoc2017.day21;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day21.txt";

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + FractalArt.solvePart1(FILENAME, 5));
        LOGGER.info(() -> "Solution for part 2: " + FractalArt.solvePart2(FILENAME));
    }
}
