package de.havox_design.aoc.utils.java.helper;

public class JavaMathUtils {
    private JavaMathUtils() {
        // Utility class...
    }

    public static long greatestCommonDivisor(long a, long b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDivisor(b, a % b);
    }

    public static long leastCommonMultiple(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        long absNumberA = Math.abs(a);
        long absNumberB = Math.abs(b);
        long absHigherNumber = Math.max(absNumberA, absNumberB);
        long absLowerNumber = Math.min(absNumberA, absNumberB);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }

        return lcm;
    }
}
