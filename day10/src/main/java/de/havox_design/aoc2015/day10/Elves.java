package de.havox_design.aoc2015.day10;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Elves {
    private final String input;

    public Elves(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        Elves instance = new Elves(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        Elves instance = new Elves(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return processForIterations(40).length();
    }

    public int solvePart2() {
        return 0;
    }

    protected String processIteration(String input) {
        StringBuilder newInput = new StringBuilder();

        int i = 0;
        while(i < input.length()) {
            Pattern pattern = Pattern.compile(input.charAt(i) + "+");
            Matcher matcher = pattern.matcher(input.substring(i));

            if (matcher.find()) {
                String group = matcher.group(0);
                i += matcher.group(0).length() - 1;
                newInput.append(matcher.group(0).length()).append(group.charAt(0));
            }

            i++;
        }

        return newInput.toString();
    }

    protected String processForIterations(int iterations) {
        String newInput = input;

        for(int i = 0; i < iterations; i++) {
            newInput = processIteration(newInput);
        }

        return newInput;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
