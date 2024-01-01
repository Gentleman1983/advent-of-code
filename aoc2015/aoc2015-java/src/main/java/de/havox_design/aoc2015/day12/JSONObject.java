package de.havox_design.aoc2015.day12;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.List;

public final class JSONObject extends LinkedHashMap<String, JSONEntity> implements JSONEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final JSONString RED = new JSONString("red");

    @Override
    public List<Integer> getIntegersWithoutRed() {
        if (values().contains(RED)) {
            return List.of();
        }
        return values()
                .stream()
                .map(JSONEntity::getIntegersWithoutRed)
                .flatMap(List::stream)
                .toList();
    }
}
