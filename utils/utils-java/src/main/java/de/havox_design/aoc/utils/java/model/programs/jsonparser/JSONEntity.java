package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.util.List;

@SuppressWarnings("javaarchitecture:S7027")
public sealed interface JSONEntity permits JSONObject, JSONString, JSONArray, JSONNumber {
    List<Integer> getIntegersWithoutRed();
}
