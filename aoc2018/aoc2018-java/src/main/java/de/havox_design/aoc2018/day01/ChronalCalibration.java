package de.havox_design.aoc2018.day01;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;
import java.util.stream.Collectors;

public class ChronalCalibration implements AoCFunctionality {
    private final List<Long> input;

    public ChronalCalibration(String fileName) {
        input = readData(fileName)
                .stream()
                .map(Long::parseLong)
                .toList();
    }

    public static long processTask1(String fileName) {
        ChronalCalibration instance = new ChronalCalibration(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChronalCalibration instance = new ChronalCalibration(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return input
                .stream()
                .collect(Collectors.summarizingLong(Long::longValue))
                .getSum();
    }

    public long processTask2() {
        return 0;
    }
}
