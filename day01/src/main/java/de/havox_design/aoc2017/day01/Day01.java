package de.havox_design.aoc2017.day01;

import de.havox_design.aoc.utils.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 {
    private final List<String> input;

    public Day01(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        List<Integer> data = parseInput();
        data.add(data.get(0));

        long sum = 0L;

        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).equals(data.get(i + 1))) {
                sum += data.get(i);
            }
        }

        return sum;
    }

    public long solvePart2() {
        List<Integer> data = parseInput();

        return IntStream
                .range(0, data.size())
                .mapToLong(index -> {
                            int digit = data.get(index);
                            int otherDigit = data.get((index + (data.size() / 2)) % data.size());
                            return digit == otherDigit ? digit : 0;
                        }
                )
                .sum();
    }

    private List<Integer> parseInput() {
        List<Integer> data = new ArrayList<>();

        for (char c : input.get(0).toCharArray()) {
            int number = Integer.parseInt(String.valueOf(c));
            data.add(number);
        }

        return data;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
