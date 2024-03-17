package de.havox_design.aoc2019.day12;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class TheNBodyProblem implements AoCFunctionality {
    private final String input;

    public TheNBodyProblem(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        TheNBodyProblem instance = new TheNBodyProblem(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        TheNBodyProblem instance = new TheNBodyProblem(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
