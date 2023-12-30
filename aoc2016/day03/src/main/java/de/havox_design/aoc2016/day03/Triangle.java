package de.havox_design.aoc2016.day03;

public record Triangle(int a, int b, int c) {
    public boolean isValid() {
        return (a + b > c) &&
                (a + c > b) &&
                (b + c > a);

    }

    public static Triangle fromString(String input) {
        String[] parts = input.split("\\s+");

        if(parts.length != 3) {
            throw new IllegalArgumentException("Input '" + input + "' does not match input format!");
        }

        return new Triangle(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
