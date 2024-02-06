package de.havox_design.aoc2018.day07;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static long processTask2(String fileName, int workers, Map<String, Integer> processingTime) {
        Day07 instance = new Day07(fileName);
        return instance.processTask2(workers, processingTime);
    }

    @SuppressWarnings("squid:S2259")
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
        Map<String, Integer> processingTime = new HashMap<>();
        processingTime.put("A", 61);
        processingTime.put("B", 62);
        processingTime.put("C", 63);
        processingTime.put("D", 64);
        processingTime.put("E", 65);
        processingTime.put("F", 66);
        processingTime.put("G", 67);
        processingTime.put("H", 68);
        processingTime.put("I", 69);
        processingTime.put("J", 70);
        processingTime.put("K", 71);
        processingTime.put("L", 72);
        processingTime.put("M", 73);
        processingTime.put("N", 74);
        processingTime.put("O", 75);
        processingTime.put("P", 76);
        processingTime.put("Q", 77);
        processingTime.put("R", 78);
        processingTime.put("S", 79);
        processingTime.put("T", 80);
        processingTime.put("U", 81);
        processingTime.put("V", 82);
        processingTime.put("W", 83);
        processingTime.put("X", 84);
        processingTime.put("Y", 85);
        processingTime.put("Z", 86);

        return processTask2(5, processingTime);
    }

    public long processTask2(int workers, Map<String, Integer> processingTime) {
        List<Task> done = new ArrayList<>();
        Set<Task> available = new HashSet<>(getRoots());
        Worker[] workforce = new Worker[workers];
        long time = 0L;

        while (done.size() != input.size()) {
            Task task;
            int worker;

            while ((task = getFirst(done, available)) != null && (worker = getAvailableWorker(workers, workforce, time)) != -1) {
                available.remove(task);
                workforce[worker] = new Worker(task, time, processingTime);
            }

            long nextTime = Arrays
                    .stream(workforce)
                    .filter(Objects::nonNull)
                    .mapToLong(Worker::getEnd)
                    .min()
                    .orElseThrow(() -> new IllegalStateException("This should never happen..."));

            updateState(workforce, done, available, nextTime);
            time = nextTime;
        }
        return time;
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
                .orElse(null);
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

    private int getAvailableWorker(int workers, Worker[] workforce, long time) {
        return IntStream
                .range(0, workers)
                .filter(i -> workforce[i] == null || workforce[i].getEnd() <= time)
                .findFirst()
                .orElse(-1);
    }

    private void updateState(Worker[] workforce, List<Task> done, Set<Task> available, long time) {
        IntStream
                .range(0, workforce.length)
                .filter(i -> Objects.nonNull(workforce[i]))
                .filter(i -> workforce[i].getEnd() <= time)
                .forEach(t -> {
                            done.add(workforce[t].task());
                            available.addAll(workforce[t].task().children());
                            workforce[t] = null;
                        }
                );
    }
}
