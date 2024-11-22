package de.havox_design.aoc2022.day17;

@SuppressWarnings("javaarchitecture:S7027")
public class Block {
    private static final int ID_X = 0;
    private static final int ID_Y = 1;

    private final Tetris tetris;
    private final Shape shape;
    private int x;
    private int y;

    public Block(Tetris tetris, Shape shape, int[] spawn) {
        this.tetris = tetris;
        this.shape = shape;
        this.x = spawn[ID_X];
        this.y = spawn[ID_Y];
    }

    private int[][] leftEdge() {
        return switch (shape) {
            case SHAPE_MINUS -> new int[][]{{x, y}};
            case SHAPE_PLUS -> new int[][]{{x, y}, {x - 1, y - 1}, {x, y - 2}};
            case SHAPE_BACKWARDS_L -> new int[][]{{x, y}, {x, y - 1}, {x - 2, y - 2}};
            case SHAPE_I -> new int[][]{{x, y}, {x, y - 1}, {x, y - 2}, {x, y - 3}};
            case SHAPE_SQUARE -> new int[][]{{x, y}, {x, y - 1}};
        };
    }

    private int[][] rightEdge() {
        return switch (shape) {
            case SHAPE_MINUS -> new int[][]{{x + 3, y}};
            case SHAPE_PLUS -> new int[][]{{x, y}, {x + 1, y - 1}, {x, y - 2}};
            case SHAPE_BACKWARDS_L -> new int[][]{{x, y}, {x, y - 1}, {x, y - 2}};
            case SHAPE_I -> new int[][]{{x, y}, {x, y - 1}, {x, y - 2}, {x, y - 3}};
            case SHAPE_SQUARE -> new int[][]{{x + 1, y}, {x + 1, y - 1}};
        };
    }

    private int[][] bottomEdge() {
        return switch (shape) {
            case SHAPE_MINUS -> new int[][]{{x, y}, {x + 1, y}, {x + 2, y}, {x + 3, y}};
            case SHAPE_PLUS -> new int[][]{{x - 1, y - 1}, {x, y - 2}, {x + 1, y - 1}};
            case SHAPE_BACKWARDS_L -> new int[][]{{x - 2, y - 2}, {x - 1, y - 2}, {x, y - 2}};
            case SHAPE_I -> new int[][]{{x, y - 3}};
            case SHAPE_SQUARE -> new int[][]{{x, y - 1}, {x + 1, y - 1}};
        };
    }

    protected void push(int push) {
        int[][] edge = push < 0 ? leftEdge() : rightEdge();

        for (var e : edge) {
            if (notFree(e[0] + push, e[1])) {
                return;
            }
        }

        x += push;
    }

    protected boolean drop() {
        for (var e : bottomEdge()) {
            if (notFree(e[0], e[1] - 1)) {
                return false;
            }
        }

        y--;

        return true;
    }

    protected void addToGrid() {
        while (tetris.grid.size() < y + 1) {
            tetris.grid.add(0);
        }

        switch (shape) {
            case SHAPE_MINUS -> tetris.grid.set(y, tetris.grid.get(y) | (0b1111000 >> x));
            case SHAPE_PLUS -> {
                tetris.grid.set(y, tetris.grid.get(y) | (0b0100000 >> (x - 1)));
                tetris.grid.set(y - 1, tetris.grid.get(y - 1) | (0b1110000 >> (x - 1)));
                tetris.grid.set(y - 2, tetris.grid.get(y - 2) | (0b0100000 >> (x - 1)));
            }
            case SHAPE_BACKWARDS_L -> {
                tetris.grid.set(y, tetris.grid.get(y) | (0b0010000 >> (x - 2)));
                tetris.grid.set(y - 1, tetris.grid.get(y - 1) | (0b0010000 >> (x - 2)));
                tetris.grid.set(y - 2, tetris.grid.get(y - 2) | (0b1110000 >> (x - 2)));
            }
            case SHAPE_I -> {
                for (var i = y; i > y - 4; i--) {
                    tetris.grid.set(i, tetris.grid.get(i) | (0b1000000 >> x));
                }
            }
            case SHAPE_SQUARE -> {
                tetris.grid.set(y, tetris.grid.get(y) | (0b1100000 >> x));
                tetris.grid.set(y - 1, tetris.grid.get(y - 1) | (0b1100000 >> x));
            }
        }
    }

    private boolean notFree(int x, int y) {
        return x < 0 || x > 6 || y < 0 || (y < tetris.grid.size() && (tetris.grid.get(y) & (0b1000000 >> x)) != 0);
    }
}
