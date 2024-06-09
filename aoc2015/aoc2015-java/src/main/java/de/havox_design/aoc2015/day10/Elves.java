package de.havox_design.aoc2015.day10;


import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Elves implements AoCFunctionality {
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
        return processForIterations(50).length();
    }

    protected String processIteration(String input) {
        StringBuilder newInput = new StringBuilder();
        int i = 0;

        while(i < input.length()) {
            i = lookAndSay(input, newInput, i);
        }

        return newInput.toString();
    }

    private int lookAndSay(String input, StringBuilder newInput, int from) {
        int i = from;

        while (i < input.length() && input.charAt(i) == input.charAt(from)) {
            ++i;
        }

        newInput.append(i - from);
        newInput.append(input.charAt(from));

        return i;
    }

    protected String processForIterations(int iterations) {
        String newInput = input;

        for(int i = 0; i < iterations; i++) {
            newInput = processIteration(newInput);
        }

        return newInput;
    }
}
