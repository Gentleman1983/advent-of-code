package de.havox_design.aoc2018.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class ChronalCoordinates implements AoCFunctionality {
    private final String input;

    public ChronalCoordinates(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        ChronalCoordinates instance = new ChronalCoordinates(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChronalCoordinates instance = new ChronalCoordinates(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 17L;
    }

    public long processTask2() {
        return 0;
    }
}
