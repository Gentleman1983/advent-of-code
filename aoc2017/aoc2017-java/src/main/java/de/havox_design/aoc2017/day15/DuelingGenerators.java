package de.havox_design.aoc2017.day15;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;
import java.util.function.IntPredicate;

public class DuelingGenerators implements AoCFunctionality {
    private static final int ID_A = 0;
    private static final int ID_B = 1;
    private static final IntPredicate PREDICATE_A = a -> a % 4 == 0;
    private static final IntPredicate PREDICATE_B = b -> b % 8 == 0;

    private final List<String> input;

    public DuelingGenerators(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        DuelingGenerators instance = new DuelingGenerators(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        DuelingGenerators instance = new DuelingGenerators(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        int[] data = parseInput();

        GeneratorPair generatorPair = GeneratorPair.of(data[ID_A], data[ID_B]);

        return countMatchingPairs(generatorPair, 40000000, false);
    }

    public long solvePart2() {
        int[] data = parseInput();

        GeneratorPair generatorPair = GeneratorPair.of(data[ID_A], data[ID_B]);

        return countMatchingPairs(generatorPair, 5000000, true);
    }

    private long countMatchingPairs(GeneratorPair generatorPair, int iterations, boolean isPart2) {
        long counter = 0L;

        for (int i = 0; i < iterations; i++) {
            if(isPart2) {
                generatorPair = generatorPair.next(PREDICATE_A, PREDICATE_B);
            }
            else {
                generatorPair = generatorPair.next();
            }

            if (generatorPair.matches()) {
                counter++;
            }
        }

        return counter;
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
}
