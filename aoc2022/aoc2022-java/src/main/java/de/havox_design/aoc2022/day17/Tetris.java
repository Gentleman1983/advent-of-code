package de.havox_design.aoc2022.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Tetris {
    private final Supplier<Integer> nextPush;
    private final Supplier<Shape> nextShape;
    private final String puzzleInput;
    protected final List<Integer> grid = new ArrayList<>();

    public Tetris(String puzzleInput) {
        this.puzzleInput = puzzleInput;
        this.nextPush = nextPush();
        this.nextShape = nextShape();
    }

    private Supplier<Shape> nextShape() {
        return new Supplier<>() {
            private final Shape[] shapes = Shape.values();
            private int i = 0;

            @Override
            public Shape get() {
                return shapes[i++ % shapes.length];
            }
        };
    }

    private Supplier<Integer> nextPush() {
        return new Supplier<>() {
            private final char[] input = puzzleInput.trim().toCharArray();
            private int i = 0;

            @Override
            public Integer get() {
                return switch (input[i++ % input.length]) {
                    case '<' -> -1;
                    case '>' -> 1;
                    default -> throw new IllegalArgumentException("Invalid push: " + input[i - 1]);
                };
            }
        };
    }

    void tick() {
        Shape shape = nextShape.get();
        int[] spawn = shape.spawn(grid.size() - 1);
        Block block = new Block(this, shape, spawn);
        boolean dropped;

        do {
            block.push(nextPush.get());
            dropped = block.drop();
        } while (dropped);

        block.addToGrid();
    }
}
