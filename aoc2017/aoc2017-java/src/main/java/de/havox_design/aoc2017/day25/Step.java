package de.havox_design.aoc2017.day25;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("javaarchitecture:S7027")
public class Step {
    final int newValue;
    final int shiftBy;
    final char nextStateName;

    private Step(int newValue, int shiftBy, char nextStateName) {
        this.newValue = newValue;
        this.shiftBy = shiftBy;
        this.nextStateName = nextStateName;
    }

    static Map<Integer, Step> create(Iterator<String> iterator) {
        return Map.of(Helper.getNumber(iterator.next()), new Step(
                Helper.getNumber(iterator.next()),
                Helper.getValue(iterator.next()).equals("left") ? -1 : 1,
                Helper.getCharacter(iterator.next())));
    }
}
