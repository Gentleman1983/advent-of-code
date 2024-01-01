package de.havox_design.aoc2015.day06;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class HouseDecoratingContest implements AoCFunctionality {
    private static final int FROM_X = 0;
    private static final int FROM_Y = 1;
    private static final int TO_X = 2;
    private static final int TO_Y = 3;

    private final List<String> input;
    private final boolean[][] houseDecoration = new boolean[1000][1000];
    private final int[][] brightness = new int[1000][1000];

    public HouseDecoratingContest(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        HouseDecoratingContest instance = new HouseDecoratingContest(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        HouseDecoratingContest instance = new HouseDecoratingContest(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        for (String instruction : input) {
            if (instruction.startsWith("turn on")) {
                int[] values = parseInstruction(instruction, 8);
                turnOnLight(values[FROM_X], values[TO_X], values[FROM_Y], values[TO_Y]);

            } else if (instruction.startsWith("turn off")) {
                int[] values = parseInstruction(instruction, 9);
                turnOffLight(values[FROM_X], values[TO_X], values[FROM_Y], values[TO_Y]);

            } else if (instruction.startsWith("toggle")) {
                int[] values = parseInstruction(instruction, 7);
                toggleLight(values[FROM_X], values[TO_X], values[FROM_Y], values[TO_Y]);
            } else {
                throw new IllegalArgumentException("no valid instruction: " + instruction);
            }
        }

        return countNumberOfLitLights();
    }

    public int solvePart2() {
        solvePart1();
        return countBrightness();
    }

    private int countNumberOfLitLights() {
        int numberOfLightsLit = 0;

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if (houseDecoration[x][y]) {
                    numberOfLightsLit++;
                }
            }
        }

        return numberOfLightsLit;
    }

    private int countBrightness() {
        int currentBrightness = 0;

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                currentBrightness += brightness[x][y];
            }
        }

        return currentBrightness;
    }

    private int[] parseInstruction(String instruction, int offset) {
        int[] values = new int[4];
        String data = instruction.substring(offset);
        String[] words = data.split(" ");
        String[] fromWords = words[0].split(",");
        String[] toWords = words[2].split(",");

        values[FROM_X] = Integer.parseInt(fromWords[0]);
        values[FROM_Y] = Integer.parseInt(fromWords[1]);
        values[TO_X] = Integer.parseInt(toWords[0]);
        values[TO_Y] = Integer.parseInt(toWords[1]);

        return values;
    }

    private void turnOnLight(int fromX, int toX, int fromY, int toY) {
        setLight(fromX, toX, fromY, toY, true, 1);
    }

    private void turnOffLight(int fromX, int toX, int fromY, int toY) {
        setLight(fromX, toX, fromY, toY, false, -1);
    }

    private void toggleLight(int x, int y) {
        setLight(x, y, !houseDecoration[x][y], 2);
    }

    private void toggleLight(int fromX, int toX, int fromY, int toY) {
        for (int x = fromX; x <= toX; x++) {
            for (int y = fromY; y <= toY; y++) {
                toggleLight(x, y);
            }
        }
    }

    private void setLight(int x, int y, boolean value, int brightnessChange) {
        houseDecoration[x][y] = value;
        brightness[x][y] += brightnessChange;

        if (brightness[x][y] < 0) {
            brightness[x][y] = 0;
        }
    }

    private void setLight(int fromX, int toX, int fromY, int toY, boolean value, int brightnessChange) {
        for (int x = fromX; x <= toX; x++) {
            for (int y = fromY; y <= toY; y++) {
                setLight(x, y, value, brightnessChange);
            }
        }
    }
}
