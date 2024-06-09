package de.havox_design.aoc2019.day07;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class AmplificationCircuit implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";

    private final List<Long> input;

    public AmplificationCircuit(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        AmplificationCircuit instance = new AmplificationCircuit(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        AmplificationCircuit instance = new AmplificationCircuit(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return process(Set.of(0, 1, 2, 3, 4));
    }

    public long processTask2() {
        return process(Set.of(5, 6, 7, 8, 9));
    }

    private long process(Collection<Integer> availablePhases) {
        return CollectionUtils
                .permutations(availablePhases)
                .stream()
                .map(settings -> runComputers(settings.iterator()))
                .max(Long::compareTo)
                .orElseThrow();
    }

    @SuppressWarnings("squid:S2142")
    private long runComputers(Iterator<Integer> settings) {
        BlockingQueue<Long> firstConnection = new LinkedBlockingQueue<>();
        BlockingQueue<Long> previousConnection = firstConnection;
        Set<Future<?>> computers = new HashSet<>();

        while (settings.hasNext()) {
            previousConnection.add(settings.next().longValue());

            BlockingQueue<Long> nextConnection = settings.hasNext() ? new LinkedBlockingQueue<>() : firstConnection;

            computers.add(IntComputer.runComputer(input, previousConnection, nextConnection, true));
            previousConnection = nextConnection;
        }

        try {
            firstConnection.add(0L);

            for (Future<?> computer : computers) {
                computer.get();
            }

            return previousConnection.remove();
        } catch (InterruptedException | ExecutionException e) {
            throw new AdventOfCodeException(e);
        }
    }
}
