package de.havox_design.aoc2015.day04;

import de.havox_design.aoc2015.utils.DataReader;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class AdventCoins {
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
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            String combinedKey = input + i;
            String md5Hash = DigestUtils.md5Hex(combinedKey);

            if(md5Hash.startsWith("00000")) {
                return i;
            }
        }

        throw new IllegalStateException("An valid hash should be able to be found.");
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
