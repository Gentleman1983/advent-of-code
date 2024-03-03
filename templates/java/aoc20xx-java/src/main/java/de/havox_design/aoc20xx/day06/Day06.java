package de.havox_design.aoc20xx.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day06 implements AoCFunctionality {
    private final List<String> input;

    public Day06(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 1L;
    }

    public long processTask2() {
        return 1L;
    }
}
