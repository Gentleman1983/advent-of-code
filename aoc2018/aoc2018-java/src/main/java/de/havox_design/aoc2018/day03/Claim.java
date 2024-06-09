package de.havox_design.aoc2018.day03;

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Claim(int id, Set<Position2d<Integer>> positions) {
    public static Claim from(String claimMessage) {
        int id = 0;
        Set<Position2d<Integer>> positions = new HashSet<>();
        Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        Matcher matcher = pattern.matcher(claimMessage);

        if(matcher.matches()) {
            id = Integer.parseInt(matcher.group(1));

            int startX = Integer.parseInt(matcher.group(2));
            int startY = Integer.parseInt(matcher.group(3));
            int sizeX = Integer.parseInt(matcher.group(4));
            int sizeY = Integer.parseInt(matcher.group(5));

            for(int x = startX; x < startX + sizeX; x++) {
                for(int y = startY; y < startY + sizeY; y++) {
                    positions.add(new Position2d<>(x, y));
                }
            }
        }

        return new Claim(id, positions);
    }
}
