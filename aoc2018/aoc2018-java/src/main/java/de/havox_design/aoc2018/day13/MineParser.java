package de.havox_design.aoc2018.day13;

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MineParser {
    private static final String CART_FORMAT = "[<>v^]";
    private static final String CROSSING_FORMAT = "\\+";
    private static final String DOWN_FORMAT = "[v|]";
    private static final String DOWN_RIGHT_FORMAT = "/";
    private static final String LEFT_FORMAT = "[-<]";
    private static final String RIGHT_FORMAT = "[->]";
    private static final String UP_FORMAT = "[|^]";
    private static final String UP_LEFT_FORMAT = "\\\\";

    public Mine parse(List<String> input) {
        Set<Cart> carts = new HashSet<>();
        Path[][] paths = new Path[
                input
                        .stream()
                        .mapToInt(String::length)
                        .max()
                        .orElseThrow()
                ][input.size()];
        Coordinate point;

        for (int i = 0; i < input.size(); i++) {
            String[] s = input.get(i).split("(?!^)");

            for (int j = 0; j < s.length; j++) {
                point = new Coordinate(j, i);
                parseCharacters(point, s[j], paths, carts);
            }
        }

        return new Mine(paths, carts);
    }

    private void parseCharacters(Coordinate p, String s, Path[][] paths, Set<Cart> carts) {
        if (s.matches(DOWN_RIGHT_FORMAT)) {
            paths[p.getX()][p.getY()] = Path.SW_NE;
        } else if (s.matches(UP_LEFT_FORMAT)) {
            paths[p.getX()][p.getY()] = Path.NW_SE;
        } else if (s.matches(CROSSING_FORMAT)) {
            paths[p.getX()][p.getY()] = Path.CROSSING;
        } else {
            FourDirectionsFlipped d = null;

            if (s.matches(LEFT_FORMAT)) {
                d = FourDirectionsFlipped.LEFT;
                paths[p.getX()][p.getY()] = Path.HORIZONTAL;
            } else if (s.matches(RIGHT_FORMAT)) {
                d = FourDirectionsFlipped.RIGHT;
                paths[p.getX()][p.getY()] = Path.HORIZONTAL;
            } else if (s.matches(UP_FORMAT)) {
                d = FourDirectionsFlipped.UP;
                paths[p.getX()][p.getY()] = Path.VERTICAL;
            } else if (s.matches(DOWN_FORMAT)) {
                d = FourDirectionsFlipped.DOWN;
                paths[p.getX()][p.getY()] = Path.VERTICAL;
            }

            if (s.matches(CART_FORMAT)) {
                carts.add(new Cart(carts.size(), p, d));
            }
        }
    }
}
