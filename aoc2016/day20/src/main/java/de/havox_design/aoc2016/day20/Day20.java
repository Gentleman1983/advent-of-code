package de.havox_design.aoc2016.day20;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;
import java.util.Optional;

public class Day20 {
    private final List<String> input;

    public Day20(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return processData()
                .firstIP();
    }

    public long solvePart2() {
        return processData()
                .numberOfAllowedIPs();
    }

    @SuppressWarnings("squid:S127")
    private ProcessingResult processData() {
        List<Range> ranges = input
                .stream()
                .map(Range::new)
                .toList();

        long firstIP = -1;
        long allowedIPs = 0;

        for (long i = 0; i <= 4294967295L; i++) {
            var range = findRange(ranges, i);

            if (range.isPresent()) {
                i = range
                        .get()
                        .upperBond();
            } else {
                allowedIPs++;
                if (firstIP == -1) {
                    firstIP = i;
                }
            }
        }

        return new ProcessingResult(firstIP, allowedIPs);
    }

    private Optional<Range> findRange(List<Range> ranges, long i) {
        return ranges
                .stream()
                .filter(r -> r.contains(i))
                .findAny();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
