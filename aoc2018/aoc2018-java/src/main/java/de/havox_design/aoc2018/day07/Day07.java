package de.havox_design.aoc2018.day07;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 implements AoCFunctionality {
    private final List<Task> input;

    public Day07(String fileName) {
        TaskParser parser = new TaskParser();
        input = parser.parse(readData(fileName));
    }

    public static String processTask1(String fileName) {
        Day07 instance = new Day07(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day07 instance = new Day07(fileName);
        return instance.processTask2();
    }

    public String processTask1() {
        List<Task> done = new ArrayList<>();
        Set<Task> available = new HashSet<>(getRoots());

        while (!available.isEmpty()) {
            Task next = getFirst(done, available);
            available.remove(next);
            done.add(next);
            available.addAll(next.children());
        }

        return done
                .stream()
                .map(Task::id)
                .collect(Collectors.joining());
    }

    public long processTask2() {
        return 0;
    }

    private Task getFirst(List<Task> done, Set<Task> available) {
        return available
                .stream()
                .filter(t ->
                        new HashSet<>(done)
                                .containsAll(
                                        input
                                                .stream()
                                                .filter(task ->
                                                        task
                                                                .children()
                                                                .contains(t)
                                                )
                                                .toList()
                                )
                )
                .min(Comparator.comparing(Task::id))
                .orElseThrow(() -> new IllegalStateException("This should never happen..."));
    }

    private List<Task> getRoots() {
        return input
                .stream()
                .filter(t ->
                        input
                                .stream()
                                .noneMatch(task ->
                                        task
                                                .children()
                                                .contains(t)
                                )
                )
                .toList();
    }
}
