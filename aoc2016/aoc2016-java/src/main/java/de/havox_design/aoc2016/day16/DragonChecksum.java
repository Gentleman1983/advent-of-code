package de.havox_design.aoc2016.day16;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DragonChecksum implements AoCFunctionality {
    private final String input;
    private byte[] inputBytes;

    public DragonChecksum(String fileName) {
        input = readData(fileName).getFirst();
        inputBytes = parseInputBytes();
    }

    public static String solvePart1(String fileName) {
        DragonChecksum instance = new DragonChecksum(fileName);

        return instance.solvePart1(272);
    }

    public static String solvePart2(String fileName) {
        DragonChecksum instance = new DragonChecksum(fileName);

        return instance.solvePart2(35651584);
    }

    public String solvePart1(int discSize) {
        return solve(discSize);
    }

    public String solvePart2(int discSize) {
        return solve(discSize);
    }

    private String solve(int size) {
        byte[] data = inputBytes.clone();

        while (data.length < size) {
            data = expand(data);
        }

        data = Arrays.copyOf(data, size);

        byte[] checksum = data.clone();

        while (checksum.length % 2 == 0) {
            checksum = getChecksum(checksum);
        }

        byte[] ch = checksum;

        return IntStream.range(0, inputBytes.length)
                .mapToObj(i -> String.valueOf(ch[i]))
                .collect(Collectors.joining());
    }

    private byte[] expand(byte[] a) {
        byte[] result = Arrays.copyOf(a, a.length + 1 + a.length);

        for (int i = 0, j = result.length - 1; i < a.length; i++, j--) {
            result[j] = (byte) (a[i] ^ 1);
        }

        return result;
    }

    private byte[] getChecksum(byte[] a) {
        byte[] result = new byte[a.length / 2];

        for (int i = 0, j = 0; i < a.length; i += 2, j++) {
            result[j] = (byte) (a[i] ^ a[i + 1] ^ 1);
        }

        return result;
    }

    private byte[] parseInputBytes() {
        byte[] b = new byte[input.length()];

        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (input.charAt(i) == '1' ? 1 : 0);
        }

        return b;
    }
}
