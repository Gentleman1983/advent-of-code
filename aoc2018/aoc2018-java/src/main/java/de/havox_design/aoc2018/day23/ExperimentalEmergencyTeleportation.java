package de.havox_design.aoc2018.day23;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Comparator;
import java.util.List;

public class ExperimentalEmergencyTeleportation implements AoCFunctionality {
    private final List<Nanobot> nanobots;

    public ExperimentalEmergencyTeleportation(String fileName) {
        NanobotParser parser = new NanobotParser();
        List<String> input = readData(fileName);

        this.nanobots = parser.parse(input);
    }

    public static long processTask1(String fileName) {
        ExperimentalEmergencyTeleportation instance = new ExperimentalEmergencyTeleportation(fileName);
        return instance.processTask1();
    }

    public long processTask1() {
        final Nanobot nanobotWithLargestRange = nanobots
                .stream()
                .max(Comparator.comparingLong(Nanobot::range))
                .orElseThrow();

        return nanobots
                .stream()
                .filter(nanobotWithLargestRange::isInRange)
                .count();
    }
}
