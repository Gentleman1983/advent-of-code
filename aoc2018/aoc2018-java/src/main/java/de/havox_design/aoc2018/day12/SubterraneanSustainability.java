package de.havox_design.aoc2018.day12;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SubterraneanSustainability implements AoCFunctionality {
    private final GardenInput input;

    public SubterraneanSustainability(String fileName) {
        GardenInputParser parser = new GardenInputParser();

        input = parser.parse(readData(fileName));
    }

    public static long processTask1(String fileName) {
        SubterraneanSustainability instance = new SubterraneanSustainability(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SubterraneanSustainability instance = new SubterraneanSustainability(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return countPotsWithPlantsAfterGeneration(20L);
    }

    public long processTask2() {
        return countPotsWithPlantsAfterGeneration(50000000000L);
    }

    private long countPotsWithPlantsAfterGeneration(long generation) {
        List<Pots> states = initPotStates();
        int index;

        while (states.size() <= generation) {
            Pots pots = nextGeneration(states.getLast());

            index = IntStream
                    .range(0, states.size())
                    .filter(i -> states.get(i).pots().equals(pots.pots()))
                    .findFirst()
                    .orElse(-1);

            if (index >= 0) {
                return calculateValueForCycle(states, pots, index, generation);
            }

            states.add(pots);
        }

        Pots lastPots = states.getLast();

        return getValue(lastPots.pots(), lastPots.offset());
    }

    private long calculateValueForCycle(List<Pots> states, Pots pots, int match, long generation) {
        long repeat = Math.subtractExact(states.size(), match);
        long sub = generation - states.size() + 1;
        long div = sub / repeat;
        int remainder = (int) (sub % repeat);
        long offsetDifferenceBetweenMatch = Math.subtractExact(
                pots.offset(),
                states.get(match).offset()
        );
        long offsetDiffBetweenRemainder = Math.subtractExact(
                states.get(match + remainder).offset(),
                states.get(match).offset()
        );
        long offset = offsetDifferenceBetweenMatch * div + offsetDiffBetweenRemainder + states.get(match).offset();

        return getValue(states.get(match + remainder).pots(), offset);
    }

    private long getValue(List<Boolean> pots, long offset) {
        return IntStream
                .range(0, pots.size())
                .filter(pots::get)
                .mapToLong(i -> Math.subtractExact(i, offset))
                .sum();
    }

    private List<Pots> initPotStates() {
        List<Pots> states = new ArrayList<>();

        states.add(new Pots(0, input.initialState()));

        return states;
    }

    private Pots nextGeneration(Pots currentGeneration) {
        return newPots(
                IntStream
                        .range(-2, currentGeneration.pots().size() + 1)
                        .mapToObj(i -> getNextValue(currentGeneration.pots(), i))
                        .toList(),
                currentGeneration.offset()
        );
    }

    private Pots newPots(List<Boolean> currentPots, long oldOffset) {
        int firstTrue = currentPots.indexOf(true);

        return new Pots(
                oldOffset + 2 - firstTrue,
                currentPots.subList(firstTrue, currentPots.lastIndexOf(true) + 1)
        );
    }

    private boolean valueAt(List<Boolean> currentPots, int index) {
        if (index < 0 || index >= currentPots.size()) {
            return false;
        }

        return currentPots.get(index);
    }

    private boolean getNextValue(List<Boolean> currentPots, int index) {
        return input
                .patterns()
                .getOrDefault(
                        IntStream
                                .rangeClosed(index - 2, index + 2)
                                .mapToObj(i -> valueAt(currentPots, i))
                                .toList(),
                        false
                );
    }
}
