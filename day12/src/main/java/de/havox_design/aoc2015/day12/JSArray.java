package de.havox_design.aoc2015.day12;

import java.util.ArrayList;
import java.util.List;

public final class JSArray extends ArrayList<JSEntity> implements JSEntity {
    private static final long serialVersionUID = 1L;

    @Override
    public List<Integer> getIntegersWithoutRed() {
        return stream().map(JSEntity::getIntegersWithoutRed).flatMap(List::stream).toList();
    }
}
