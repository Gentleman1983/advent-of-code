package de.havox_design.aoc2015.day01;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class NotQuiteLisp {
    private final String input;

    public NotQuiteLisp(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int processTask1(String fileName) {
        NotQuiteLisp instance = new NotQuiteLisp(fileName);
        return instance.processTask1();
    }

    public int processTask1() {
        int currentFloor = 0;

        for(char c : input.toCharArray()) {
            currentFloor += translateFloor(c);
        }

        return currentFloor;
    }

    private int translateFloor(char c) {
        if('(' == c) {
            return 1;
        } else if (')' == c) {
            return -1;
        }
        else {
            throw new IllegalArgumentException("Illegal character found.");
        }
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
