package de.havox_design.aoc2016.day21;


import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc2016.day21.operations.ScramblingOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScrambledLettersAndHash implements AoCFunctionality {
    private final List<String> input;

    public ScrambledLettersAndHash(String fileName) {
        input = readData(fileName);
    }

    public static String processTask1(String fileName) {
        return processTask1(fileName, "abcdefgh");
    }

    public static String processTask1(String fileName, String scrambledPassword) {
        ScrambledLettersAndHash instance = new ScrambledLettersAndHash(fileName);
        return instance.solveTask1(scrambledPassword);
    }

    public static String processTask2(String fileName) {
        return processTask2(fileName, "fbgdceah");
    }

    public static String processTask2(String fileName, String scrambledPassword) {
        ScrambledLettersAndHash instance = new ScrambledLettersAndHash(fileName);
        return instance.solveTask2(scrambledPassword);
    }

    public String solveTask1(String scrambledPassword) {
        List<ScramblingOperation> operations = new ArrayList<>();
        String password = scrambledPassword;

        for (String operation : input) {
            operations.add(ScramblingOperation.createOperation(operation));
        }

        for (ScramblingOperation op : operations) {
            password = op.scramble(password);
        }

        return password;
    }

    public String solveTask2(String scrambledPassword) {
        List<ScramblingOperation> operations = new ArrayList<>();
        String password = scrambledPassword;

        for (String operation : input) {
            operations.add(ScramblingOperation.createOperation(operation));
        }

        Collections.reverse(operations);

        for (ScramblingOperation op : operations) {
            password = op.unscramble(password);
        }

        return password;
    }
}
