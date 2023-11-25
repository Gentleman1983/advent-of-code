package de.havox_design.aoc2015.day12;

import java.util.List;

public sealed interface JSONEntity permits JSONObject, JSONString, JSONArray, JSONNumber {
    List<Integer> getIntegersWithoutRed();
}
