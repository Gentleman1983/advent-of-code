package de.havox_design.aoc2015.day24;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + BalancedQuantumEntanglement.solvePart1("day24.txt"));
        LOGGER.info(() -> "Solution 2: " + BalancedQuantumEntanglement.solvePart2("day24.txt"));
    }
}
