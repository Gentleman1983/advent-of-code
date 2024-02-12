package de.havox_design.aoc2018.day14;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;

public class ChocolateCharts implements AoCFunctionality {
    private final int input;

    public ChocolateCharts(String fileName) {
        input = Integer.parseInt(readString(fileName).trim());
    }

    public static String processTask1(String fileName) {
        ChocolateCharts instance = new ChocolateCharts(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChocolateCharts instance = new ChocolateCharts(fileName);
        return instance.processTask2();
    }

    public String processTask1() {
        StringBuilder scores = new StringBuilder("37");

        int[] elfIndices = new int[]{0, 1};

        while (scores.length() < input + 10) {
            int newScore = Arrays
                    .stream(elfIndices)
                    .map(scores::charAt)
                    .map(i -> i - '0')
                    .sum();

            scores.append(newScore);
            updateElfIndices(scores, elfIndices);
        }

        return scores.substring(input, input + 10);
    }

    public long processTask2() {
        StringBuilder scores = new StringBuilder("37");
        String wanted = String.valueOf(input);

        int[] elfIndices = new int[]{0, 1};
        long indexOf = -1L;

        while (indexOf < 0L) {
            int newScore = Arrays
                    .stream(elfIndices)
                    .map(scores::charAt)
                    .map(i -> i - '0')
                    .sum();

            scores.append(newScore);
            indexOf = scores.indexOf(wanted, scores.length() - wanted.length() - 1);
            updateElfIndices(scores, elfIndices);
        }

        return indexOf;
    }

    private void updateElfIndices(CharSequence scores, int[] elfIndices) {
        for (int i = 0; i < elfIndices.length; i++) {
            elfIndices[i] += scores.charAt(elfIndices[i]) - '0' + 1;
            elfIndices[i] %= scores.length();
        }
    }
}
