package de.havox_design.aoc2017.day15;

import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class GeneratorPair {

    private static final IntBinaryOperator OP =
            (x, y) -> (int) (((long) x * y) % Integer.MAX_VALUE);
    private static final IntUnaryOperator A_OP =
            i -> OP.applyAsInt(i, 16807);
    private static final IntUnaryOperator B_OP =
            i -> OP.applyAsInt(i, 48271);
    private static final IntUnaryOperator EXTRACTOR =
            x -> x % 65536;

    private final int a;
    private final int b;

    private GeneratorPair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public GeneratorPair next() {
        return of(a, b);
    }

    public GeneratorPair next(IntPredicate aPredicate, IntPredicate bPredicate) {
        return of(
                findFirst(IntStream.iterate(a, A_OP).filter(aPredicate)),
                findFirst(IntStream.iterate(b, B_OP).filter(bPredicate))
        );
    }

    public boolean matches() {
        return EXTRACTOR.applyAsInt(a) == EXTRACTOR.applyAsInt(b);
    }

    public static GeneratorPair of(int a, int b) {
        return of(() -> A_OP.applyAsInt(a), () -> B_OP.applyAsInt(b));
    }

    public static GeneratorPair of(IntSupplier aSupplier, IntSupplier bSupplier) {
        return new GeneratorPair(aSupplier.getAsInt(), bSupplier.getAsInt());
    }

    private static IntSupplier findFirst(IntStream stream) {
        return () -> stream
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expected value"));
    }
}
