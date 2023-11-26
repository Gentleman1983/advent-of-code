package de.havox_design.aoc2015.day25;

import de.havox_design.aoc2015.utils.DataReader;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeLock {
    public static final Pattern PATTERN = Pattern.compile(".+row (\\d+), column (\\d+).");

    private final List<String> input;

    public CodeLock(String fileName) {
        input = readData(fileName);
    }

    public static BigInteger solvePart1(String fileName) {
        CodeLock instance = new CodeLock(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        CodeLock instance = new CodeLock(fileName);
        return instance.solvePart2();
    }

    public BigInteger solvePart1() {
        Matcher matcher = matchRegex(PATTERN, input.get(0));
        return calculateCode(Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(1)));
    }

    private BigInteger calculateCode(long column, long row) {
        long v = (column + row - 2) * (column + row - 1) / 2 + column;
        BigInteger c = BigInteger.valueOf(20151125L);
        BigInteger f = BigInteger.valueOf(252533L);
        BigInteger m = BigInteger.valueOf(33554393L);
        return c.multiply(f.modPow(BigInteger.valueOf(v - 1), m)).mod(m);
    }

    public Matcher matchRegex(final Pattern pattern, final CharSequence input) {
        final Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Input '" + input + "' does not match pattern " + pattern.pattern());
        }
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
