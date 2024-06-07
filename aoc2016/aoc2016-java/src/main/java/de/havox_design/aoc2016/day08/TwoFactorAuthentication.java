package de.havox_design.aoc2016.day08;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;

public class TwoFactorAuthentication implements AoCFunctionality {
    private static final int COLUMNS = 50;
    private static final int ROWS = 6;
    private static final int ID_ROW = 0;
    private static final int ID_COL = 1;
    private static final char ON = '#';
    private static final char OFF = '.';

    private final List<String> input;
    private char[][] display = new char[ROWS][COLUMNS];

    public TwoFactorAuthentication(String fileName) {
        input = readData(fileName);
        processDisplayInputs();
    }

    public static long solvePart1(String fileName) {
        TwoFactorAuthentication instance = new TwoFactorAuthentication(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        TwoFactorAuthentication instance = new TwoFactorAuthentication(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return getPixelCount(display);
    }

    public String solvePart2() {
        return printDisplay();
    }

    private void processDisplayInputs() {
        fillRectangle(display, ROWS, COLUMNS, OFF);

        for (var line : input) {
            if (line.startsWith("rect")) {
                var parts = line
                        .substring(5)
                        .split("x");
                fillRectangle(display, Integer.parseInt(parts[ID_COL]), Integer.parseInt(parts[ID_ROW]), ON);
            } else if (line.startsWith("rotate column")) {
                var parts = line.split("=")[1].split(" by ");
                shiftColumn(display, Integer.parseInt(parts[ID_ROW]), Integer.parseInt(parts[ID_COL]));
            } else if (line.startsWith("rotate row")) {
                var parts = line.split("=")[1].split(" by ");
                shiftRow(display, Integer.parseInt(parts[ID_ROW]), Integer.parseInt(parts[ID_COL]));
            }
        }
    }

    private String printDisplay() {
        StringBuilder builder = new StringBuilder().append("\n");

        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLUMNS; c++) {
                builder.append(display[r][c]);
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    private void shiftRow(char[][] display, int row, int shift) {
        for (int s = 0; s < shift; s++) {
            char last = display[row][COLUMNS - 1];
            for (int c = COLUMNS - 1; c > 0; c--) {
                display[row][c] = display[row][c - 1];
            }
            display[row][0] = last;
        }
    }

    private void shiftColumn(char[][] display, int column, int shift) {
        for (int s = 0; s < shift; s++) {
            char last = display[ROWS - 1][column];
            for (int r = ROWS - 1; r > 0; r--) {
                display[r][column] = display[r - 1][column];
            }
            display[0][column] = last;
        }
    }

    private void fillRectangle(char[][] display, int row, int column, char character) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                display[r][c] = character;
            }
        }
    }

    private long getPixelCount(char[][] display) {
        long count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (display[i][j] == ON) {
                    count++;
                }
            }
        }
        return count;
    }
}
