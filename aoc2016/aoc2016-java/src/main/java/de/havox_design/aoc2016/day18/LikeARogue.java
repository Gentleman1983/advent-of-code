package de.havox_design.aoc2016.day18;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.stream.IntStream;

public class LikeARogue implements AoCFunctionality {
    private final String input;

    public LikeARogue(String fileName) {
        input = readData(fileName).get(0);
    }

    public static long solvePart1(String fileName) {
        LikeARogue instance = new LikeARogue(fileName);
        return instance.solvePart1(40);
    }

    public static long solvePart2(String fileName) {
        LikeARogue instance = new LikeARogue(fileName);
        return instance.solvePart2();
    }

    public long solvePart1(int rowCount) {
        return compute(rowCount);
    }

    public long solvePart2() {
        return solvePart1(400000);
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
}
