package de.havox_design.aoc2016.day05;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HowAboutANiceGameOfChess implements AoCFunctionality {
    private final String input;

    public HowAboutANiceGameOfChess(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName) {
        HowAboutANiceGameOfChess instance = new HowAboutANiceGameOfChess(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        HowAboutANiceGameOfChess instance = new HowAboutANiceGameOfChess(fileName);
        return instance.solvePart2();
    }

    @SuppressWarnings("squid:S4790")
    public String solvePart1() {
        return IntStream
                .iterate(0, i -> i + 1)
                .mapToObj(i -> DigestUtils.md5Hex(input + i))
                .filter(v -> v.startsWith("00000"))
                .limit(8)
                .map(v -> String.valueOf(v.charAt(5)))
                .collect(Collectors.joining());
    }

    @SuppressWarnings("squid:S4790")
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
}
