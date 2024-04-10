package de.havox_design.aoc2019.day22;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.List;

public class SlamShuffle implements AoCFunctionality {
    private static final String INSTRUCTION_CUT_N = "cut";
    private static final String INSTRUCTION_DEAL_INTO_NEW_STACK = "deal into new stack";
    private static final String INSTRUCTION_DEAL_WITH_INCREMENT = "deal with increment";
    private static final char INSTRUCTION_VALUE_DELIMITER = ' ';

    private final List<String> input;

    public SlamShuffle(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        SlamShuffle instance = new SlamShuffle(fileName);
        return instance.processTask1();
    }

    public static BigInteger processTask2(String fileName) {
        SlamShuffle instance = new SlamShuffle(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        int targetIndex = 2019;
        int numberOfCards = 10007;

        for (String instruction : input) {
            if (instruction.contains(INSTRUCTION_DEAL_INTO_NEW_STACK)) {
                targetIndex = numberOfCards - targetIndex - 1;
            } else if (instruction.contains(INSTRUCTION_DEAL_WITH_INCREMENT)) {
                targetIndex = Math.floorMod(targetIndex * extractInstructionValue(instruction), numberOfCards);
            } else if (instruction.contains(INSTRUCTION_CUT_N)) {
                targetIndex = Math.floorMod(targetIndex + (extractInstructionValue(instruction) * (-1)), numberOfCards);
            }
        }

        return targetIndex;
    }

    public BigInteger processTask2() {
        BigInteger targetIndex = BigInteger.valueOf(2020);
        BigInteger numberOfCards = BigInteger.valueOf(119315717514047L);
        BigInteger shuffleCount = BigInteger.valueOf(101741582076661L);
        Pair<BigInteger, BigInteger> coefficients = getFinalCoefficients(numberOfCards.longValue());
        BigInteger a = coefficients
                .getLeft()
                .modPow(shuffleCount, numberOfCards);
        BigInteger b = coefficients
                .getRight()
                .mod(numberOfCards)
                .multiply(BigInteger.ONE.mod(numberOfCards).subtract(a).mod(numberOfCards))
                .mod(numberOfCards)
                .multiply(BigInteger.ONE.subtract(coefficients.getLeft()).modInverse(numberOfCards))
                .mod(numberOfCards);

        return targetIndex
                .subtract(b)
                .multiply(a.modInverse(numberOfCards))
                .mod(numberOfCards);
    }

    private int extractInstructionValue(String instruction) {
        return Integer.parseInt(instruction.substring(instruction.lastIndexOf(INSTRUCTION_VALUE_DELIMITER) + 1));
    }

    private Long extractInstructionLongValue(String instruction) {
        return Long.parseLong(instruction.substring(instruction.lastIndexOf(INSTRUCTION_VALUE_DELIMITER) + 1));
    }

    private Pair<BigInteger, BigInteger> getFinalCoefficients(long size) {
        Pair<BigInteger, BigInteger> coefficients = getCoefficients(input.getFirst());

        for (int i = 1; i < input.size(); i++) {
            coefficients = compose(input.get(i), coefficients, size);
        }

        return coefficients;
    }

    private Pair<BigInteger, BigInteger> getCoefficients(String instruction) {
        long a = 0L;
        long b = 0L;

        if (instruction.contains(INSTRUCTION_DEAL_INTO_NEW_STACK)) {
            a = -1L;
            b = -1L;
        } else if (instruction.contains(INSTRUCTION_DEAL_WITH_INCREMENT)) {
            a = extractInstructionLongValue(instruction);
        } else if (instruction.contains(INSTRUCTION_CUT_N)) {
            a = 1L;
            b = -1 * extractInstructionLongValue(instruction);
        }

        return Pair.of(BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    private Pair<BigInteger, BigInteger> compose(String instruction, Pair<BigInteger, BigInteger> coefficients, long size) {
        BigInteger a = coefficients.getLeft();
        BigInteger b = coefficients.getRight();
        Pair<BigInteger, BigInteger> cd = getCoefficients(instruction);
        BigInteger c = cd.getLeft();
        BigInteger d = cd.getRight();

        return Pair.of(a.multiply(c).mod(BigInteger.valueOf(size)), b.multiply(c).add(d).mod(BigInteger.valueOf(size)));
    }
}
