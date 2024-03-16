package de.havox_design.aoc2019.day05;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class SunnyWithAChanceOfAsteroids implements AoCFunctionality {
    private final String input;

    public SunnyWithAChanceOfAsteroids(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        SunnyWithAChanceOfAsteroids instance = new SunnyWithAChanceOfAsteroids(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SunnyWithAChanceOfAsteroids instance = new SunnyWithAChanceOfAsteroids(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
