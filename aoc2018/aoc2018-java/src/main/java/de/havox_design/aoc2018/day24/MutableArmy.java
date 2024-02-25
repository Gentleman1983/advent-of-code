package de.havox_design.aoc2018.day24;

import java.util.ArrayList;
import java.util.List;

public class MutableArmy {
    private final String name;
    private final List<MutableGroup> groups;

    public MutableArmy(String name, List<MutableGroup> groups) {
        this.name = name;
        this.groups = new ArrayList<>(groups);
    }

    public String getName() {
        return name;
    }

    public List<MutableGroup> getGroups() {
        return groups;
    }
}
