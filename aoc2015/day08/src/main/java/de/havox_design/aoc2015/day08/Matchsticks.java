package de.havox_design.aoc2015.day08;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class Matchsticks {
    private final List<String> input;

    public Matchsticks(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        Matchsticks instance = new Matchsticks(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        Matchsticks instance = new Matchsticks(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return calculateCharactersOfCode() - calculateCharactersInMemory();
    }

    public int solvePart2() {
        return calculateEncodedCharacters() - calculateCharactersOfCode();
    }

    protected int calculateCharactersOfCode() {
        return input
                .stream()
                .map(String::length)
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int calculateCharactersInMemory() {
        return input
                .stream()
                .map(Matchsticks::inMemoryLength)
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int calculateEncodedCharacters() {
        return input
                .stream()
                .map(Matchsticks::encodedLength)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static int inMemoryLength(String line) {

        String rawString = line.substring(1, line.length() - 1);
        int length = rawString.length();

        int count = rawString.length();
        if (count == 0) {
            return count;
        }
        int i = 0;

        do {
            if (rawString.charAt(i) == '\\'
                    && (rawString.charAt(i + 1) == '\\'
                    || rawString.charAt(i + 1) == '"')) {
                count -= 1;
                i += 2;
            } else if (rawString.charAt(i) == '\\'
                    && rawString.charAt(i + 1) == 'x') {
                i += 4;
                count -= 3;
            } else {
                i += 1;
            }
        } while (i < length);
        return count;
    }

    private static int encodedLength(String line) {
        StringBuilder builder = new StringBuilder();
        builder.append("\\\"");
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                builder.append("\\");
            }
            builder.append(line.charAt(i));
        }
        builder.append("\\\"");
        return builder.toString().length();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
