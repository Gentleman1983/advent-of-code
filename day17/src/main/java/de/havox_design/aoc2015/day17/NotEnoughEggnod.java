package de.havox_design.aoc2015.day17;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class NotEnoughEggnod {
    private final List<Integer> input;

    public NotEnoughEggnod(String fileName) {
        input = readData(fileName)
                .stream()
                .map(Integer::parseInt)
                .toList();
    }

    public static int solvePart1(String fileName) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);
        return instance.solvePart1();
    }

    protected static int countCombinations(String fileName, int amountOfEggnod) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);
        return instance.countWays(instance.input, 0, amountOfEggnod, 0);
    }

    public static int solvePart2(String fileName) {
        NotEnoughEggnod instance = new NotEnoughEggnod(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return countWays(input, 0, 150, 0);
    }

    public int solvePart2() {
        return 0;
    }

    private int countWays(List<Integer> containers, int startIndex, int target, int countSoFar) {
        if (target == 0) {
            return 1;
        } else if (target < 0) {
            return 0;
        } else {
            int count = countSoFar;
            for (int i = startIndex; i < containers.size(); ++i) {
                count += countWays(containers, i + 1, target - containers.get(i), countSoFar);
            }
            return count;
        }
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
