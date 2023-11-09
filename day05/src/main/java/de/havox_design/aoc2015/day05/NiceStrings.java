package de.havox_design.aoc2015.day05;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class NiceStrings {
    private final List<String> input;

    public NiceStrings(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        NiceStrings instance = new NiceStrings(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        NiceStrings instance = new NiceStrings(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        int numberNiceStrings = 0;

        for (String string : input) {
            if (
                    countVowels(string) >= 3 &&
                            containsDoubleLetter(string) &&
                            !containsForbiddenStrings(string)
            ) {
                numberNiceStrings++;
            }
        }

        return numberNiceStrings;
    }

    public int solvePart2() {
        return 0;
    }

    private int countVowels(String string) {
        return 0;
    }

    private boolean containsDoubleLetter(String string) {
        return false;
    }

    private boolean containsForbiddenStrings(String string) {
        return false;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
