package de.havox_design.aoc2016.day17;

import com.google.common.hash.Hashing;
import de.havox_design.aoc2016.utils.input.DataReader;
import de.havox_design.aoc2016.utils.search.BreadthFirstSearch;
import de.havox_design.aoc2016.utils.search.PathResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day17 {
    private static final int SIZE = 4;
    private static final Tile START_TILE = new Tile(0, 0);
    private static final Tile TARGET_TILE = new Tile(3, 3);

    private final String input;

    public Day17(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        var resultMap = BreadthFirstSearch.run(new State(START_TILE, ""),
                st -> getNextFeasibleStates(st, input));

        return resultMap.values().stream()
                .filter(res -> res.getNode().tile().equals(TARGET_TILE))
                .min(Comparator.comparing(PathResult::getDistance))
                .orElseThrow()
                .getNode().path();
    }

    public long solvePart2() {
        return 0L;
    }

    private static List<State> getNextFeasibleStates(State state, String passCode) {
        if (state.tile().equals(TARGET_TILE)) {
            return List.of();
        }

        String hash = getMd5Hash(passCode + state.path());

        var result = new ArrayList<State>(4);
        for (int i = 0; i < 4; i++) {
            var neighbor = state.tile().neighbor(Direction.values()[i]);
            boolean doorIsOpen = neighbor.isValid(SIZE, SIZE)
                    && hash.charAt(i) >= 'b' && hash.charAt(i) <= 'f';
            if (doorIsOpen) {
                result.add(new State(neighbor, state.path() + Direction.values()[i].getSymbol()));
            }
        }

        return result;
    }

    @SuppressWarnings({ "deprecated", "UnstableApiUsage" })
    private static String getMd5Hash(String s) {
        return Hashing.md5().hashString(s, StandardCharsets.UTF_8).toString();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
