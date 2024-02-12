package de.havox_design.aoc2018.day15;

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MapParser {
    private static final String WALL_FORMAT = "#";
    private static final String OPEN_FORMAT = "\\.";
    private static final String GOBLIN_FORMAT = "G";
    private static final String ELF_FORMAT = "E";

    public GameMap parse(List<String> input) {

        Field[][] field = new Field[
                input
                        .stream()
                        .mapToInt(String::length)
                        .max()
                        .orElseThrow()
                ][input.size()];
        List<Unit> units = new ArrayList<>();

        for (int y = 0; y < input.size(); y++) {
            String t = input.get(y);

            for (int x = 0; x < t.length(); x++) {
                parseChar(new Coordinate(x, y), t.substring(x, x + 1), field, units);
            }
        }

        return new GameMap(field, units);
    }

    private void parseChar(Coordinate position, String string, Field[][] field, List<Unit> units) {
        if (string.matches(WALL_FORMAT)) {
            field[position.getX()][position.getY()] = Field.WALL;
        } else if (string.matches(OPEN_FORMAT)) {
            field[position.getX()][position.getY()] = Field.EMPTY;
        } else {
            field[position.getX()][position.getY()] = Field.EMPTY;

            Unit unit;

            if (string.matches(GOBLIN_FORMAT)) {
                unit = new Unit(units.size(), Team.GOBLIN, position);
            } else if (string.matches(ELF_FORMAT)) {
                unit = new Unit(units.size(), Team.ELF, position);
            } else {
                throw new IllegalArgumentException();
            }

            units.add(unit);
        }
    }
}
