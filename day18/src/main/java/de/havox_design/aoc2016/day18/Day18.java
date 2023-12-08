package de.havox_design.aoc2016.day18;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day18 {
    private final String input;

    public Day18(String fileName) {
        input = readData(fileName).get(0);
    }

    public static long solvePart1(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart1(40);
    }

    public static long solvePart2(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart2();
    }

    public long solvePart1(int rowCount) {
        return compute(rowCount);
    }

    public long solvePart2() {
        return 0L;
    }

    private long compute(int rowCount) {
        long counter = countSafeTiles(input);

        String row = input;

        for (int i = 0; i < rowCount - 1; i++) {
            String previousRow = "." + row + ".";
            StringBuilder builder = new StringBuilder();

            for (int j = 0, length = row.length(); j < length; j++) {
                String parents = previousRow.substring(j, j + 3);
                boolean isTrap = "^^.".equals(parents)
                        || ".^^".equals(parents)
                        || "..^".equals(parents)
                        || "^..".equals(parents);
                builder.append(isTrap ? '^' : '.');
            }

            row = builder.toString();
            counter += countSafeTiles(row);
        }

        return counter;
    }

    private long countSafeTiles(String row) {
        return IntStream
                .range(0, row.length())
                .filter(i -> row.charAt(i) == '.')
                .count();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
