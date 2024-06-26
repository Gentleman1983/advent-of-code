package de.havox_design.aoc2016.day14;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class OneTimePad implements AoCFunctionality {
    private final String input;

    private boolean stretched = false;

    public OneTimePad(String fileName) {
        input = readData(fileName).get(0);
    }

    public static long solvePart1(String fileName) {
        OneTimePad instance = new OneTimePad(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        OneTimePad instance = new OneTimePad(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        return solve();
    }

    public long solvePart2() {
        stretched = true;

        return solve();
    }

    private int solve() {
        String salt = input;
        List<String> hashes = new ArrayList<>();
        List<Integer> keyIndices = new ArrayList<>();

        for (int i = 0; keyIndices.size() < 64; i++) {
            String hash = getHash(hashes, salt, i);
            Optional<Character> character = getTripletChar(hash);

            if (character.isPresent()) {
                boolean isKey = IntStream
                        .rangeClosed(i + 1, i + 1000)
                        .anyMatch(index -> hasFiveCharInARow(getHash(hashes, salt, index), character.get()));

                if (isKey) {
                    keyIndices.add(i);
                }
            }
        }

        return keyIndices
                .getLast();
    }

    private Optional<Character> getTripletChar(String string) {
        return IntStream
                .range(0, string.length() - 2)
                .filter(i -> string.charAt(i) == string.charAt(i + 1) && string.charAt(i) == string.charAt(i + 2))
                .mapToObj(string::charAt)
                .findAny();
    }

    private boolean hasFiveCharInARow(String string, char character) {
        return IntStream
                .range(0, string.length() - 4)
                .anyMatch(i -> string.charAt(i) == character
                        && string.charAt(i + 1) == character
                        && string.charAt(i + 2) == character
                        && string.charAt(i + 3) == character
                        && string.charAt(i + 4) == character);
    }

    private String getHash(List<String> hashes, String salt, int index) {
        if (index == hashes.size()) {
            hashes.add(getHash(salt + index));
        }

        return hashes.get(index);
    }

    private String getHash(String string) {
        String hashcode = string;

        for (int i = 0, count = getHashingRuns(); i < count; i++) {
            hashcode = getMd5Hash(hashcode);
        }

        return hashcode;
    }

    private int getHashingRuns() {
        return stretched ? 2017 : 1;
    }

    @SuppressWarnings({"squid:S4790"})
    private static String getMd5Hash(String string) {
        return DigestUtils
                .md5Hex(string);
    }
}
