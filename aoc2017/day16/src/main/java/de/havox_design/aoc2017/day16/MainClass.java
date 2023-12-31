package de.havox_design.aoc2017.day16;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private static final String FILENAME = "day16.txt";
    private static final String INITIAL_WORD = "abcdefghijklmnop";
    private static final int ITERATIONS = 1000000000;

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution for part 1: " + PermutationPromenade.solvePart1(FILENAME, INITIAL_WORD));
        LOGGER.info(() -> "Solution for part 2: " + PermutationPromenade.solvePart2(FILENAME, INITIAL_WORD, ITERATIONS));
    }
}
