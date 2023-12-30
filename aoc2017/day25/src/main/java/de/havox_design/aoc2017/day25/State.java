package de.havox_design.aoc2017.day25;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@SuppressWarnings("squid:S4276")
public class State implements BiFunction<Integer, Integer, Integer> {
    private final Map<Integer, Step> conditions;

    @SafeVarargs
    State(Map<Integer, Step>... conditions) {
        this.conditions = Arrays.stream(conditions)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }

    int shiftBy(int value) {
        return conditions.get(value).shiftBy;
    }

    char nextStateName(int value) {
        return conditions.get(value).nextStateName;
    }

    @Override
    public Integer apply(Integer key, Integer value) {
        return conditions.get(value == null ? 0 : value).newValue;
    }
}
