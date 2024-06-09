package de.havox_design.aoc2016.day03;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;

public class SquaresWithThreeSides implements AoCFunctionality {
    private final List<String> input;

    public SquaresWithThreeSides(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        SquaresWithThreeSides instance = new SquaresWithThreeSides(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        SquaresWithThreeSides instance = new SquaresWithThreeSides(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        List<Triangle> triangles = input
                .stream()
                .map(String::trim)
                .map(Triangle::fromString)
                .toList();

        return processValidTriangleCount(triangles);
    }

    public long solvePart2() {
        List<Triangle> triangles = buildTriangleListForPart2();

        return processValidTriangleCount(triangles);
    }

    private long processValidTriangleCount(List<Triangle> triangles) {
        long validTriangles = 0L;

        for(Triangle triangle : triangles) {
            if(triangle.isValid()) {
                validTriangles++;
            }
        }

        return validTriangles;
    }

    private List<Triangle> buildTriangleListForPart2() {
        List<Triangle> triangles = new ArrayList<>();

        for(int i = 0; i < input.size() - 2; i += 3) {
            int j = i + 1;
            int k = i + 2;

            String[] dataRowI = splitData(input.get(i));
            String[] dataRowJ = splitData(input.get(j));
            String[] dataRowK = splitData(input.get(k));

            triangles.add(
                    new Triangle(Integer.parseInt(dataRowI[0]),
                            Integer.parseInt(dataRowJ[0]),
                            Integer.parseInt(dataRowK[0]))
            );
            triangles.add(
                    new Triangle(Integer.parseInt(dataRowI[1]),
                            Integer.parseInt(dataRowJ[1]),
                            Integer.parseInt(dataRowK[1]))
            );
            triangles.add(
                    new Triangle(Integer.parseInt(dataRowI[2]),
                            Integer.parseInt(dataRowJ[2]),
                            Integer.parseInt(dataRowK[2]))
            );
        }

        return triangles;
    }

    private String[] splitData(String string) {
        String[] result = string.trim().split("\\s+");

        if(result.length != 3) {
            throw new IllegalArgumentException("Input '" + string + "' is no valid input.");
        }

        return result;
    }
}
