package de.havox_design.aoc2015.day12;

import java.util.List;

public sealed interface JSEntity permits JSObject,JSString,JSArray,JSNumber {
    List<Integer> getIntegersWithoutRed();
}
