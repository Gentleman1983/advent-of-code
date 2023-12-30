package de.havox_design.aoc2015.day12;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public final class JSONArray extends ArrayList<JSONEntity> implements JSONEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public List<Integer> getIntegersWithoutRed() {
        return stream()
                .map(JSONEntity::getIntegersWithoutRed)
                .flatMap(List::stream)
                .toList();
    }
}
