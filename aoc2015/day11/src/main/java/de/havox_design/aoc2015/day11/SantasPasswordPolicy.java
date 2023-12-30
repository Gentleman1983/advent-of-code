package de.havox_design.aoc2015.day11;

import de.havox_design.aoc.utils.java.input.DataReader;

import static de.havox_design.aoc2015.day11.PasswordValidator.*;

import java.util.List;

public class SantasPasswordPolicy {
    private final String input;

    public SantasPasswordPolicy(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName) {
        SantasPasswordPolicy instance = new SantasPasswordPolicy(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        SantasPasswordPolicy instance = new SantasPasswordPolicy(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return nextPassword(input);
    }

    public String solvePart2() {
        return nextPassword(nextPassword(input));
    }

    private String nextPassword(String initialPassword) {
        byte[] encoded = encodeString(initialPassword);
        PasswordValidator validator = PasswordValidator.getInstance(encoded);
        boolean firstRun = true;

        while (firstRun || !validator.validate()) {
            if(firstRun) {
                firstRun = false;
            }

            increment(encoded);
            validator = PasswordValidator.getInstance(encoded);
        }
        return decodeString(encoded);
    }

    private void increment(byte[] password) {
        byte increment = (byte) 1;
        for (int i = password.length - 1; i >= 0 && increment > (byte) 0; --i) {
            byte incrementedPassword = (byte) (password[i] + increment);
            password[i] = (byte) (incrementedPassword % LETTER_RANGE);
            increment = (byte) (incrementedPassword / LETTER_RANGE);
        }
    }

    private byte[] encodeString(String string) {
        byte[] encodedString = new byte[string.length()];
        for (int i = 0; i < encodedString.length; ++i) {
            encodedString[i] = (byte) (Character.getNumericValue(string.charAt(i)) - FIRST_VALID_LETTER_VALUE);
        }
        return encodedString;
    }

    private String decodeString(byte[] encodedString) {
        StringBuilder password = new StringBuilder();
        for (byte b : encodedString) {
            password.append((char) (FIRST_VALID_LETTER + b));
        }
        return password.toString();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
