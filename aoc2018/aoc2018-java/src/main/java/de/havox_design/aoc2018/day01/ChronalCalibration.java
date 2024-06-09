package de.havox_design.aoc2018.day01;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        long frequency = 0;
        Set<Long> seenFrequencies = new HashSet<>();
        int i = 0;

        while(!seenFrequencies.contains(frequency)) {
            seenFrequencies.add(frequency);
            frequency += input.get(i);
            i = (i + 1) % input.size();
        }

        return frequency;
    }
}
