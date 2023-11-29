package de.havox_design.aoc2016.day03;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.ArrayList;
import java.util.List;

public class Day03 {
    private final List<Triangle> input;

    public Day03(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day03 instance = new Day03(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day03 instance = new Day03(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        long validTriangles = 0L;

        for(Triangle triangle : input) {
            if(triangle.isValid()) {
                validTriangles++;
            }
        }

        return validTriangles;
    }

    public long solvePart2() {return 0L;
    }

    private List<Triangle> readData(String fileName) {
        return DataReader
                .readData(fileName, MainClass.class)
                .stream()
                .map(String::trim)
                .map(Triangle::fromString)
                .toList();
    }
}
