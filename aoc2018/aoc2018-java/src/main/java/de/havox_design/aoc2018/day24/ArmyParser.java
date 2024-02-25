package de.havox_design.aoc2018.day24;

import java.util.ArrayList;
import java.util.List;

public class ArmyParser {
    private final GroupParser groupParser;

    public ArmyParser(GroupParser parser) {
        this.groupParser = parser;
    }

    public Army parse(List<String> input) {
        String header = input.getFirst();

        if (!header.endsWith(":")) {
            throw new IllegalArgumentException();
        }

        String name = header.substring(0, header.length() - 1);
        List<Group> groups = new ArrayList<>();

        for(int i=1; i < input.size(); i++) {
            String line = input.get(i);
            Group group = groupParser.parse(line, name);
            groups.add(group);
        }

        return new Army(name, groups);
    }
}
