package de.havox_design.aoc2015.day22;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + RPGWizardFight.solvePart1("day22.txt"));
        LOGGER.info(() -> "Solution 2: " + RPGWizardFight.solvePart2("day22.txt"));
    }
}
