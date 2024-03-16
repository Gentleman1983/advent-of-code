package de.havox_design.aoc2019.day04;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SecureContainer implements AoCFunctionality {
    private final int rangeFrom;
    private final int rangeTo;

    public SecureContainer(String fileName) {
        String input = readString(fileName);
        List<Integer> inputRange = Arrays
                .stream(input.split("-"))
                .map(value -> Integer.parseInt(value.trim()))
                .toList();

        if (inputRange.size() != 2) {
            throw new IllegalArgumentException(
                    String.format("Input '%s' does not fit the input format '${RANGEFROM}-${RANGETO}'.", input)
            );
        }

        rangeFrom = inputRange.getFirst();
        rangeTo = inputRange.getLast();
    }

    public static long processTask1(String fileName) {
        SecureContainer instance = new SecureContainer(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SecureContainer instance = new SecureContainer(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return IntStream
                .rangeClosed(rangeFrom, rangeTo)
                .filter(this::isValidPassword)
                .count();
    }

    public long processTask2() {
        return 0;
    }

    private boolean isValidPassword(int password) {
        String passwordString = String.valueOf(password);
        boolean hasTwoSameAdjacentDigits = false;
        int lastDigit = -1;

        for (int i = 0; i < passwordString.length(); i++) {
            int currentDigit = passwordString.charAt(i) - '0';

            if (lastDigit == currentDigit) {
                hasTwoSameAdjacentDigits = true;
            } else if (lastDigit > currentDigit) {
                return false;
            }

            lastDigit = currentDigit;
        }

        return hasTwoSameAdjacentDigits;
    }
}
