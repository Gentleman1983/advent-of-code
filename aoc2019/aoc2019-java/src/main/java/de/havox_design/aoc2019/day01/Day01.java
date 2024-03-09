package de.havox_design.aoc2019.day01;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;
import java.util.Map;

public class Day01 implements AoCFunctionality {
    private final List<Long> input;

    public Day01(String fileName) {
        input = readData(fileName).stream().map(line -> Long.parseLong(line.trim())).toList();
    }

    public static long processTask1(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        long totalFuelRequirement = 0L;

        for (long moduleMass : input) {
            totalFuelRequirement += calculateFuelRequirement(moduleMass);
        }

        return totalFuelRequirement;
    }

    public long processTask2() {
        return 0;
    }

    private long calculateFuelRequirement(long mass) {
        return (mass / 3) - 2;
    }
}
