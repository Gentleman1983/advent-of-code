package de.havox_design.aoc2015.day05;

import de.havox_design.aoc2015.utils.DataReader;
import org.apache.commons.lang3.StringUtils;

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
        int numberOfVowels = 0;

        for (char vowel : "aeiou".toCharArray()) {
            numberOfVowels += StringUtils.countMatches(string, vowel);
        }

        return numberOfVowels;
    }

    private boolean containsDoubleLetter(String string) {
        for (int i = 0; i < string.length() - 1; i++) {
            if (string.charAt(i) == string.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsForbiddenStrings(String string) {
        return StringUtils.containsAny(string, "ab", "cd", "pq", "xy");
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
