package de.havox_design.aoc2017.day25;

import static de.havox_design.aoc2017.day25.Helper.*;

import java.util.Iterator;
import java.util.Map;

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
        return Map.of(getNumber(iterator.next()), new Step(
                getNumber(iterator.next()),
                getValue(iterator.next()).equals("left") ? -1 : 1,
                getCharacter(iterator.next())));
    }
}
