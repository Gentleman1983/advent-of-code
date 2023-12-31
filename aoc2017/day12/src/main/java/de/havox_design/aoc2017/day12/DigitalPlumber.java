package de.havox_design.aoc2017.day12;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DigitalPlumber implements AoCFunctionality {
    private final List<String> input;

    public DigitalPlumber(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        DigitalPlumber instance = new DigitalPlumber(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        DigitalPlumber instance = new DigitalPlumber(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return calculateGroupSize(input, 0);
    }

    public long solvePart2() {
        return calculateTotalNumberOfGroups(input);
    }

    private long calculateGroupSize(Collection<String> programs, int groupId) {
        return getTargets(generateMap(programs), groupId)
                .size();
    }

    private long calculateTotalNumberOfGroups(Collection<String> programs) {
        long counter = 0L;

        for (
                Map<Integer, Program> map = generateMap(programs);
                !map.isEmpty();
                getTargets(
                        map,
                        map
                                .keySet()
                                .iterator()
                                .next()
                )
                        .stream()
                        .map(Program::getId)
                        .forEach(map::remove)
        ) {
            counter++;
        }

        return counter;
    }

    private Set<Program> getTargets(Map<Integer, Program> map, int groupId) {
        Program target = map
                .get(groupId);
        Set<Program> visited = new HashSet<>(Set.of(target));

        aggregate(target.streamTargetsExcluding(visited), visited);

        return visited;
    }

    @SuppressWarnings("squid:S3864")
    private void aggregate(Stream<Program> stream, Set<Program> seen) {
        stream
                .peek(seen::add)
                .forEach(v -> aggregate(v.streamTargetsExcluding(seen), seen));
    }

    private Map<Integer, Program> generateMap(Collection<String> programs) {
        Map<Integer, Program> map = programs
                .stream()
                .map(Program::parse)
                .collect(Collectors.toMap(Program::getId, Function.identity()));

        map
                .values()
                .forEach(p -> p.setTargets(map));

        return map;
    }
}
