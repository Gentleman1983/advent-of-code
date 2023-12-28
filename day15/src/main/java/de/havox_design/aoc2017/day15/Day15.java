package de.havox_design.aoc2017.day15;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day15 {
    private static final int ID_A = 0;
    private static final int ID_B = 1;
    private final List<String> input;

    public Day15(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day15 instance = new Day15(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        int[] data = parseInput();

        GeneratorPair generatorPair = GeneratorPair.of(data[ID_A], data[ID_B]);

        return countMatchingPairs(generatorPair, 40000000);
    }

    private long countMatchingPairs(GeneratorPair generatorPair, int iterations) {
        long counter = 0L;

        for (int i = 0; i < iterations; i++) {
            generatorPair = generatorPair.next();

            if (generatorPair.matches()) {
                counter++;
            }
        }

        return counter;
    }

    public long solvePart2() {
        return 0L;
    }

    private int[] parseInput() {
        int[] result = new int[2];

        result[ID_A] = parseRow(0);
        result[ID_B] = parseRow(1);

        return result;
    }

    private int parseRow(int index) {
        return Integer
                .parseInt(
                        input
                                .get(index)
                                .split(" ")[4]
                );
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
