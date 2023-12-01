package de.havox_design.aoc2016.day05;

import de.havox_design.aoc2016.utils.DataReader;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day05 {
    private final String input;

    public Day05(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day05 instance = new Day05(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        Day05 instance = new Day05(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return IntStream
                .iterate(0, i -> i + 1)
                .mapToObj(i -> DigestUtils.md5Hex(input + i))
                .filter(v -> v.startsWith("00000"))
                .limit(8)
                .map(v -> String.valueOf(v.charAt(5)))
                .collect(Collectors.joining());
    }

    public String solvePart2() {
        var password = new ArrayList<>(Collections.nCopies(8, " "));
        var pattern = Pattern.compile("^00000[0-7]");

        for (long i = 0; password.contains(" "); i++) {
            var md5 = DigestUtils.md5Hex(input + i);
            if (pattern.matcher(md5).find()) {
                var index = Integer.parseInt(String.valueOf(md5.charAt(5)));

                if (password.get(index).equals(" ")) {
                    password.set(index, String.valueOf(md5.charAt(6)));
                }
            }
        }

        return String.join("", password);
    }

    private String readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class).get(0);
    }
}
