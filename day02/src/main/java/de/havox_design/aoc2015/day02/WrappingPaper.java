package de.havox_design.aoc2015.day02;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class WrappingPaper {
    private final List<String> input;

    public WrappingPaper(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        WrappingPaper instance = new WrappingPaper(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        WrappingPaper instance = new WrappingPaper(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        int amount = 0;

        for (String dataRow : input) {
            Dimensions current = parseDimensions(dataRow);

            for (int side : current.getSides()) {
                amount += 2 * side;
            }

            amount += current.getSmallestSide();
        }

        return amount;
    }

    public int solvePart2() {
        return 0;
    }

    private Dimensions parseDimensions(String dataRow) {
        return Dimensions.getForDataRow(dataRow);
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
