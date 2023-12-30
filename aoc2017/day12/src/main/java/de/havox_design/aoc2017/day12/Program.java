package de.havox_design.aoc2017.day12;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    private static final Pattern DATA_ROW_PARSER = Pattern.compile(
            "(?<id>\\d+) <-> (?<target>.+)");
    private static final Pattern TARGET_PARSER = Pattern.compile(", ");

    private final int id;
    private final Set<Integer> targetNames;
    private final Set<Program> targets = new HashSet<>();

    public Program(int id, Set<Integer> targetNames) {
        this.id = id;
        this.targetNames = targetNames;
    }

    public void setTargets(Map<Integer, Program> map) {
        targetNames
                .stream()
                .map(map::get)
                .forEach(targets::add);
    }

    public int getId() {
        return id;
    }

    public Stream<Program> streamTargetsExcluding(Set<Program> visited) {
        return targets
                .stream()
                .filter(program -> !visited.contains(program));
    }

    public static Program parse(String row) {
        var matcher = DATA_ROW_PARSER
                .matcher(row);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expected a program but got: " + row + ".");
        }
        return new Program(
                Integer
                        .parseInt(matcher.group("id")),
                TARGET_PARSER
                        .splitAsStream(matcher.group("target"))
                        .map(Integer::valueOf)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }
}
