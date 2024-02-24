package de.havox_design.aoc2018.day22;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeMaze implements AoCFunctionality {
    private static final int ID_INPUT_DEPTH = 0;
    private static final int ID_INPUT_TARGET = 1;

    private final int depth;
    private final Position2d<Integer> target;
    private final Map<Position2d<Integer>, Double> erosionLevels;

    public ModeMaze(String fileName) {
        List<String> input = readData(fileName);
        final DepthParser depthParser = new DepthParser();
        final TargetParser targetParser = new TargetParser();

        this.depth = depthParser.parse(input.get(ID_INPUT_DEPTH));
        this.target = targetParser.parse(input.get(ID_INPUT_TARGET));
        this.erosionLevels = new HashMap<>();
    }

    public static long processTask1(String fileName) {
        ModeMaze instance = new ModeMaze(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ModeMaze instance = new ModeMaze(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        int[][] cave = new int[target.getY() + 1][target.getX() + 1];

        int riskLevel = 0;

        for (int y = 0; y < cave.length; y++) {
            String row = y + ":\t";
            for (int x = 0; x < cave[0].length; x++) {
                int type = getType(x, y);

                if (type == 0) {
                    row += ".";
                } else if (type == 1) {
                    row += "=";
                } else if (type == 2) {
                    row += "|";
                }

                riskLevel += type;
            }
            //System.out.println(row);
        }

        return riskLevel;
    }

    public long processTask2() {
        return 0;
    }

    private int getType(int x, int y) {
        Position2d<Integer> key = new Position2d<>(x, y);
        double value;
        if ((x == 0 && y == 0) || (y == target.getY() && x == target.getX())) {
            value = 0D;
        } else if (y == 0) {
            value = x * 16807D;
        } else if (x == 0) {
            value = y * 48271D;
        } else {
            double left = erosionLevels.get(new Position2d<>(x - 1, y));
            double top = erosionLevels.get(new Position2d<>(x, y - 1));
            value = left * top;
        }
        double erosionLevel = (value + depth) % 20183;
        erosionLevels.put(key, erosionLevel);

        return ((int) erosionLevel) % 3;
    }

}
