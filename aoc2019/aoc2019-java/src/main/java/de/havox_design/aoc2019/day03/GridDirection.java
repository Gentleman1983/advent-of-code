package de.havox_design.aoc2019.day03;

import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record GridDirection(FourDirections direction, int length) {
    public static final Pattern GRID_DIRECTION_PATTERN = Pattern.compile("([URDL])(\\d+)");

    public static GridDirection parse(String gridDirectionStr) {
        Matcher matcher = GRID_DIRECTION_PATTERN.matcher(gridDirectionStr);

        if (matcher.matches()) {
            MatchResult matcherResult = matcher.toMatchResult();
            char directionLetter = matcherResult.group(1).charAt(0);
            FourDirections direction = switch (directionLetter) {
                case 'U' -> FourDirections.UP;
                case 'D' -> FourDirections.DOWN;
                case 'L' -> FourDirections.LEFT;
                case 'R' -> FourDirections.RIGHT;
                default -> throw new IllegalStateException("This should never be reached.");
            };
            int length = Integer.parseInt(matcherResult.group(2));

            return new GridDirection(direction, length);
        } else {
            throw new IllegalArgumentException("Expect GridDirection matching: " + GRID_DIRECTION_PATTERN);
        }
    }

    public List<GridPoint> pathFrom(GridPoint startingPoint) {
        List<GridPoint> path = new ArrayList<>(length);
        GridPoint currentPoint = startingPoint;

        for (int i = 0; i < length; i++) {
            currentPoint = currentPoint.moveTo(direction);
            path.add(currentPoint);
        }

        return path;
    }
}
