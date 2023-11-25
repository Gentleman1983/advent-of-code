package de.havox_design.aoc2015.day12;

import java.util.LinkedHashMap;
import java.util.List;

public final class JSObject extends LinkedHashMap<String, JSEntity> implements JSEntity {
    private static final long serialVersionUID = 1L;

    @Override
    public List<Integer> getIntegersWithoutRed() {
        return values().stream().map(JSEntity::getIntegersWithoutRed).flatMap(List::stream).toList();
    }
}
