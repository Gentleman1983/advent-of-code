package de.havox_design.aoc2015.day15;

import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        LOGGER.info(() -> "Solution 1: " + ScienceForHungryPeople.solvePart1("day15.txt"));
        LOGGER.info(() -> "Solution 2: " + ScienceForHungryPeople.solvePart2("day15.txt"));
    }
}
