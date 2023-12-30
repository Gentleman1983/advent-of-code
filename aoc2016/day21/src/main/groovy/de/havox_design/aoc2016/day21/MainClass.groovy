package de.havox_design.aoc2016.day21

import org.codehaus.groovy.tools.shell.util.Logger

class MainClass {
    private static final Logger LOGGER = Logger.create(MainClass)
    private static final String FILENAME = 'day21.txt'

    public static void main(String[] args) {
        LOGGER.warn("Solution for part 1: ${Day21.solvePart1(FILENAME)}")
        LOGGER.warn("Solution for part 2: ${Day21.solvePart2(FILENAME)}")
    }
}
