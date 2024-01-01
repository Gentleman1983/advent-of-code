package de.havox_design.aoc2015.day04;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.codec.digest.DigestUtils;

public class AdventCoins implements AoCFunctionality {
    private final String input;

    public AdventCoins(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        AdventCoins instance = new AdventCoins(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        AdventCoins instance = new AdventCoins(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return detectSecretNumberForHashStart("00000");
    }

    public int solvePart2() {
        return detectSecretNumberForHashStart("000000");
    }

    private int detectSecretNumberForHashStart(String prefix) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String combinedKey = input + i;
            String md5Hash = DigestUtils.md5Hex(combinedKey);

            if (md5Hash.startsWith(prefix)) {
                return i;
            }
        }

        throw new IllegalStateException("An valid hash should be able to be found.");
    }
}
