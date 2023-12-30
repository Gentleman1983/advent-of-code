package de.havox_design.aoc2016.day23;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.Arrays;
import java.util.List;

public class Day23 {
    private static final String NEWLINE = "\n";
    private static final String MULTIPLICATION_ORIGINAL =
            "cpy 0 a\ncpy b c\ninc a\ndec c\njnz c -2\ndec d\njnz d -5";
    private static final String MULTIPLICATION_OPTIMIZED =
            "cpy b a\nmul a d\ncpy 0 c\ncpy 0 d\ncpy 0 d\ncpy 0 d\ncpy 0 d";

    private final String input;

    public Day23(String fileName) {
        input = readData(fileName)
                .replace(MULTIPLICATION_ORIGINAL, MULTIPLICATION_OPTIMIZED);
    }

    public static long solvePart1(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return process(7);
    }

    public long solvePart2() {
        return process(12);
    }

    @SuppressWarnings({"squid:S3776", "squid:S127"})
    private long process(long initialValue) {
        List<String> rows = Arrays
                .stream(input.split(NEWLINE))
                .map(String::trim)
                .toList();
        CounterMap<String> mem = new CounterMap<>();
        mem.put("a", initialValue);

        boolean[] toggled = new boolean[rows.size()];
        for (int i = 0; i < rows.size(); ) {
            var p = rows
                    .get(i)
                    .split(" ");
            var cmd = p[0];

            if (toggled[i]) {
                cmd = switch (cmd) {
                    case "inc" -> "dec";
                    case "dec" -> "inc";
                    case "tgl" -> "inc";
                    case "jnz" -> "cpy";
                    case "cpy" -> "jnz";
                    default -> cmd;
                };
            }

            switch (cmd) {
                case "cpy" -> {
                    if (isRegister(p[2])) {
                        mem.put(p[2], getValue(mem, p[1]));
                    }
                }
                case "inc" -> {
                    if (isRegister(p[1])) {
                        mem.inc(p[1]);
                    }
                }
                case "dec" -> {
                    if (isRegister(p[1])) {
                        mem.dec(p[1]);
                    }
                }
                case "jnz" -> {
                    long value = getValue(mem, p[1]);
                    if (value != 0) {
                        i += (int) getValue(mem, p[2]);
                        continue;
                    }
                }
                case "tgl" -> {
                    int j = i + (int) getValue(mem, p[1]);
                    if (j >= 0 && j < toggled.length) {
                        toggled[j] = !toggled[j];
                    }
                }
                case "mul" -> {
                    if (isRegister(p[1])) {
                        mem.put(p[1], mem.get(p[1]) * getValue(mem, p[2]));
                    }
                }
                default -> throw new IllegalArgumentException("Unknown command: " + cmd);
            }

            i++;
        }

        return mem.getValue("a");
    }

    private long getValue(CounterMap<String> mem, String arg) {
        return isRegister(arg) ? mem.getValue(arg) : Long.parseLong(arg);
    }

    private boolean isRegister(String s) {
        return s.length() == 1
                && Character.isLetter(s.charAt(0));
    }

    private String readData(String fileName) {
        return DataReader.readString(fileName, MainClass.class);
    }
}
