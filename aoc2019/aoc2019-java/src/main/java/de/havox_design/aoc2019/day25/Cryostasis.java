package de.havox_design.aoc2019.day25;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Cryostasis implements AoCFunctionality {
    private final String input;

    public Cryostasis(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Cryostasis instance = new Cryostasis(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Cryostasis instance = new Cryostasis(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
