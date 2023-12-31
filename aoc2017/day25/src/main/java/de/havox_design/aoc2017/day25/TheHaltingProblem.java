package de.havox_design.aoc2017.day25;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import static de.havox_design.aoc2017.day25.Helper.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TheHaltingProblem implements AoCFunctionality {
    private final List<String> input;

    public TheHaltingProblem(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        TheHaltingProblem instance = new TheHaltingProblem(fileName);
        return instance.solvePart1();
    }

    public long solvePart1() {
        return getDiagnosticChecksum();
    }

    private long getDiagnosticChecksum() {
        Iterator<String> iterator = input.iterator();
        char nextStateName = getCharacter(iterator.next());
        int iterations = getNumber(iterator.next());
        Map<Character, State> states = getStateMap(iterator);
        Map<Integer, Integer> results = new HashMap<>(Map.of(0, 0));
        int index = 0;

        for (var i = 0; i < iterations; i++) {
            State currentState = states.get(nextStateName);
            int value = results.getOrDefault(index, 0);
            int shiftBy = currentState.shiftBy(value);

            nextStateName = currentState.nextStateName(value);
            results.compute(index, currentState);
            index += shiftBy;
        }

        return results
                .values()
                .stream()
                .filter(i -> i == 1)
                .count();
    }
}
