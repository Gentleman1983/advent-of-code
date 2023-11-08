package de.havox_design.aoc2015.day03;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class PresentDelivery {
    private final String input;

    public PresentDelivery(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        PresentDelivery instance = new PresentDelivery(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        PresentDelivery instance = new PresentDelivery(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return 0;
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
