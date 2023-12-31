package de.havox_design.aoc.utils.java;


import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;

public interface AoCFunctionality {
    default List<String> readData(String fileName) {
        return DataReader.readData(fileName, getClass());
    }

    default String readString(String fileName) {
        return DataReader.readString(fileName, getClass());
    }
}
