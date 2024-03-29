package de.havox_design.aoc2019.day14;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class SpaceStoichiometry implements AoCFunctionality {
    private final String input;

    public SpaceStoichiometry(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        SpaceStoichiometry instance = new SpaceStoichiometry(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SpaceStoichiometry instance = new SpaceStoichiometry(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
