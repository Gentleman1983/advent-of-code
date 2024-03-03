package de.havox_design.aoc20xx.day15;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day15 implements AoCFunctionality {
    private final List<String> input;

    public Day15(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 1L;
    }

    public long processTask2() {
        return 1L;
    }
}
