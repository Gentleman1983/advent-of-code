package de.havox_design.aoc2018.day04;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

public class ReposeRecord implements AoCFunctionality {
    private final List<Guard> guards;

    public ReposeRecord(String fileName) {
        GuardParser parser = new GuardParser();

        guards = parser.parse(readData(fileName));
    }

    public static int processTask1(String fileName) {
        ReposeRecord instance = new ReposeRecord(fileName);

        return instance.processTask1();
    }

    public static int processTask2(String fileName) {
        ReposeRecord instance = new ReposeRecord(fileName);

        return instance.processTask2();
    }

    public int processTask1() {
        Guard mostAsleep = guards
                .stream()
                .max(
                        Comparator
                                .comparingInt(g ->
                                        g
                                                .shifts()
                                                .stream()
                                                .mapToInt(s ->
                                                        s
                                                                .awake()
                                                                .stream()
                                                                .mapToInt(this::booleanToBinary)
                                                                .sum()
                                                )
                                                .sum()
                                )
                )
                .orElseThrow();

        return mostAsleep.id() * bestMinute(mostAsleep).getLeft();
    }

    public int processTask2() {
        Pair<Guard, Pair<Integer, Integer>> p = guards
                .stream()
                .map(g -> Pair.of(g, bestMinute(g)))
                .max(Comparator.comparingInt(s -> s.getRight().getRight()))
                .orElseThrow();

        return p.getLeft().id() * p.getRight().getLeft();
    }

    private Pair<Integer, Integer> bestMinute(Guard g) {
        return IntStream
                .range(0, 60)
                .boxed()
                .map(i -> Pair.of(i, minuteScore(g, i)))
                .max(Comparator.comparingInt(Pair::getRight))
                .orElseThrow();
    }

    private int minuteScore(Guard g, int minute) {
        return g
                .shifts()
                .stream()
                .mapToInt(s -> booleanToBinary(s.awake().get(minute)))
                .sum();
    }

    private int booleanToBinary(boolean bool) {
        return bool ? 0 : 1;
    }
}
