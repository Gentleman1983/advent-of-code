package de.havox_design.aoc2015.day01;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class NotQuiteLisp implements AoCFunctionality {
    private final String input;

    public NotQuiteLisp(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int processTask1(String fileName) {
        NotQuiteLisp instance = new NotQuiteLisp(fileName);
        return instance.processTask1();
    }

    public static int processTask2(String fileName) {
        NotQuiteLisp instance = new NotQuiteLisp(fileName);
        return instance.processTask2();
    }

    public int processTask1() {
        int currentFloor = 0;

        for (char c : input.toCharArray()) {
            currentFloor += translateFloor(c);
        }

        return currentFloor;
    }

    public int processTask2() {
        int currentFloor = 0;
        int step = 0;

        for (char c : input.toCharArray()) {
            step++;
            currentFloor += translateFloor(c);

            if (-1 == currentFloor) {
                return step;
            }
        }

        throw new IllegalArgumentException("Expected Santa to enter basement (floor '-1').");
    }

    private int translateFloor(char c) {
        if ('(' == c) {
            return 1;
        } else if (')' == c) {
            return -1;
        } else {
            throw new IllegalArgumentException("Illegal character found.");
        }
    }
}
